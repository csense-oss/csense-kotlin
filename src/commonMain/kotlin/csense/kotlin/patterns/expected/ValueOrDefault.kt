@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.patterns.expected

import csense.kotlin.*
import kotlin.contracts.*


public inline fun <Data, Error> Expected<Data, Error>.valueOrDefault(
    default: Data
): Data = when (this) {
    is Expected.Success -> value
    is Expected.Failed -> default
}

public inline fun <Data, Error> Expected<Data, Error>.valueOrDefault(
    defaultLazy: () -> Data
): Data {
    contract {
        callsInPlace(defaultLazy, InvocationKind.AT_MOST_ONCE)
    }
    return when (this) {
        is Expected.Success -> value
        is Expected.Failed -> defaultLazy()
    }
}


@Deprecated(
    level = DeprecationLevel.ERROR, message = "Success has a value field instead",
    replaceWith = ReplaceWith("this.value")
)
@Suppress("MissingTestFunction", "UnusedReceiverParameter", "UNUSED_PARAMETER")
public fun Expected.Success<*>.valueOrDefault(
    default: Any?
): Nothing = unexpected()

@Deprecated(
    level = DeprecationLevel.ERROR, message = "Failed will always use the default argument",
    replaceWith = ReplaceWith("default")
)
@Suppress("MissingTestFunction", "UnusedReceiverParameter", "UNUSED_PARAMETER")
public fun Expected.Failed<*>.valueOrDefault(
    default: Any?
): Nothing = unexpected()


@Deprecated(
    level = DeprecationLevel.ERROR, message = "Success has a value field instead",
    replaceWith = ReplaceWith("this.value")
)
@Suppress("MissingTestFunction", "UnusedReceiverParameter", "UNUSED_PARAMETER")
public fun Expected.Success<*>.valueOrDefault(
    default: () -> Any?
): Nothing = unexpected()

@Deprecated(
    level = DeprecationLevel.ERROR, message = "Failed will always run the default argument",
    replaceWith = ReplaceWith("default")
)
@Suppress("MissingTestFunction", "UnusedReceiverParameter", "UNUSED_PARAMETER")
public fun Expected.Failed<*>.valueOrDefault(
    default: () -> Any?
): Nothing = unexpected()