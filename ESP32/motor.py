import time
import RPi.GPIO as GPIO

def motor_setup():
    GPIO.setmode(GPIO.BCM)
    GPIO.setup(6, GPIO.OUT) ##GPIO 6 motor OUTPUT
    GPIO.setup(7, GPIO.OUT) ##GPIO 7 motor OUTPUT
    GPIO.setup(8, GPIO.OUT) ##GPIO 8 motor OUTPUT

def motor_6run(on_time):
    GPIO.output(6, False)
    print('motor 6 ON!!!!!')
    time.sleep(on_time)
    GPIO.output(6, True)
    print('motor 6 OFF')

def motor_7run(on_time):
    GPIO.output(7, False)
    print('motor 7 ON!!!!!')
    time.sleep(on_time)
    GPIO.output(7, True)
    print('motor 7 OFF')

def motor_8run(on_time):
    GPIO.output(8, False)
    print('motor 8 ON!!!!!')
    time.sleep(on_time)
    GPIO.output(8, True)
    print('motor 8 OFF')
    
if __name__ == '__main__':
    GPIO.cleanup()
    motor_setup()
    motor_6run(1)
    time.sleep(1)
    motor_7run(1)
    time.sleep(1)
    motor_8run(1)
    time.sleep(1)
    