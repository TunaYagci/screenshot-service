import requests
import time
import pprint
import sys


arguments = sys.argv

if len(arguments) < 2:
    raise Exception('Client needs a url to start')

apiGatewayURL = "http://localhost:8765"
scanServiceURL = "/scan-service"
statusServiceURL = "/status-service"

# initiate the scan with urls
data = {'urls': arguments[1:]}
r = requests.post(url=apiGatewayURL + scanServiceURL + "/scan", data=data)
response = r.json()
scanId = response['scanID']
print ("Created a scan with id: " + scanId)
print ("Polling for status....")

status = None
while 1:
    r = requests.get(url=apiGatewayURL + statusServiceURL + "/status", params={'scanId': scanId})
    status = r.json()
    print(status)
    if status == 'COMPLETED' or status == 'FAILED':
        break
    time.sleep(1)

if status:
    r = requests.get(url=apiGatewayURL + statusServiceURL + "/status/result", params={'scanId': scanId})
    json = r.json()
    pprint.pprint(json)
