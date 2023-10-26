# Bob Backend API Server
## Description
This is the API server for the Bob project. <br/>
Bob can customize the taste and quantity of food. And Bob can donate the money saved to the charity. <br/>

## Spec
- JAVA 17
- Spring Boot 3.1.5
- Spring Cloud 2022.0.4
- Spring Cloud Netflix Eureka Server
- Spring Cloud Gateway
- Spring Cloud Stream Kafka Binder 3.1.5
- H2 Database : PostgreSQL
- Open API Swagger 3.0.0

## API Spec
### Auth Service
- Create Token
- Refresh Token
### Member Service
- Sign Up
- Get Member
- Sign In
### Shop Service
- Get Shop List
- Get Shop Menu List
### Order Service
- Create Receipt
- Get Order Menu History List
- Get Order Menu Detail
### Payment Service
- Pay
- Get Payment History List
- Get Payment History Detail
### Delivery Service
- Get Delivery Status
- Get Delivery History List

## Sequence Diagram
### [Auth Service](https://github.com/MuscatLab/bob-backend/tree/main/auth-service#sequence-diagram)
1. [Create Token](https://github.com/MuscatLab/bob-backend/tree/main/auth-service#1-create-auth-token)
2. [Refresh Token](https://github.com/MuscatLab/bob-backend/tree/main/auth-service#2-refresh-auth-token)
### [Member Service](https://github.com/MuscatLab/bob-backend/tree/main/member-service#sequence-diagram)
1. [Sign Up](https://github.com/MuscatLab/bob-backend/tree/main/auth-service#2-refresh-auth-token)
2. [Get Member](https://github.com/MuscatLab/bob-backend/tree/main/auth-service#2-refresh-auth-token)
3. [Sign In](https://github.com/MuscatLab/bob-backend/tree/main/member-service#3-sign-in)

## API Architecture
```mermaid
graph LR
GW[Gateway Service] --- AS[Auth Service]
GW --- ES[Eureka Server]
GW --- MS[Member Service]
GW --- SS[Shop Service]
GW --- OS[Order Service]
GW --- PS[Payment Service]
GW --- DS[Delivery Service]
ES -.- AS
ES -.- MS
ES -.- SS
ES -.- OS
ES -.- PS
ES -.- DS
    subgraph Kafka
        AS
        MS
        SS
        OS
        PS
        DS
    end
Kafka --- DB[H2 : Postgresql]
```

## How to run
### Run with Application
```shell
$ docker-compose up -d
```