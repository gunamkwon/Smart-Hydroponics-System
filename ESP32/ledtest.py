import RPi.GPIO as GPIO
import time

GPIO.setmode(GPIO.BCM) # Broadcom pin-numbering scheme

GPIO.setup(17, GPIO.OUT) # output rf
# Initial state for LEDs:
print("Testing RF out, Press CTRL+C to exit")

try:
    while 1:
        print("set GPIO high")
        GPIO.output(17, False)
        time.sleep(3)

except KeyboardInterrupt: # If CTRL+C is pressed, exit cleanly:
    GPIO.output(17, False)
    print("Keyboard interrupt")
    print("clean up") 
    GPIO.cleanup() # cleanup all GPIO