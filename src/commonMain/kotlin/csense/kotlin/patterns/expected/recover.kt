package csense.kotlin.patterns.expected

import csense.kotlin.*
import kotlin.jvm.*


public inline fun <Value, Error> Expected<Value, Error>.recover(
    transform: (Error) -> Value
): Expected.Success<Value> {
    return when (this) {
        is Expected.Success -> this
        is Expected.Failed -> Expected.Success(transform(this.error))
    }
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

@Deprecated(
    level = DeprecationLevel.ERROR, message = "If you already know its a success result you should not recover it.",
    replaceWith = ReplaceWith("this")
)
@Suppress("UNUSED_PARAMETER", "MissingTestFunction", "UnusedReceiverParameter")
public inline fun <Value> Expected.Success<Value>.recover(
    uselessTransform: (Nothing) -> Unit
): Expected.Success<Value> = unexpected()
