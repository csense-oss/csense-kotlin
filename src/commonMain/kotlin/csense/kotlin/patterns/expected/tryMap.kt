package csense.kotlin.patterns.expected

import csense.kotlin.*
import kotlin.jvm.*


public inline fun <InputValue, OutputValue, Error> Expected<InputValue, Error>.tryMap(
    transform: Expected.Companion.ExpectedContext.(InputValue) -> Expected<OutputValue, Error>
): Expected<OutputValue, Error> {
    return if (this.isSuccess()) {
        tryMap(transform)
    } else {
        this
    }
}




@JvmName("tryMapSuccess")
public inline fun <InputValue, OutputValue, Error> Expected<InputValue, Nothing>.tryMap(
    transform: Expected.Companion.ExpectedContext.(InputValue) -> Expected<OutputValue, Error>
): Expected<OutputValue, Error> {
    val value = valueOrFailed { return@tryMap this }
    return with(Expected.Companion.ExpectedContext.instance) { transform(value) }
}



@Deprecated(
    level = DeprecationLevel.ERROR, message = "If you already know its a failed result you should not map it.",
    replaceWith = ReplaceWith("this")
)
@Suppress("UNUSED_PARAMETER", "MissingTestFunction", "UnusedReceiverParameter")
public inline fun <Error> Expected.Failed<Error>.tryMap(
    uselessTransform: Expected.Companion.ExpectedContext.(Nothing) -> Expected.Failed<Error>
): Expected.Failed<Error> = unexpected()


@Deprecated(
    level = DeprecationLevel.ERROR, message = "If you already know its a failed result you should not map it.",
    replaceWith = ReplaceWith("this")
)
@Suppress("UNUSED_PARAMETER", "MissingTestFunction", "UnusedReceiverParameter")
public inline fun <Error> Expected<Nothing, Error>.tryMap(
    uselessTransform: Expected.Companion.ExpectedContext.(Nothing) -> Expected.Failed<Error>
): Expected.Failed<Error> = unexpected()

