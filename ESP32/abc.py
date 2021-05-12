import threading
import time
import math
import sys
from grove.adc import ADC
from tds import *
from motor import *
from led import *
import RPi.GPIO as GPIO


class tdsThread(threading.Thread):
    def __init__(self):
        threading.Thread.__init__(self, name='Tds Thread')
        
    def run(self):
        global tds_Value
        while 1:
            tds_Value = tds()
            print('tds value : {}'.format(tds_Value))
            time.sleep(1)
            
            
class motorThread(threading.Thread):
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
                
                
class ledThread(threading.Thread):
    def __init__(self):
        threading.Thread.__init__(self, name='Led Thread')
        
    def run(self):
        try:
            while 1:
                now = time_now()
                print('now Hour: {}'.format(now))
                #if led_auto == 1:
                led_run(now)
                time.sleep(5)
            #else:
                #print("led_task is manul now")
                    #if led_Value == 1:
                        #GPIO.output(4, False)
                    #else:
                        #GPIO.output(4, True)
                        #time.sleep(0.1)
        except KeyboardInterrupt:
            print("Keyboard interrupt")
            GPIO.cleanup() # cleanup all GPIO
            
    
def main():
    motor_setup()
    led_setup()
    
    global tds_Value
    
    t1 = tdsThread()
    t1.start()
    t2 = motorThread()
    t2.start()
    t3 = ledThread()
    t3.start()
    
    
if __name__ == '__main__':
    main()