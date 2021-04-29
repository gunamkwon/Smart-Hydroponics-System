import time
import grove.gpio as GPIO


def setup(pin):
    GPIO.setmode(GPIO.BCM)
    water_level = GPIO(pin, GPIO.IN)


def check():
    ret = water_level.read()
    if ret == 1:
        return 1
    else:
        return 0

# while문 안에 check() 후 return value에 따라 command and dealy
