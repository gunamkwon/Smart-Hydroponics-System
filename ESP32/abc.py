import threading
import time
import math
import sys
from grove.adc import ADC
from tds import *
import RPi.GPIO as GPIO
from motor import *


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
            if tds_Value < 100.0: # when tds value is lower than minimum tds.
                motor_run(3) # turn on motor during 3 sec.
                time.sleep(10) # and wait 10 sec
                
    
def main():
    motor_setup()
    
    global tds_Value
    
    t1 = tdsThread()
    t1.start()
    t2 = motorThread()
    t2.start()
    
    
if __name__ == '__main__':
    main()