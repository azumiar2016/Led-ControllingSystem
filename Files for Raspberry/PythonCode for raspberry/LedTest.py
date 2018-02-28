import sys
import time
import datetime
import pigpio
import json
import urllib.request
import socket
#import ast
localhost = socket.gethostbyname(socket.gethostname())
redPin = 18
bluePin = 24
greenPin = 16
while True:
    pi = pigpio.pi()
    url = "http://"+localhost+"/db_get_values.php"
    x = urllib.request.urlopen(url)
    raw_data = x.read()
#print(raw_data)
    encoding = x.info().get_content_charset('utf8')  # JSON default
    data = json.loads(raw_data.decode(encoding))
    print(data)   #this would be your json data
    
# Pin Setup
    pi.set_PWM_dutycycle(redPin,int(data['red']))
    pi.set_PWM_dutycycle(bluePin,int(data['blue']))
    pi.set_PWM_dutycycle(greenPin,int(data['green']))
    time.sleep(2)
    pi.stop()