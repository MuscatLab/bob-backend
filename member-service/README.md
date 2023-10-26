# Bob Member Service

## Description

This is the Member Service for the Bob project. It is a Spring Boot application that uses the Spring Cloud Netflix
Eureka
Server designed be hexagonal architecture.

## Spec

- JAVA 17
- Spring Boot 3.1.5
- Spring Cloud 2022.0.4
- Spring Cloud Netflix Eureka Server
- H2 Database : PostgreSQL
- Spring Data JPA
- QueryDSL

## Environment Variables

| Name        | Description                     | Default   |
|-------------|---------------------------------|-----------|
| APP_PORT    | The port the server will run on | 8082      |
| APP_PROFILE | The profile of the server       | local     |
| DB_URL      | The url of the database         | localhost |
| DB_NAME     | The name of the database        | bob       |
| DB_USERNAME | The username of the database    | bob       |
| DB_PASSWORD | The password of the database    | bob       |
| EUREKE_HOST | The host of the eureka server   | localhost |
| EUREKE_PORT | The port of the eureka server   | 8761      |

## Architecture

```mermaid
graph LR
    MC[Member Controller] --> IP[Input]
    MS[Member Service] .-> IP
    MS .-> OP[Output]
    MP[Member Persistence Adapter] --> OP
```

## Sequence Diagram

### 1. Sign Up

```mermaid
sequenceDiagram
    participant UC as User Client
    participant MC as Member Controller
    participant MI as Input Port
    participant MS as Member Service
    participant MO as Output Port
    participant MP as Member Persistence Adapter
    participant RE as Member Repository
    UC ->> MC: /members/sign-up
    MC ->> MS: Create Member Command
    MS ->> MP: Load Member by Email Port
    MP ->> RE: Find Member by Email
    RE -->> MP: Member Entity
    MP -->> MS: Member Entity
    MS -->> MS: Check Member Exists
    MS ->> MP: Create Member Port
    MP ->> RE: Save Member
    RE -->> MP: Member Entity
    MP -->> MS: Member Entity
    MS -->> MC: Member Entity
    MC -->> UC: Sign Up Response
```

### 2. Get Member by Id

```mermaid
sequenceDiagram
    participant UC as User Client
    participant MC as Member Controller
    participant MI as Input Port
    participant MS as Member Service
    participant MO as Output Port
    participant MP as Member Persistence Adapter
    participant RE as Member Repository
    UC ->> MC: /members/{memberId}
    MC ->> MS: Member Id
    MS ->> MP: Load Member by Id Port
    MP ->> RE: Find Member by Id
    RE -->> MP: Member Entity
    MP -->> MS: Member Entity
    MS -->> MC: Member Entity
    MC -->> UC: Member Response
```

### 3. Sign In

```mermaid
sequenceDiagram
    participant UC as User Client
    participant MC as Member Controller
    participant MI as Input Port
    participant MS as Member Service
    participant MO as Output Port
    participant MP as Member Persistence Adapter
    participant RE as Member Repository
    UC ->> MC: /members/sign-in
    MC ->> MS: Validate Password Command
    MS ->> MP: Load Member by Email Port
    MP ->> RE: Find Member by Email
    RE -->> MP: Member Entity
    MP -->> MS: Member Entity
    MS -->> MC: boolean
    MC -->> UC: Sign In Response
```
