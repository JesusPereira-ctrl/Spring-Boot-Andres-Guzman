# Hibernate Introduction

It is an object-relational mapping (ORM) tool that allows you to work with data from a relational database (RDBMS) in the form of classes and objects (object-oriented programming) for Java.

## How it works?

```
                            ┌──────────────────────────────────────────────────────────┐
                            │                    Repository / DAO                      │
                            └──────────────────────────────────────────────────────────┘
                                        │                              │
                                        ▼                              ▼
                            ┌───────────────────────┐      ┌───────────────────────────┐
EntityManager Object <----- │ Java Persistence API  │      │  Hibernate Native API     │ ----> Session Object
                            │        (JPA)          │      │                           │
                            └───────────────────────┘      └───────────────────────────┘
                                         │                              │
                                         └──────────────┬───────────────┘
                                                        ▼
                            ┌──────────────────────────────────────────────────────────┐
                            │                        Hibernate                         │
                            └──────────────────────────────────────────────────────────┘
                                                         │
                                                         ▼
                            ┌──────────────────────────────────────────────────────────┐
                            │                          JDBC                            │
                            └──────────────────────────────────────────────────────────┘
                                                       │  ▲
                                                       ▼  │
                                                ┌──────────────────┐
                                                │     Database     │
                                                └──────────────────┘
```

Every query or operation passes through the entity objects

Remember JPA is a standard or an specification and Hibernate is an implementation

## Types of queries

- Hibernate Query Language (HQL)

- Criteria API

- Native SQL

## Example Entity Class

```java
@Entity
public class Client {

    @Id
    private Long id;

    private String name;

    private String lastName;

    @Column(name = "payment_method")
    private Date paymentMethod;

    // Getters and Setters
}
```

## Associations

- `@ManyToOne`
- `@OneToMany`
- `@OneToOne`
- `@ManyToMany`
