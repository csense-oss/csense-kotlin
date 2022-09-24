package csense.kotlin.patterns.expected

import csense.kotlin.*


public inline fun <Value, Error> Expected<Value, Error>.valueOrOnFailed(
    onFailed: (error: Error) -> Nothing
): Value {
    return when (this) {
        is Expected.Success -> value
        is Expected.Failed -> onFailed(error)
    }
}

@Deprecated(
    level = DeprecationLevel.ERROR, message = "Success has a value field instead",
    replaceWith = ReplaceWith("this.value")
)
@Suppress("MissingTestFunction", "UnusedReceiverParameter")
public inline fun Expected.Success<*>.valueOrOnFailed(
    onFailed: (error: Error) -> Nothing
): Nothing = unexpected()


@Deprecated(
    level = DeprecationLevel.ERROR, message = "Failed will always run the onFailed argument",
    replaceWith = ReplaceWith("onFailed(error)")
)
@Suppress("MissingTestFunction", "UnusedReceiverParameter")
public inline fun Expected.Failed<*>.valueOrOnFailed(
    onFailed: (error: Error) -> Nothing
): Nothing = unexpected()
