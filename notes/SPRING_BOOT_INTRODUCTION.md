# Spring Boot Introduction

## What is Spring Boot?

Spring Boot is a project of the Spring ecosystem, built on top of Spring Framework,
that simplifies creating production-ready Java applications.

- Starters: grouped dependencies (e.g. spring-boot-starter-web).
- Auto-configuration based on the classpath ("convention over configuration").
- Dependency versions managed automatically (spring-boot-starter-parent / BOM).
- Embedded server: Tomcat by default (via Spring Web). No WAR required.
- Executable JARs, run with `java -jar`.
- DevTools: automatic restart during development.
- Actuator: health checks, metrics and monitoring for production.
- Build with Maven or Gradle (chosen at start.spring.io; includes the wrapper).
- Externalized configuration: application.yml, profiles, environment variables.

## Ecosystem (separate modules, added as needed)

- Spring Data JPA / MongoDB: data access
- Spring Security: authentication and authorization
- spring-boot-starter-test: JUnit, Mockito, etc.
- Spring Cloud: tools for distributed systems / microservices
- Spring WebFlux: reactive programming

## Summary

Spring Framework (created by Rod Johnson, now maintained by the Spring team at Broadcom)
provides IoC, MVC and data access. Spring Boot builds on it and automates setup through
starters and auto-configuration.