# DTO (Data Transfer Object)

A DTO (Data Transfer Object) is a simple object whose sole purpose is to transport data between layers or systems. It contains no business logic—only fields and their corresponding getters/setters (or a `record` in modern Java).

## The problem it solves

Imagine that you have this JPA entity:

```java
@Entity
public class User {

    @Id @GeneratedValue
    private Long id;

    private String name;

    private String email;

    private String password;      // hash

    private boolean active;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;
}
```

If you return this entity directly from your controller, several problems arise:

1. You expose sensitive data: the password is included in the JSON.
2. You couple your API to your database: changing a column breaks all clients.
3. Issues with JPA: lazy-loaded relationships (LazyInitializationException) and circular references during serialization.
4. Mass assignment: if you receive the entity as an `@RequestBody`, the client could send fields like `"active": true` or an ID that they shouldn't be able to control.
5. Overhead: you send fields that the client does not need.

## The solution: one DTO per use case

```java
// What the client send to create
public record CreateUserRequest(
    @NotBlank String name,
    @Email String email,
    @Size(min = 8) String password
) {}

// What the client received
public record UserResponse(
    Long id,
    String name,
    String email
) {}
```

The client never sees or controls the entity. You decide what goes in and what comes out.

## Key benefits

1. Security: You control exactly which data is exposed and which is accepted.
2. Decoupling: You can change the database model without breaking the API contract.
3. Specific validation: Each input DTO can have its own rules (e.g., @NotBlank, @Email).
4. Versioning: You can have `UserResponseV1` and `UserResponseV2` without modifying the entity.
5. Performance: You send only what is necessary, avoiding the loading of unnecessary relationships.

## Best practices

1. Use `record` for DTOs: they are immutable and concise (Java 16+).
2. Separate input and output DTOs (Request/Response); it is rarely a good idea to reuse one for both.
3. Do not include business logic in DTOs—only declarative validations.
4. Do not return JPA entities from the controller, not even "for now."
5. Perform mapping in the service layer or by using a mapper, not in the controller.

## When is it excessive?

In a very small CRUD application or a prototype, some teams skip DTOs to move faster. This is a valid decision only if the entity is trivial and contains no sensitive data; however, as the project grows, the cost of refactoring often ends up being higher than it would have been to use them from the start.
