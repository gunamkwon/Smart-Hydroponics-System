import RPi.GPIO as GPIO
import time

GPIO.setmode(GPIO.BCM) # Broadcom pin-numbering scheme

GPIO.setup(4, GPIO.OUT) # output rf
# Initial state for LEDs:
print("Testing RF out, Press CTRL+C to exit")

try:
    while 1:
        print("set GPIO high")
        GPIO.output(4, False)
        time.sleep(3)

except KeyboardInterrupt: # If CTRL+C is pressed, exit cleanly:
    print("Keyboard interrupt")
    print("clean up") 
    GPIO.cleanup() # cleanup all GPIO