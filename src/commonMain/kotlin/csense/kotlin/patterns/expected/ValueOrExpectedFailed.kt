package csense.kotlin.patterns.expected

import csense.kotlin.*
import kotlin.contracts.*


public inline fun <Value, Error> Expected<Value, Error>.valueOrOnExpectedFailed(
    onFailed: (Expected.Failed<Error>) -> Nothing
): Value {
    contract {
        returns() implies (this@valueOrOnExpectedFailed is Expected.Success)
        callsInPlace(onFailed, InvocationKind.AT_MOST_ONCE)
    }
    return when (this) {
        is Expected.Success -> value
        is Expected.Failed -> onFailed(this)
    }
}

@Deprecated(
    level = DeprecationLevel.ERROR, message = "Success will never call onFailed",
    replaceWith = ReplaceWith("")
)
@Suppress("MissingTestFunction", "UnusedReceiverParameter", "UNUSED_PARAMETER")
public fun Expected.Success<*>.valueOrOnExpectedFailed(
    onFailed: (Expected.Failed<Error>) -> Nothing
): Boolean = unexpected()

@Deprecated(
    level = DeprecationLevel.ERROR, message = "Failed will always call onFailed",
    replaceWith = ReplaceWith("onFailed")
)
@Suppress("MissingTestFunction", "UnusedReceiverParameter", "UNUSED_PARAMETER")
public fun Expected.Failed<*>.valueOrOnExpectedFailed(
    onFailed: (Expected.Failed<Error>) -> Nothing
): Boolean = unexpected()
