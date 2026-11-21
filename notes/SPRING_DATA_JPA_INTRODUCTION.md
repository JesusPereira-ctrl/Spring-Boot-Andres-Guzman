# Spring Data JPA Introduction

## What is?

Spring Data JPA is a module within the Spring ecosystem that simplifies access to relational databases. It is built on top of JPA (typically using Hibernate under the hood) and aims to eliminate repetitive code—or "boilerplate"—from the persistence layer.

Without Spring Data JPA, you would have to write DAOs using `EntityManager`, craft manual queries, handle transactions, and so on. With Spring Data JPA, you simply declare an interface, and Spring generates the implementation at runtime.

## Advantages and considerations

### Advantages

- Significantly less boilerplate code
- Built-in derived queries, `@Query`, pagination, and auditing
- Full integration with Spring Boot (auto-configuration and transactions)

### Things to watch out for

- N+1 problem: lazy loading (`LAZY`) that triggers excessive queries. This can be resolved using `JOIN FETCH` or `@EntityGraph`.
- Overly long method names: if a query method becomes unreadable, use `@Query`.
- Exposing entities in the API: use DTOs instead of returning entities directly.
- `ddl-auto` in production: use Flyway or Liquibase instead.

## Topics for further exploration

1. Relationships (`@OneToMany`, `@ManyToOne`, `@ManyToMany`)
2. The N+1 problem and `@EntityGraph`
3. Projections (interfaces and DTOs)
4. Auditing (`@CreatedDate`, `@LastModifiedDate`)
5. Specifications (dynamic queries)
6. Migrations with Flyway
