# Dependency Injection and IoC in Spring

## The problem they solve

Without IoC, a class creates its own dependencies:

```java
public class OrderService {
    private final PaymentGateway gateway = new StripePaymentGateway(); // strong coupling

    public void checkout(Order order) {
        gateway.charge(order.getTotal());
    }
}
```

This presents several problems:

- `OrderService` is aware of the concrete implementation (`Stripe`).
- You cannot test it without calling the actual Stripe service.
- Switching providers requires modifying the class.
- If`StripePaymentGateway` requires its own dependencies, `OrderService` must also construct them.

## IoC vs. DI

1. IoC (Inversion of Control) is the principle: your code no longer controls how objects are created and connected; instead, that responsibility is inverted—handed over to a framework or container.
2. DI (Dependency Injection) is the technique Spring uses to implement IoC: the container provides you with dependencies that are already constructed.

```java
@Service
public class OrderService {
    private final PaymentGateway gateway; // it depends on an abstraction

    public OrderService(PaymentGateway gateway) { // Spring injects it
        this.gateway = gateway;
    }
}
```

Now, OrderService neither knows nor cares which implementation it receives.

## How Spring handles this: the container

At its core is the ApplicationContext (which extends BeanFactory). It is the IoC container that creates, configures, and manages the lifecycle of beans—which are simply objects managed by Spring.

## Startup Flow (`SpringApplication.run(...)`)

1. Scanning: @SpringBootApplication includes @ComponentScan, which searches for annotated classes (`@Component`, `@Service`, `@Repository`, `@Controller`, `@Configuration`) within the base package and its subpackage.
2. Registration of `BeanDefinitions`: For each class (and each @Bean method) discovered, Spring stores a definition (class, scope, dependencies, etc.). No actual objects exist yet.
3. Dependency Graph Resolution: Spring determines which beans require others and the order in which they should be created.
4. Instantiation and Injection: Beans are created (using reflection), with dependencies injected via the constructor.
5. Post-processing:`BeanPostProcessors` come into play here—for instance, by creating the proxies that enable features like `@Transactional`, `@Async`, `@Cacheable`, etc.
6. Initialization Callbacks: `@PostConstruct`, `InitializingBean`, etc.
7. Bean is ready for use. Upon closing the context, `@PreDestroy` and similar mechanisms are executed.

## Ways to declare beans

Using stereotypes (your own classes):

```java
@Service
public class OrderService { ... }
```

With `@Bean` (for third-party classes or those requiring special configuration, such as AWS SDKs):

```java
@Configuration
public class AwsConfig {

    @Bean
    public S3Client s3Client(@Value("${aws.region}") String region) {
        return S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();
    }
}
```

You cannot annotate the S3Client class with `@Service` because it is not yours, so `@Bean` is the right tool.

## Type of injections

| Type        |           Example           |                 Recommendation |
| :---------- | :-------------------------: | -----------------------------: |
| Constructor |       `public A(B c)`       |                      Preferred |
| Setter      | `@Autowired void setB(B b)` | Only for opcional dependencies |
| Field       |  `@Autowired private B b;`  |                          Avoid |

## Why use a constructor?

1. It allows for `final` fields (immutability).
2. Dependencies are explicit and mandatory: you cannot create a "half-initialized" object.
3. You can instantiate it in tests—e.g., `new OrderService(mockGateway)`—without needing Spring.
4. With a single constructor, `@Autowired` is unnecessary.

With Lombok, it’s even cleaner:

```java
@Service
@RequiredArgsConstructor
public class OrderService {
    private final PaymentGateway gateway;
    private final OrderRepository repository;
}
```

## When there is more than one implementation

If there are two beans of the same type, Spring throws a `NoUniqueBeanDefinitionException`. Solutions:

```java
@Primary // default option
@Service
public class StripeGateway implements PaymentGateway { ... }

@Service("paypal")
public class PaypalGateway implements PaymentGateway { ... }
```

```java
// Choice a specific option
public OrderService(@Qualifier("paypal") PaymentGateway gateway) { ... }

// or inject alls
public OrderService(List<PaymentGateway> gateways) { ... }
public OrderService(Map<String, PaymentGateway> gatewaysByName) { ... }
```

## Scopes

1. `singleton` (default): a single instance per container. For this reason, your beans must be stateless (without shared mutable state), otherwise you will encounter concurrency issues.
2. `prototype`: a new instance each time one is requested.
3. `request` / `session`: for web applications.

## Profiles and environments (very useful with AWS)

You can change deployments based on the environment:

```java
@Configuration
public class StorageConfig {

    @Bean
    @Profile("local")
    public S3Client localS3() {
        return S3Client.builder()
                .endpointOverride(URI.create("http://localhost:4566")) // LocalStack
                .region(Region.US_EAST_1)
                .build();
    }

    @Bean
    @Profile("prod")
    public S3Client prodS3() {
        return S3Client.create(); // use the IAM Role of ECS/EKS/EC2
    }
}
```

The rest of your code just receives an `S3Client` and doesn't know which environment it's running in. That’s IoC in action.

## Practical benefits

1. Testability: you inject mocks using `@MockBean` / `@Mock` or directly via the constructor.
2. Low coupling: you depend on interfaces, not implementations.
3. Centralized configuration: you can change behavior without touching the business logic.
4. AOP for free: features like transactions, caching, security, and metrics work because Spring controls bean creation and can wrap them in proxies.

## Common Pitfalls

1. Circular dependencies: A needs B, and B needs A. With Spring constructors, this causes the application to fail at startup—which is actually a good thing, as it alerts you to a design flaw. The solution is to redesign (e.g., by extracting a third class), not to use `@Lazy`.
2. Internal calls with `@Transactional`: If you call `this.method()` from within the same class, the proxy is bypassed, and the annotation is not applied.
3. Instantiating a bean using `new`: Calling `new OrderService()` creates an object outside the container, meaning no dependency injections or proxies are applied.
4. Mutable state in singletons: Modifiable instance variables within a `@Service` are a concurrency bug waiting to happen.
