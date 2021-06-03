#System
import threading
import sys
import time
import math

#THREAD
import bt2
from motor import *
from tds import *
from bluetooth import *
from liquid_level import *
from led import *

#etc
import RPi.GPIO as GPIO
from grove.adc import ADC

# global variable
i=1
ret_temp=1
led_auto = 1 # 1: auto , 0: manual
led_ctrl = 0 # 1: ON , 0: OFF

# Make Socket
server_socket= BluetoothSocket(RFCOMM)
server_socket,client_socket,address = bt2.bt_setup(server_socket)

class LED(threading.Thread):
    def __init__(self):
        super().__init__()
        
    def run(self):
        led_setup()
        while True:
            if(led_auto == 1):
                auto_time = time_now()
                if(auto_time == 1):
                    led_on()
                else:
                    led_off()
                    
            else:
                if(led_ctrl == 1):
                    led_on()
                else:
                    led_off()
            time.sleep(0.1)
            
class LEVEL(threading.Thread):
    def __init__(self):
        super().__init__()
        
    def run(self):
        i = 0
        ret_temp = 0
        liquid_setup()
        while True:
            if(i == 0):
                ret_temp = check()
                i = 1
            else:
                ret = check()
                if(ret != ret_temp):
                    if(ret != 1):
                        bt2.send(client_socket,"lUnsufficient") #l1 = sufficient
                        i = 0
                    else:
                        bt2.send(client_socket,"lEnough") #l0 = enough
                        i = 0
            time.sleep(1)

            
class MOTOR(threading.Thread):
    def __init__(self):
        threading.Thread.__init__(self, name='Motor Thread')
    
    def run(self):
        motor_setup()
        global tds_Value
        tds_Value = get_tds()
        global tds_Range
        tds_Range = 0
        
        while 1:
            tds_Value = get_tds()
            #상추일때
            if tds_Range == 1:
                if tds_Value < 500: # tds가 500보다 낮으면
                    motor_6run(1)   #양액A 1초간 넣어줌
                    motor_13run(1)   #양액B 1초간 넣어줌
                    time.sleep(120) #양액이 퍼지는 동안 기다려줌 (2분)
                elif tds_Value > 800: #tds가 800보다 낮으면
                    motor_12run(5)     #물을 5초 동안 넣어줌
                    time.sleep(120)   #농도가 변화할때까지 기다림
                else:
                    time.sleep(1)  # 양액이 적정범위인지 1초마다 검사
            #부추일때
            elif tds_Range == 2:
                if tds_Value < 800:
                    motor_6run(1)
                    motor_13run(1)
                    time.sleep(120)
                elif tds_Value > 1000:
                    motor_12run(1)
                    time.sleep(120)
                else:
                    time.sleep(1)
            #딸기일때
            elif tds_Range == 3:
                if tds_Value < 900:
                    motor_6run(1)
                    motor_13run(1)
                    time.sleep(120)
                elif tds_Value > 1000:
                    motor_12run(1)
                    time.sleep(120)
                else:
                    time.sleep(1)
            else:
                print('Select crops by your smartphone')
                time.sleep(2)

class TDS(threading.Thread):
    def __init__(self):
        super().__init__()
    
    def run(self):
        while 1:
            msg_tds = get_tds()
            round_tds = round(msg_tds,2)
            msg = "v" + str(round_tds)
            print(msg)
            bt2.send(client_socket,msg)
            time.sleep(2)
        
print("main thread start")

#LED THREAD
led = LED()
led.start()

#WATER_LEVEL THREAD - OK
level = LEVEL()
level.start()

#MOTOR THREAD
motor = MOTOR()
motor.start()

#TDS THREAD
tds = TDS()
tds.start()

#BLUETOOTH THREAD
while True:
    data = bt2.read(client_socket)
    print("Received: %s" %data)
    
    if('A' in data):
        if(data[0]=='2'):
            led_auto = 1
            print("Auto Mode ON")
        elif(data[0]=='1'):
            led_auto = 0
            print("Manual Mode ON")
        else:
            print("data error")
            
    elif('M' in data): 
        if(data[0]=='1'):
            led_ctrl = 1
        elif(data[0]=='2'):
            led_ctrl = 0
        else:
            print("data error")
            
    elif('#' in data):
        global tds_Range
        if(data[0]=='1'):
            print("TDS: lettuce mode")
            tds_Range = 1
        elif(data[0]=='2'):
            print("TDS: chives mode")
            tds_Range = 2
        elif(data[0]=='3'):
            print("TDS: strawberry mode")
            tds_Range = 3
        else:
            printf("data error")
            
    else:
        print("Quit")
        break;

# JOIN
led.join()
level.join()
motor.join()
tds.join()
bt2.close()
#GPIO.output(13, False)
#GPIO.output(5, False)
#GPIO.output(6, False)
GPIO.cleanup()
print("main thread end")
