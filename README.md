#PizzaMenu REST API

This stack was used on this project:

* Spring Framework
* Spring Boot
* Spring DATA JPA 
* Lombok
* PostgreSQL
* Swagger
* Log4J2

**Features**

* Static Analysis
    - PMD
    - SonarQube (SonarLint IntelliJ IDEA)
    - Checkstyle (Google styles)

* Onion Architecture like

**Pending Features**

* Security
    - RBAC
    - Auditing
* Spring JPA
* Service Stub
* Coverage - Unit Tests
* Swagger documentation 

**Pre-requisites**

* docker v19.03.5+
* docker-compose v1.24.1+
* java 8

**Build**

`./gradlew bootJar`

**Run**

This command builds the images and start the containers and services

`docker-compose up --build`

This command stop the services

`docker-compose stop`

This command starts the services

`docker-compose start`

**Play**

Open http://localhost:8086/swagger-ui.html

**Troubleshoot**

Permission issues when starting, solution:

`sudo rm -r postgres-data`
