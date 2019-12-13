#PizzaMenu REST API

This stack was used on this project:

* Spring Framework
* Spring Boot
* Spring DATA JPA 
* Lombok
* PostgreSQL
* Swagger

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
