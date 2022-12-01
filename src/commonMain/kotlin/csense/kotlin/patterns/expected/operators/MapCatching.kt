package csense.kotlin.patterns.expected.operators

import csense.kotlin.general.*
import csense.kotlin.patterns.expected.*
import csense.kotlin.patterns.expected.expectedMapCatchingError.*


public inline fun <InputValue, OutputValue, Error> Expected<InputValue, Error>.mapCatching(
    transform: (InputValue) -> OutputValue
): Expected<OutputValue, ExpectedMapCatchingError<Error>> {
    val value = valueOrOnFailed {
        return@mapCatching Expected.Failed(ExpectedMapCatchingError.Failed(it))
    }
    return try {
        Expected.Success(transform(value))
    } catch (e: Throwable) {
        Expected.Failed(ExpectedMapCatchingError.Exception(e))
    }
}



@Deprecated(
    level = DeprecationLevel.ERROR, message = "If you already know its a failed result you should not map it.",
    replaceWith = ReplaceWith("this")
)
@Suppress("UNUSED_PARAMETER", "MissingTestFunction", "UnusedReceiverParameter")
public inline fun <Error> Expected.Failed<Error>.mapCatching(
    transform: (Nothing) -> Nothing
): Expected.Failed<ExpectedMapCatchingError.Failed<Error>> = unexpected()


@Deprecated(
    level = DeprecationLevel.ERROR, message = "If you already know its a failed result you should not map it.",
    replaceWith = ReplaceWith("this")
)
@Suppress("UNUSED_PARAMETER", "MissingTestFunction", "UnusedReceiverParameter")
public inline fun <Error> Expected<Nothing, Error>.mapCatching(
    transform: (Nothing) -> Nothing
): Expected.Failed<ExpectedMapCatchingError.Failed<Error>> = unexpected()