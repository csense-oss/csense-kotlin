package csense.kotlin.patterns.expected

import csense.kotlin.*
import kotlin.contracts.*


public inline fun <Value, Error> Expected<Value, Error>.recoverCatching(
    transform: (Error) -> Value
): Expected<Value, ExpectedExceptionFailed<Error>> {
    contract { callsInPlace(transform, InvocationKind.AT_MOST_ONCE) }
    return when (this) {
        is Expected.Success -> this
        is Expected.Failed -> try {
            Expected.Success(transform(this.error))
        } catch (e: Throwable) {
            Expected.Failed(ExpectedExceptionFailed(this, e))
        }
    }
}


public class ExpectedExceptionFailed<Error>(
    public val failed: Expected.Failed<Error>,
    public val exception: Throwable
)

@Deprecated(
    level = DeprecationLevel.ERROR, message = "If you already know its a success result you should not recover it.",
    replaceWith = ReplaceWith("this")
)
@Suppress("UNUSED_PARAMETER", "MissingTestFunction", "UnusedReceiverParameter")
public inline fun <Value, Error> Expected.Success<Value>.recoverCatching(
    transform: (Error) -> Value
): Nothing = unexpected()