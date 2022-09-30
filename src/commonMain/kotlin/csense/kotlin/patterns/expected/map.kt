package csense.kotlin.patterns.expected

import csense.kotlin.*


public inline fun <InputValue, OutputValue, Error> Expected<InputValue, Error>.map(
    transform: (InputValue) -> OutputValue
): Expected<OutputValue, Error> {
    val value = valueOrFailed { return@map this }
    return Expected.Success(transform(value))
}

public inline fun <InputValue, OutputValue> Expected.Success<InputValue>.map(
    transform: (InputValue) -> OutputValue
): Expected.Success<OutputValue> {
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

