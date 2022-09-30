package csense.kotlin.patterns.expected

import csense.kotlin.*


@Throws
public inline fun <Value, reified Error> Expected<Value, Throwable>.withErrorType(

): Expected<Value, Error> = when (this) {
    is Expected.Success -> this
    is Expected.Failed -> asErrorTypeOrNull() ?: throw error
}


@Deprecated(
    level = DeprecationLevel.ERROR, message = "Success does not have an error",
    replaceWith = ReplaceWith("")
)
@Suppress("UnusedReceiverParameter")
public fun Expected.Success<*>.withErrorType(): Nothing = unexpected()


public inline fun <Value, reified Error> Expected<Value, Throwable>.withErrorType(
    onWrongErrorType: (Throwable) -> Error
): Expected<Value, Error> = when (this) {
    is Expected.Success -> this
    is Expected.Failed -> asErrorTypeOrNull() ?: Expected.Failed(onWrongErrorType(error))
}


@Deprecated(
    level = DeprecationLevel.ERROR, message = "Success has a value field instead",
    replaceWith = ReplaceWith("this.value")
)
@Suppress("UNUSED_PARAMETER", "UnusedReceiverParameter")
public fun Expected.Success<*>.withErrorType(
    onWrongErrorType: (Throwable) -> Error
): Nothing = unexpected()
