**Microservices using Netfix stack and Spring Boot 2.X Framework base example**

## Base services

1. Config service. Cloud configuration for all services.
2. Registry service. Eureka service, which know about all the services.
3. Gateway service. Zuul service, which balance the all request between services communication.
4. Proxy service. Service, which sends the requests between services.
5. Auth service. OAuth2 service, which add secure for all services.
6. Data service. Service, which responsible to work with the database.
7. API service. Service, which contain all business logic and communicate with the database.

---

## Run the example.

Just start the services step by step.

1. Config service.
2. Registry service.
3. Gateway service.
4. Proxy service.
5. Auth service.
6. Data service.
7. API service.

---

## Test the services.

First, you just to know, all the requests is secured. And you will need to get the token.
Auth service can get it. But you should call it using gateway:
1. POST http://localhost:4001/uaa/oauth/token
Gateway service will redirect your request to OAuth2 service and return the token.
Here is the body data for successfull token getting:
client_id:api-service
username:admin
password:admin
client_secret:Gh3d4HasJd
grant_type:password 
2. After getting the token you can call the controllers form API service.
Just Add to each request the header:
 - Authorization: Bearer token
Example:
- GET http://localhost:4001/data/account/all

