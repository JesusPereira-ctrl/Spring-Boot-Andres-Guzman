# JSON (JavaScript Object Notation)

JSON (JavaScript Object Notation) is a text format for representing and transmitting data in a way that can be read by both humans and programs. Although it originated in JavaScript, it is now language-independent and serves as the standard format for REST APIs.

## Example

```json
{
	"id": 1,
	"title": "study Spring Boot",
	"completed": false,
	"priority": 2.5,
	"tags": ["java", "backend"],
	"autor": {
		"nombre": "Ana",
		"email": "ana@example.com"
	},
	"limitDate": null
}
```

## Basic rules

1. Object: enclosed in curly braces { } and contains "key": value pairs.
2. Array: enclosed in square brackets [ ] and contains a list of values.
3. Keys must always be enclosed in double quotes.
4. Values ​​can only be of the following types:
   - text: "hello" (using double quotes, not single quotes)
   - number: 42 or 3.14
   - boolean: true or false
   - null
   - another object or an array
5. Elements are separated by commas; a comma is not permitted after the last element.
6. Comments are not supported.

A very common mistake when starting out is using single quotes or leaving a trailing comma, which causes the JSON to become invalid.

## What is it used for?

When a client (a frontend, a mobile app, or another service) communicates with your API, it needs a common format for exchanging data. JSON serves this purpose:

```
Client  ── POST /todo  {"title": "Study"} ──►  Your API Spring Boot
Client  ◄── 201 Created  {"id": 1, "title": "Study"} ──  Your API Spring Boot
```

The request and the response travel as JSON text, with the header `Content-Type: application/json`.

## How to connect with Spring Boot

Spring Boot includes Jackson, a library that automatically converts between Java objects and JSON:

- Serialization: Java object → JSON (what happens when you return something from a controller).
- Deserialization: JSON → Java object (what happens with @RequestBody).

```java
public record TodoDto(Long id, String title, boolean completed) {}

@RestController
@RequestMapping("/todo")
public class TodoController {

    @PostMapping
    public TodoDto create(@RequestBody TodoDto dto) {
        // Jackson converts the JSON received in a TodoDto
        return dto; // and converts the TodoDto back to JSON
    }
}
```

The JSON field names must match those of the Java object (this can be customized using @JsonProperty). If the JSON is malformed, Spring responds with a 400 Bad Request.
