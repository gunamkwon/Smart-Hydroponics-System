import time
import RPi.GPIO as GPIO


def liquid_setup():
    GPIO.setmode(GPIO.BCM)
    GPIO.setup(26, GPIO.IN)
    


def check():
    ret = GPIO.input(26)
    if ret == 1:
        return 1
    else:
        return 0

# while문 안에 check() 후 return value에 따라 command and dealy
if __name__ == '__main__':
    liquid_setup()
    while 1:
        ret = check()
        print(ret)
        time.sleep(1)