import time
import RPi.GPIO as GPIO

def led_setup():
    GPIO.setmode(GPIO.BCM)
    GPIO.setup(4, GPIO.OUT) ##GPIO 4 LED OUTPUT
    
def time_now():
    now= time.localtime()
    #print("%02d:%02d"%(now.tm_hour,now.tm_min))
    if now.tm_hour >= 7 and now.tm_hour < 20:
        return 1;
    else:
        return 0;

def led_run(nowHour):
    #if now.tm_hour>=7 and now.tm_hour<20: # turn on 7:00 to 20:00
    if nowHour == 1:
        GPIO.output(4, False)
        print('led on')
        time.sleep(5)
        
    else:
        GPIO.output(4, True)
        print('led off')
        time.sleep(5)
    
    
if __name__ == '__main__':
    led_setup()
    now = time_now()
    print('{}'.format(now))
    while 1:
        led_run(now)