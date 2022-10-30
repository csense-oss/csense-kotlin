@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.patterns.expected.operators

import csense.kotlin.*
import csense.kotlin.patterns.expected.*
import kotlin.contracts.*


public inline fun <Value> Expected<Value, *>.successValueOrNull(): Value? {
    contract {
        returnsNotNull() implies (this@successValueOrNull is Expected.Success)
    }
    if (this.isFailed()) {
        return null
    }
    return this.value
}


@Deprecated(
    level = DeprecationLevel.ERROR, message = "Success has a value field instead",
    replaceWith = ReplaceWith("this.value")
)
@Suppress("UnusedReceiverParameter")
public inline fun <Value> Expected.Success<Value>.successValueOrNull(): Nothing = unexpected()


@Deprecated(
    level = DeprecationLevel.ERROR, message = "Failed will always become null",
    replaceWith = ReplaceWith("null")
)
@Suppress("UnusedReceiverParameter")
public inline fun <Error> Expected.Failed<Error>.successValueOrNull(): Nothing = unexpected()
