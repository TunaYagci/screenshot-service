# Screenshot as a Service
This project is called **Screenshot Service** build with the help of mostly **Spring Cloud**, a.k.a **Netflix OSS**.  

### There are 3 microservices:  
* **Scan**: Responsible for starting a screenshot request by talking to Kafka.
* **Image**: This service is responsible for handling&serving images throgh a REST API.
* **Status**: This service is responsible for result and status of the screenshots. It serves some REST endpoins documented below.

### Other external services:
* **Eureka**: Spring Cloud Eureka is used for service discovery for communication of above services.
* **Zuul Proxy**: Netflix Zuul is used as an **API Gateway** for these microservices. It is also responsible of handling **server side load balancing**.
* **PostgreSQL**: Used by **image-service** and **status-service** for holding data. It is especially used for storing images, inside the **image-service** itself.
* **Kafka**: Used as an **event bus** to establish a clean **CQRS/ES** pattern.
* **Chrome**: Google Chrome and chromedriver is loaded as another microservice for handling screenshot capturing.

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

1. Clone the repository
1. ``` cd /deployment ```
1. ```docker-compose up -d ```

## Architecture
TODO.

## Usage 
Usage with python client. TODO.
