# arithmetic-services 

## Usage
* Run all the services.
* Connect to the application through API-GATEWAY using Postman on port 8765.
## API Endpoints
| HTTP Verbs | Endpoints | Action |
| --- | --- | --- |
| POST | /api/v1/auth/register | To register a new user |
| POST | /api/v1/auth/authenticate | To authenticate an existing user |
| GET | /users/view-all | To retrieve all users |
| GET | /users/view/{id} | To retrieve a user by id |
| PUT | /users/update/{id} | To update a user by id |
| DELETE | /users/delete/{id} | To delete a user by id |
| GET | /arithmetic/{operation}/{leftOPpd}/{rightOpd} | To perform arithmetic operations such as addition, subtraction, multiply and division |

## Zipkin
  * To view all the logs at one place. 
  * Note : Download zipkin-server.jar for Java from here :- https://zipkin.io/pages/quickstart.html
  * Then execute the following command : `java -jar zipkin-server.jar`
  
## Eureka Server
  * Exposed at http://localhost:8761/
  
## Technologies
  * Spring Boot 3
  * Spring Security
  * Spring Data JPA
  * Spring Cloud
  * MySQL Database
  * Micrometer Tracing
  * JWT Authentication
  * JUnit testing with Mockito
