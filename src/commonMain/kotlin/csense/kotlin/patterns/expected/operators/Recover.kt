package csense.kotlin.patterns.expected.operators

import csense.kotlin.general.*
import csense.kotlin.patterns.expected.*
import kotlin.contracts.*
import kotlin.jvm.*


public inline fun <Value, Error> Expected<Value, Error>.recover(
    transform: (Error) -> Value
): Expected.Success<Value> {
    contract { callsInPlace(transform, InvocationKind.AT_MOST_ONCE) }
    return when (this) {
        is Expected.Success -> this
        is Expected.Failed -> recover(transform)
    }
}


public inline fun <Value, Error> Expected.Failed<Error>.recover(
    transform: (Error) -> Value
): Expected.Success<Value> {
    contract { callsInPlace(transform, InvocationKind.EXACTLY_ONCE) }
    return Expected.Success(transform(this.error))
}


@Deprecated(
    level = DeprecationLevel.ERROR, message = "If you already know its a success result you should not recover it.",
    replaceWith = ReplaceWith("this")
)
@Suppress("UNUSED_PARAMETER", "MissingTestFunction", "UnusedReceiverParameter")
@JvmName("recoverAlwaysSuccess")
@JvmSynthetic
public inline fun <Value> Expected<Value, Nothing>.recover(
    uselessTransform: (Nothing) -> Unit
): Expected.Success<Value> = unexpected()
