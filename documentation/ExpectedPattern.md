# Expected

Purpose of this pattern

``
To facilitate an expected result (success / error) type that is not restricted and is easy to work with.
``

The Result type in kotlin's std. lib requires error to be a throwable, which is not very handy (especially since kotlin
tries to avoid exceptions...).

Thus, the user is left with 2 options:

- either roll their own or (if even possible) use
- exceptions for error states (which might be inefficient).

This implementation provides the user(you) with a simple solution that allows for any error types, and provides as much
convenience as possible.

## Examples

### custom Error type (enum/sealed class)

```kotlin

import java.security.Provider.Service

enum class ServiceError {
    NotFound,
    NotAuthorized
}

class MyService {
    fun callAServiceThatAlwaysFails(): Expected<ServiceError, String> = expected {
        ServiceError.NotAuthorized.asFailed()
    }
}

class UsageSite{
    fun useService(service: MyService): String?{
        val result = service.callAServiceThatAlwaysFails()
        when(result){
            is ExpectedFailed -> return null
            is ExpectedSuccess -> return value
        }
        return result.valueOrOnFailed{ return null }
    }
}

```