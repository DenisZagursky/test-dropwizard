# test
Small project for checking libraries for Dropwizard
1. Implemented DAO with Hibernate
1. Implemented DI with Google Guice
1. Implemented Migrations with Liquibase
1. Implemented get queries to database on JOOQ (more useful then working with sql like strings) 
1. Implemented Simple jwt auth (user can`t work with endpoints until he dont login)
1. Implemented 2 endpoints for post project and get project. ( with 2 different styles. 1st- Hibernate DAO, 2nd- JOOQ)


How to start the test application
---
1. use 'docker-compose up -d' to up database
1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/test-com.wizard.formular-1.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`
