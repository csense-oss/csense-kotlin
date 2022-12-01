package csense.kotlin.patterns.expected.operators

import csense.kotlin.general.*
import csense.kotlin.patterns.expected.*
import kotlin.contracts.*


public inline fun <InputValue, OutputValue, Error> Expected<InputValue, Error>.map(
    transform: (InputValue) -> OutputValue
): Expected<OutputValue, Error> {
    contract {
        callsInPlace(transform, InvocationKind.AT_MOST_ONCE)
    }
    val value = valueOrOnExpectedFailed { return@map it }
    return Expected.Success(transform(value))
}

public inline fun <InputValue, OutputValue> Expected.Success<InputValue>.map(
    transform: (InputValue) -> OutputValue
): Expected.Success<OutputValue> {
    contract {
        callsInPlace(transform, InvocationKind.EXACTLY_ONCE)
    }
    return Expected.Success(transform(value))
}

@Deprecated(
    level = DeprecationLevel.ERROR, message = "If you already know its a failed result you should not map it.",
    replaceWith = ReplaceWith("this")
)
@Suppress("UNUSED_PARAMETER", "MissingTestFunction", "UnusedReceiverParameter")
public inline fun <Error> Expected.Failed<Error>.map(
    uselessTransform: (Nothing) -> Unit = {}
): Expected.Failed<Error> = unexpected()


@Deprecated(
    level = DeprecationLevel.ERROR, message = "If you already know its a failed result you should not map it.",
    replaceWith = ReplaceWith("this")
)
@Suppress("UNUSED_PARAMETER", "MissingTestFunction", "UnusedReceiverParameter")
public inline fun <Error> Expected<Nothing, Error>.map(
    uselessTransform: (Nothing) -> Unit = {}
): Expected.Failed<Error> = unexpected()
