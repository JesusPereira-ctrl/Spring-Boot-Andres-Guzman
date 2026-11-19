# Interceptors

## What is an interceptor?

An interceptor is a component positioned within the flow of a request to execute logic either before or after the request reaches its final destination (for example, a controller), without that destination needing to be aware of its existence. It is a way to apply the concept of AOP (Aspect-Oriented Programming) to cross-cutting concerns, such as logging, authentication, metrics, auditing, header management, etc.

## In Spring Boot (Spring MVC): HandlerInterceptor

It executes around the controller and provides three hook points:

|      Method       | When it executes                                                   |                                       Typical use                                       |
| :---------------: | :----------------------------------------------------------------- | :-------------------------------------------------------------------------------------: |
|    `preHandle`    | Before the controller                                              | Validating tokens, logging, intercepting/terminating the request (by returning `false`) |
|   `postHandle`    | After the controller, before the view is rendered                  |                              Modifying the `ModelAndView`                               |
| `afterCompletion` | After the entire request is complete (even if an exception occurs) |             Cleaning up resources, measuring execution time, final logging              |

## Interceptor vs Filter (pregunta clásica de entrevista)

|                                           |         Filter (`jakarta.servlet.filter`)          |            Interceptor (`HandlerInterceptor`)             |
| :---------------------------------------: | :------------------------------------------------: | :-------------------------------------------------------: |
|                Belongs to                 |                Servlet / container                 |                        Spring MVC                         |
|                 Executes                  |  Before the request reaches the DispatcherServlet  |    After the DispatcherServlet, before the controller     |
| Access to the handler (controller/method) |                         No                         |           Yes (you can read method annotations)           |
|     Can modify request/response body      |                Yes (using wrappers)                |                          Limited                          |
|                Typical use                | case Security (Spring Security), CORS, compression | Business logging, auditing, endpoint-specific validations |

Execution order: `Filter → DispatcherServlet → Interceptor.preHandle → Controller → Interceptor.postHandle → Interceptor.afterCompletion → Filter`

## Other “interceptors” you might encounter in your stack:

1. `ClientHttpRequestInterceptor`: intercepts outgoing calls made via RestTemplate (useful for adding a token or logging calls to external APIs).
2. `ExchangeFilterFunction`: the equivalent for `WebClient`.
3. Feign `RequestInterceptor`: used to add headers in Feign clients.
4. AWS SDK `ExecutionInterceptor`: intercepts SDK v2 calls to AWS services (S3, SQS, DynamoDB, etc.), for purposes such as tracing or metrics.
5. Aspects (using `@Aspect`): these are not HTTP interceptors, but rather interceptors for method calls (e.g., in services or repositories); however, they are based on the same concept.
