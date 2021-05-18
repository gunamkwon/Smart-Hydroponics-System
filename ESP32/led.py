import time
import RPi.GPIO as GPIO

def led_setup():
    GPIO.setmode(GPIO.BCM)
    GPIO.setup(17, GPIO.OUT) # GPIO 17 LED OUTPUT
    
def time_now():
    now= time.localtime()
    #print("%02d:%02d"%(now.tm_hour,now.tm_min))
    if now.tm_hour >= 7 and now.tm_hour < 20:
        return 1;
    else:
        return 0;

def led_run(nowHour):
    if nowHour == 1:
        GPIO.output(17, True)
    else:
        GPIO.output(17, False)

def led_control(ctrl):
    if(ctrl == 1):
        GPIO.output(17, True)
    else:
        GPIO.output(17, False)
    time.sleep(1)
def led_on():
    GPIO.output(17, True)
    
def led_off():
    GPIO.output(17, False)
    
if __name__ == '__main__':
    led_setup()
    while 1:
        auto_time = time_now()
        print(auto_time)
        if(auto_time == 1):
            led_on()
        else:
            led_off()
