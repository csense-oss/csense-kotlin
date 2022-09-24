package csense.kotlin.patterns.expected

import csense.kotlin.*


public inline fun <Value, Error> Expected<Value, Error>.valueOrFailed(
    onFailed: Expected.Failed<Error>.() -> Nothing
): Value = when (this) {
    is Expected.Success -> value
    is Expected.Failed -> onFailed(this)
}


@Deprecated(
    level = DeprecationLevel.ERROR, message = "Success will never call onFailed",
    replaceWith = ReplaceWith("")
)
@Suppress("MissingTestFunction", "UnusedReceiverParameter")
public fun Expected.Success<*>.valueOrFailed(
    onFailed: Expected.Failed<Error>.() -> Nothing
): Boolean = unexpected()

@Deprecated(
    level = DeprecationLevel.ERROR, message = "Failed will always call onFailed",
    replaceWith = ReplaceWith("onFailed")
)
@Suppress("MissingTestFunction", "UnusedReceiverParameter")
public fun Expected.Failed<*>.valueOrFailed(
    onFailed: Expected.Failed<Error>.() -> Nothing
): Boolean = unexpected()
