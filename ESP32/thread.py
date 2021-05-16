import threading
import time
import bt2
import tds
from bluetooth import *

led_auto = 0
server_socket= BluetoothSocket(RFCOMM)
server_socket,client_socket,address = bt2.bt_setup(server_socket)

class LED(threading.Thread):
    def __init__(self):
        super().__init__()
        
    def run(self):
        #led = LED.setup()
        while True:
            if(led_auto == 1):
                auto_time = led.time_check() 
                if(auto_time==1):
                    led.led_on()
                else:
                    led.led_off()
                time.sleep(0.1)
            else:
                send = 1
                print("led_task is manul now")
                time.sleep(10)
            
class LEVEL(threading.Thread):
    def __init__(self):
        super().__init__()
    def run(self):
        #level = water_level.setup()
        while True:
            #ret = water_level.check(level)
            print("chekcing level\n")
            ret = 0
            if(ret != 1):
                bt2.send(client_socket,"v15.1")
                print("sending Message")
            time.sleep(10)

            
class MOTOR(threading.Thread):
    def __init__(self):
        threading.Thread.__init__(self, name='Motor Thread')
    
    def run(self):
        global tds_Value
        tds_Value = tds()
        while 1:
            #if tds_Value < tds_Min
            if tds_Value < 100: # when tds value is lower than minimum tds.
                motor_7run(1) # turn on motor during 5 sec.
                time.sleep(120) # and wait 2 min.
                
            #elif tds_Value > tds_Max
            elif tds_Value > 200: # when tds value is lower than maximum tds.
                motor_8run(1) # turn on motor during 30 sec.
                time.sleep(120) # and wait 2 min.
                
            else:
                time.sleep(1)
        
print("main thread start")
#LED THREAD
led = LED()
led.start()

#WATER_LEVEL THREAD
level = LEVEL()
level.start()

#MOTOR THREAD
motor = MOTOR()
motor.start()

#BLUETOOTH THREAD
while True:
    data = bt2.read(client_socket)
    print("Received: %s" %data)
    
    if('A' in data):
        if(data[0]=='2'):
            print("Auto Mode ON")
        elif(data[0]=='1'):
            print("Manual Mode ON")
        else:
            print("data error")
            
    elif('M' in data):
        if(data[0]=='1'):
            print("LED ON")
        elif(data[0]=='2'):
            print("LED OFF")
        else:
            print("data error")
            
    elif('#' in data):
        if(data[0]=='1'):
            print("TDS: lettuce mode")
        elif(data[0]=='2'):
            print("TDS: chives mode")
        elif(data[0]=='3'):
            print("TDS: strawberry mode")
        else:
            printf("data error")
            
    else:
        print("Quit")
        break;

# JOIN
led.join()
level.join()
motor.join()
bt2.close()
print("main thread end")
