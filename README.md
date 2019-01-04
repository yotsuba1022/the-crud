# The CRUD
[![Build Status](https://travis-ci.com/yotsuba1022/the-curd.svg?branch=master)](https://travis-ci.com/yotsuba1022/the-curd)
[![Hex.pm](https://img.shields.io/badge/language-java-blue.svg)]()
[![Hex.pm](https://img.shields.io/hexpm/l/plug.svg)]()

The general lifestyle of the web developer. No matter how professional/hard the techniques you used. You're still doing the CRUD, this repository is just a side project which wants to demo the technical diversity of CRUD in Java ecosystem. You can CRUD this way, CRUD that way. It's just the CRUD, which gives us a job and money.

Trello Board: [Click me](https://trello.com/b/R6dgEl2m/the-crud-storyboard)

## Modules

This side project contains the following modules:
- User module: Dealing with user related business logic.

## Environment, Technical Stack and Components
- Operating System: macOS (Mojave)
- Development Language: Java (Oracle JDK10)
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

## Sample CURL Command
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

## Swagger UI Link
- [Default Link](http://localhost:8080/the-crud/swagger-ui.html#/)
- [Customized Link](http://localhost:8080/the-crud/docs)

## API Design Document
- TBD
