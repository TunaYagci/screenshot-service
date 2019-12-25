# Screenshot as a Service
This project is called **Screenshot Service** build with the help of mostly **Spring Cloud**, a.k.a **Netflix OSS**.  

### There are 5 microservices:  
* [**Scan**](https://hub.docker.com/repository/docker/tunayagci/scan-service): Responsible for starting a screenshot request by talking to Kafka.
* [**Image**](https://hub.docker.com/repository/docker/tunayagci/image-service): This service is responsible for handling&serving images throgh a REST API.
* [**Status**](https://hub.docker.com/repository/docker/tunayagci/status-service): This service is responsible for result and status of the screenshots. It serves some REST endpoins documented below.
* [**Screenshot Consumers**](https://hub.docker.com/repository/docker/tunayagci/screenshot-consumers): These are the **Kafka** consumers that responsible with taking screenshot and forward the image to **image-service**.
* [**Chrome**](https://hub.docker.com/r/robcherry/docker-chromedriver/): This is the WebDriver who is responsible for actually taking the screesnhot with [chromedriver](https://chromedriver.chromium.org/). 


### Other external services:
* [**Eureka**](https://hub.docker.com/repository/docker/tunayagci/eureka-server): Spring Cloud Eureka is used for service discovery for communication of above services.
* [**Zuul Proxy**](https://hub.docker.com/repository/docker/tunayagci/zuul-api-gateway): Netflix Zuul is used as an **API Gateway** for these microservices. It is also responsible of handling **server side load balancing**.
* **PostgreSQL**: Used by **image-service** and **status-service** for holding data. It is especially used for storing images, inside the **image-service** itself.
* **Kafka**: Used as an **event bus** to establish a clean **CQRS/ES** pattern.

### REST API endpoints: 

| **Service** | **Endpoint**                                     | **Method** | **Parameters**                                        | **Description**                                                                                                                                   |
|---------|----------------------------------------------|--------|---------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------|
| Scan    | /scan-service/scan                           | POST   | urls: A list of urls                              | Start the screenshot request with a list of urls.                                                                                             |
| Status  | /status-service/status                       | GET    | scanId: The unique id provided with the API above | Returns the latest status of the scan request.                                                                                                |
| Status  | /status-service/status/result                | GET    | scanId: The unique id provided with the API above | Returns the result of the scan. As the result is updated through screenshot requests come, use the above API to ensure the results are final. |
| Image   | /image-service/image/{scanId}/{screenshotId} | GET    | scanId, screenshotId.                             | Serves the image of the provided url.                                                                                                         |

## Installation

The JAVA source classes must be built either locally or images with jars can be provided through Docker Hub.
Images in Docker Hub contains **fat jars**.

### Build locally

1. Make sure you have installed **Maven 3+** and it is in your PATH variables. You can test it as ```mvn -version```
1. Clone the repository
1. ```cd /deployment```
1. ```sh build-all.sh```
1. ```docker-compose -f docker-compose-local.yml up -d``` 

### Or build from images

1. Make sure you have at least 6G of RAM
1. Install Docker [(configure hardware settings)](https://docs.docker.com/docker-for-mac/)
1. Clone the repository
1. ``` cd /deployment ```
1. ```docker-compose up -d ```

* Each Java container runs with a 1G of `-Xmx1g` enabled.
* Low memory can create serious problems as timeout issues.

## Local development

(If you want to run the services locally in your IDEA)

1. You still need Docker to run Kafka and Chrome services.
1. `cd /dev`
1. `docker-compose up -d`
1. `cd /deployment`
1. `sh build-all.sh`
1. Run every service (except for `event-registry`) as:   
```java
java -jar -Dspring.profiles.active=dev /dist/app.jar
```

## Architecture

Here is a brief overview of what's going on, which service talks with each other, etc:
![arch](/arch-screenshot.png)

* Databases aren't drawn here but there are 2 separate PostgreSQL database for **image-service** and **status-service**.
* Each REST call has to go through **zuul-proxy**.

### Scaling

* Any service except for the **eureka**, **zuul** and **image-service** can be scaled as a cluster and already configured to do so.

## Usage 

There is a **python** client in the repository you can find under `/client`.
This client starts a scan, polls the status every 1 second and then shows the result a **JSON**.
Steps to use the python client:  

1. Install `python 2.7+` and `pip` and make sure they on the `$PATH`
1. Clone the repository
1. `pip install requests` 
1. Run `client.py` with URL parameters, such as:  
```bash
python client.py [URLS]
```
an example:  
```bash
python client.py https://www.gmail.com https://www.ign.com
```
result:
```python
/usr/bin/python2.7 /Users/eko/IdeaProjects/screenshot/client/client.py https://www.gmail.com https://www.ign.com qq
Created a scan with id: 8b1f0c26-8bd3-4f35-b905-ff0ab228ccf3
Polling for status....
STARTED
STARTED
STARTED
...
PARTIAL_COMPLETED
PARTIAL_COMPLETED
PARTIAL_COMPLETED
...
COMPLETED
{u'creationTime': u'2019-12-25T14:24:09.825+0000',
 u'id': 1,
 u'scanResults': [{u'creationTime': u'2019-12-25T14:24:11.582+0000',
                   u'id': 2,
                   u'imageRef': u'http://localhost:8765/image-service/image/8b1f0c26-8bd3-4f35-b905-ff0ab228ccf3/1',
                   u'message': None,
                   u'scanStatus': u'COMPLETED',
                   u'url': u'https://www.gmail.com'},
                  {u'creationTime': u'2019-12-25T14:24:11.614+0000',
                   u'id': 3,
                   u'imageRef': u'http://localhost:8765/image-service/image/8b1f0c26-8bd3-4f35-b905-ff0ab228ccf3/2',
                   u'message': None,
                   u'scanStatus': u'COMPLETED',
                   u'url': u'https://www.ign.com'},
                  {u'creationTime': u'2019-12-25T14:24:11.653+0000',
                   u'id': 4,
                   u'imageRef': None,
                   u'message': u'no protocol: qq',
                   u'scanStatus': u'FAILED',
                   u'url': u'qq'}]
```

## Known Problems

#### `Hystrix timeout` when I send any request

This is a known problem by [Netflix Zuul team](https://github.com/spring-cloud/spring-cloud-netflix/issues/2606).
It usually occurs on the **first request** to each route.

#### Docker and service hangs on the request

Most problems are occurring because of low memory on all containers.
As stated above, containers require at least 1G of memory.
To configure memory on **Docker for Mac**, see https://docs.docker.com/docker-for-mac/
6GB memory with 2GB swap can be considered okay with no clusters.
