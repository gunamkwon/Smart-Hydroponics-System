import time
import RPi.GPIO as GPIO

def motor_setup():
    GPIO.setmode(GPIO.BCM)
    GPIO.setup(12, GPIO.OUT) # WATER : GPIO 5 motor OUTPUT
    GPIO.setup(6, GPIO.OUT) # TDS1 : GPIO 6 motor OUTPUT
    GPIO.setup(13, GPIO.OUT) # TDS2 : GPIO 13 motor OUTPUT

def motor_12run(on_time):
    GPIO.output(12, True)
    print('motor 12 ON!!!!!')
    time.sleep(on_time)
    GPIO.output(12, False)
    print('motor 12 OFF')


def motor_6run(on_time):
    GPIO.output(6, True)
    print('motor 6 ON!!!!!')
    time.sleep(on_time)
    GPIO.output(6, False)
    print('motor 6 OFF')

def motor_13run(on_time):
    GPIO.output(13, True)
    print('motor 13 ON!!!!!')
    time.sleep(on_time)
    GPIO.output(13, False)
    print('motor 13 OFF')
    
if __name__ == '__main__':
    GPIO.cleanup()
    motor_setup()
    motor_12run(1)
    time.sleep(1)
    motor_6run(1)
    time.sleep(1)
    motor_13run(1)
    time.sleep(1)
    