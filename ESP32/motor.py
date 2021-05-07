import time
import RPi.GPIO as GPIO


def motor_setup():
    GPIO.setmode(GPIO.BCM)
    GPIO.setup(4, GPIO.OUT)  # GPIO 4 motor OUTPUT


def motor_run(second, second2):
    GPIO.output(4, False)
    time.sleep(second)
    GPIO.output(4, True)
    time.sleep(second2)
