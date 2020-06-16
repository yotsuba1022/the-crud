# The CRUD
[![Build Status](https://travis-ci.com/yotsuba1022/the-curd.svg?branch=master)](https://travis-ci.com/yotsuba1022/the-curd)
[![Hex.pm](https://img.shields.io/badge/language-java-blue.svg)]()
[![Hex.pm](https://img.shields.io/hexpm/l/plug.svg)]()

The general lifestyle of the web developer. No matter how professional/hard the techniques you used. You're still doing the CRUD, this repository is just a side project which wants to demo the technical diversity of CRUD in Java ecosystem. You can CRUD this way, CRUD that way. It's just the CRUD, which gives us a job and money.

## Modules

This side project contains the following modules:
- User module: Dealing with user related business logic.

## Environment, Technical Stack and Components
- Operating System: macOS (Mojave)
- Container Platform: Docker (18.09.2)
- Development Language: Java (Amazon Corretto - 11.0.3.7.1)
  - [Download Link](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html)
- Web Container: Tomcat (Spring-boot embedded)
- Application Framework: Spring Boot (2.0.5 RELEASE)
- Database:
    - MySQL (8.0.12)
    - H2 (For test purpose only)
- Persistence layer: [MyBatis](http://www.mybatis.org/mybatis-3/) (3.4.6)
- Cryptography: [Jasypt](http://www.jasypt.org/) (2.0.0)
- HTTP request client: [Unirest](https://github.com/Kong/unirest-java) (1.4.9)
- Code Generation: [Lombok](https://projectlombok.org/) (1.16.22)
- Unit Test: [JUnit](https://junit.org/junit5/) (4.12)
- Behavior Driven Development: [Cucumber](https://cucumber.io/) (1.2.5)
- Build Tool: [Maven](https://maven.apache.org/) (3.3.3)
- CI Service: [Travis CI](https://travis-ci.com/)
- API Documentation: [Swagger](https://swagger.io/)

## Setup Commands for Docker Environment
Before executing the commands in this block, please ensure that you already installed docker on your local machine.

1. Clone this repository with the following command:
    ```
    https://github.com/yotsuba1022/the-crud.git
    ``` 
2. Move into the root path of the project you cloned.

3. Execute the following command to build image then start containers.

   3.1. If you want to run containers as usual processes, execute the following command:
    ```
     $ docker-compose up --build
    ```
   3.2. If you want to run containers as daemon processes, execute the following command:
    ```
     $ docker-compose up --build -d
    ```
        
4. Fnish all containers by the following command when you want to shut down all of them:
        
    ```
     $ docker-compose down
    ```

## Setup Commands for Local Environment
Before executing the commands in this block, please ensure that you already installed and launched all the required components in your computer (e.g. MySQL).

1. Compile, build and test the Spring Boot application:
    ```
    $ mvn clean test package -Pintegration
    ```

2. Launch the service:
    ```
    $ mvn spring-boot:run
    ```

## Sample CURL Commands
###### Create User
```
$ curl -v -H "Content-Type:application/json" -X POST "http://localhost:8080/the-crud/api/v1/users" -d '{"username":"rabido1022","password":"!QAZ2wsx","firstName":"Rabido","lastName":"JOJO","birthday":"1988-10-22T09:15:01","age":30,"gender":"MALE","registrationDate":"2019-01-03T23:15:01","test":false,"admin":true,"suspended":false,"vip":true}' | python -m json.tool
```
###### Update User
```
$ curl -v -H "Content-Type:application/json" -X PUT "http://localhost:8080/the-crud/api/v1/users/26" -d '{"firstName":"JOJO", "lastName":"Lue", "birthday":"1988-10-22T23:15:15", "age":30, "gender":"MALE", "admin":true, "vip":true, "test":true, "suspended":true}' | python -m json.tool
```
###### Get User
```
$ curl -v -X GET "http://localhost:8080/the-crud/api/v1/users/1" | python -m json.tool
```
###### Query Users
```
$ curl -v -X GET "http://localhost:8080/the-crud/api/v1/users?id=1" | python -m json.tool
$ curl -v -X GET "http://localhost:8080/the-crud/api/v1/users?isVip=true&firstName=Rabido" | python -m json.tool
```
###### Delete User
```
$ curl -v -X DELETE "http://localhost:8080/the-crud/api/v1/users/23" | python -m json.tool
```

## Swagger UI Links
- [Default Link](http://localhost:8080/the-crud/swagger-ui.html#/)
- [Customized Link](http://localhost:8080/the-crud/docs)
