# Expected

Purpose of this pattern

``
To facilitate an expected result (success / error) type that is not restricted and is easy to work with. (simple, conceice, does not polute global namespace)
``

The Result type in kotlin's std. lib requires error to be a throwable, which is not very handy (especially since kotlin
tries to avoid exceptions...).

Thus, the user is left with 2 options:

- either roll their own or (if even possible) use some libraries.
- exceptions for error states (which might be inefficient).

This implementation provides the user(you) with a simple solution that allows for any error types, and provides as much
convenience as possible.

**Exceptions**
If the name contains "*catching*" then exception would be caught (if any) otherwise they would just be "thrown" normally.

## How to 

### Create an Expected
```kotlin
private fun callService(): Expected<String, Boolean> = expected {
    //success type
    "".asSuccess()
    //failed type
    false.asFailed()    
}
```

### How to use an Expected
The following short code will be "used" in the following paragraphs
```kotlin
enum class ServiceError{
    BadInput, 
    BadToken, 
    Unknown
}
fun getServiceResult(): Expected<String, ServiceError> = expected {
    //whatever you like.
}
```

### Fail fast
Regular way to "bail" out.
```kotlin
fun failFast(){
    val successValue: String = getServiceResult().valueOrOnFailed { it: ServiceError ->
        return@failFast
    }    
}
```
If you want the ExpectedFailed 
```kotlin
fun failFast(){
    val successValue: String = getServiceResult().valueOrExpectedFailed { //this: ExpectedFailed<ServiceError>
        return@failFast
    }    
}
```

### Map
Converts a success, or passes the error along.
```kotlin
fun map(){
    val result: Expected<Int, ServiceError> = getServiceResult().map {
        it.length
    }
}
```

### Recover
Converts an Error to a success 
```kotlin
fun recover(){
    val success: ExpectedSuccess<String> = getServiceResult().recover {
        "its a really nice error: $it"
    }
}
```



### Create / use an "always" successful expected
```kotlin
val always42 = expected { 42.asSuccess() }
// since the error is nothing it can ONLY be a success, thus the value is directly exposed.
always42.value
```

### Create / use an "always" failed expected
```kotlin
val always42 = expected { 42.asFailed() }
// since the Value is nothing it can ONLY be a failed, thus the error is directly exposed.
always42.error
```


## Examples

### custom Error type (enum/sealed class)

```kotlin
enum class ServiceError {
    NotFound,
    NotAuthorized
}

class MyService {
    fun callAServiceThatAlwaysFails(): Expected<ServiceError, String> = expected {
        ServiceError.NotAuthorized.asFailed()
    }
}

class UsageSite {
    fun useService(service: MyService): String? {
        val result = service.callAServiceThatAlwaysFails()
        when (result) {
            is ExpectedFailed -> return null
            is ExpectedSuccess -> return value
        }
        return result.valueOrOnFailed { return null }
    }
}

```