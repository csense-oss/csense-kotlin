package csense.kotlin.patterns.expected

import csense.kotlin.*
import kotlin.contracts.*
import kotlin.jvm.*


public inline fun <Value, Error> Expected<Value, Error>.tryRecover(
    transform: Expected.Companion.ExpectedContext.(Error) -> Expected<Value, Error>
): Expected<Value, Error> {
    contract { callsInPlace(transform, InvocationKind.AT_MOST_ONCE) }
    return when (this) {
        is Expected.Success -> this
        is Expected.Failed -> with(Expected.Companion.ExpectedContext) {
            transform(error)
        }
    }
}

@JvmName("tryRecoverFailed")
@JvmSynthetic
public inline fun <Value, Error, Result : Expected<Value, Error>> Expected<Nothing, Error>.tryRecover(
    transform: Expected.Companion.ExpectedContext.(Error) -> Result
): Result {
    contract { callsInPlace(transform, InvocationKind.EXACTLY_ONCE) }
    return with(Expected.Companion.ExpectedContext) {
        transform(error)
    }
}


@Deprecated(
    level = DeprecationLevel.ERROR, message = "If you already know its a success result you should not recover it.",
    replaceWith = ReplaceWith("this")
)
@Suppress("UNUSED_PARAMETER", "MissingTestFunction", "UnusedReceiverParameter")
public inline fun <Value> Expected.Success<Value>.tryRecover(
    transform: Expected.Companion.ExpectedContext.(Nothing) -> Expected<Nothing, Nothing>
): Expected.Success<Value> = unexpected()

@Deprecated(
    level = DeprecationLevel.ERROR, message = "If you already know its a success result you should not recover it.",
    replaceWith = ReplaceWith("this")
)
@Suppress("UNUSED_PARAMETER", "MissingTestFunction", "UnusedReceiverParameter")
public inline fun <Value> Expected<Value, Nothing>.tryRecover(
    transform: Expected.Companion.ExpectedContext.(Nothing) -> Expected<Nothing, Nothing>
): Expected.Success<Value> = unexpected()