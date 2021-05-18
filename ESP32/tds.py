import math
import sys
import time
from grove.adc import ADC
 
class GroveTDS:
 
    def __init__(self, channel):
        self.channel = channel
        self.adc = ADC()
 
    @property
    def TDS(self):
        value = self.adc.read(self.channel)
        if value != 0:
            voltage = value*5/1024.0
            tdsValue = (133.42/voltage*voltage*voltage-255.86*voltage*voltage+857.39*voltage)#*0.5
            return tdsValue
        else:
            return 0
 
Grove = GroveTDS
 
def get_tds():
    sensor = GroveTDS(0)
    tds_Value = sensor.TDS
    return tds_Value
    
 
if __name__ == '__main__':
    while 1:
        tds_Value = get_tds()
        print('tds value : {}'.format(tds_Value))
        time.sleep(1)
