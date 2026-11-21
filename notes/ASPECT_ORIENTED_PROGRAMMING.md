# Aspect Oriented Programming (AoP)

## What does an aspect do?

Aspect-oriented programming does an excellent job of making code more reusable, compact, decoupled, and modular.

It handles these tasks, which cut across our code and business logic.

In other words, they are reusable code fragments that intercept a given method to add cross-cutting functionality either before or after it.

It can be used for authentication, authorization, logging, or transactions.

It can be applied to controller methods, services, repositories, DAOs, etc.

## What does the AOP do?

```java
@Controller
public class FormController {

    // Execute functionality before of the method

    @GetMapping("/form")
    public String form(Model model) {
        Usuario usuario = new Usuario();
        ...
        return "form";
    }

    // Execute functionality after of the method
}
```

## Concepts and terminology

- Aspects: An aspect is a modularization of a concern (a task or process) that cuts across multiple objects and methods—essentially an interceptor.

- Join point: A point during the execution of a program—for example, a method invocation or the handling of an exception. In Spring AOP, a join point represents the execution of a method.

- Advice: Code that executes at a specific join point; "after" advice executes after the join point, and "around" advice executes around the join point.

- Point cuts: A point cut is an expression that groups one or more join points. A point cut can be constructed based on complex relationships defined by regular expressions (patterns) to further restrict and refine when an "advice" should be executed.

## What is an "advice"?

- Code that executes at a specific join point and is associated with a point cut expression (similar to regular expressions); it is executed before, after, or around a method that matches the pattern defined in the point cut.

- Point cut expressions can be defined in two ways: either as an expression defined in the same location as the advice, or as a reference to a reusable—or "named"—point cut.

## Types of Advice

- Before Advice: advice that executes before a method invocation.

- After Returning Advice: advice that executes after a method invocation completes successfully—that is, after it has returned a value or object.

- After Throwing Advice: advice that executes if a method throws an exception.

- After (finally) Advice: advice that executes regardless of whether a method completes normally or throws an exception.

- Around Advice: advice that surrounds a method invocation; this is the most powerful type of advice, as it can perform custom tasks both before and after the method invocation.

## How it works

Example with Before Advice

```java
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect // Aspect
@Component
public class ExampleBefore {

    @Before("execution(* com.abc.myapp.dao.*.*(..))") // Point cut expression
    public void checkAccess() { // Advice
        // ..
    }
}
```
