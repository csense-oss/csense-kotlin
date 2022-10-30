package csense.kotlin.patterns.expected.operators

import csense.kotlin.*
import csense.kotlin.patterns.expected.*
import kotlin.contracts.*


public inline fun <Value, Error> Expected<Value, Error>.valueOrOnFailed(
    onFailed: (error: Error) -> Nothing
): Value {
    contract {
        returns() implies (this@valueOrOnFailed is Expected.Success)
    }
    return when (this) {
        is Expected.Success -> value
        is Expected.Failed -> onFailed(error)
    }
}

@Deprecated(
    level = DeprecationLevel.ERROR, message = "Success has a value field instead",
    replaceWith = ReplaceWith("this.value")
)
@Suppress("MissingTestFunction", "UnusedReceiverParameter", "UNUSED_PARAMETER")
public inline fun Expected.Success<*>.valueOrOnFailed(
    onFailed: (error: Error) -> Nothing
): Nothing = unexpected()


@Deprecated(
    level = DeprecationLevel.ERROR, message = "Failed will always run the onFailed argument",
    replaceWith = ReplaceWith("onFailed(error)")
)
@Suppress("MissingTestFunction", "UnusedReceiverParameter", "UNUSED_PARAMETER")
public inline fun Expected.Failed<*>.valueOrOnFailed(
    onFailed: (error: Error) -> Nothing
): Nothing = unexpected()
