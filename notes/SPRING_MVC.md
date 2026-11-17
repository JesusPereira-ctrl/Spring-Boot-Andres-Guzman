# What is Spring MVC?

An MVC-based web framework leverages the following principles:

1. Model, View and Controller
2. Dependency Injection
3. Focused on the use of interfaces
4. Use of POJO classes

## MVC (Model, View and Controller)

```text
User ──► HTTP Request ────► Controller ───► Model ◄──► Database
                                 │            ▲
                                 │ render     │ show
                                 ▼            │
                               View ──────────┘
                                 │
User ◄── HTTP Response ◄─────────┘ (vía Controller)
```

### Components

| Componente        | Responsabilidad                                                           |
| ----------------- | ------------------------------------------------------------------------- |
| **User (Client)** | Send the HTTP request and receive the response                            |
| **Controller**    | Receives the request, coordinates the flow, and returns the HTTP response |
| **Model**         | Represents the data and business logic; communicates with the database    |
| **View**          | Presents the model data to the user (HTML, templates)                     |
| **Database**      | Stores information persistently                                           |

### Step By Step

1. The **user** sends an **HTTP request** to the controller.
2. The **controller** **retrieves and processes** data using the model.
3. The **model** queries or saves information in the database.
4. The controller **renders** the view, passing it the data from the model.
5. The **view** **displays** the information from the model.
6. The controller returns the **HTTP response** to the user.

### In Spring Boot

| Concept MVC          | Equivalent in Spring                                 |
| -------------------- | ---------------------------------------------------- |
| Controller           | `@Controller` / `@RestController`                    |
| Model                | Domain classes, `@Entity`, Services and repositories |
| View                 | Thymeleaf Template (or JSON in a API REST)           |
| Request orchestrator | `DispatcherServlet`                                  |

> In a **REST API**, there is no server-rendered view: the controller returns
> directly as JSON (serialized by Jackson), and the frontend handles displaying it.

## How It Works in Spring?

1. The Front Controller, `DispatcherServlet`, receives an HTTP request from the browser.
2. It then identifies a controller based on the URL (via a "Handler Mapping") and assigns the request to that controller.
3. The controller interacts with business logic components and sends data to the view using the `Model` object.
4. The controller returns or specifies the name of the logical view to be displayed.
5. A `ViewResolver` is selected, which determines the specific type of view to be used (HTML, PDF, Excel, etc.).
6. Finally, the view is presented to the client, incorporating the data from the `Model` object.

## Clear Separation of Responsibilities

1. Controller, validators, form object, DispatcherServlet, HandlerMapping, ViewResolver, etc.
2. They perform a specific task and can be replaced without affecting the others.

## What Does a Controller Do?

1. Controllers provide access to business logic; they delegate business logic to a set of service components.
2. Services, in turn, access databases via a DAO (Data Access Object) interface.
3. Controllers receive parameters from the user (input) and convert them into a model object, populating its attributes with the submitted data, as a result of the business logic.
