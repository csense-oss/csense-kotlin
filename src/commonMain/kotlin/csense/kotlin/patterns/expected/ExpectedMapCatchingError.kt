@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.patterns.expected

import csense.kotlin.*
import kotlin.contracts.*


public sealed interface ExpectedMapCatchingError<Error> {
    //TODO use inline value class when available for MPP
    public class Failed<Error>(
        public val error: Error
    ) : ExpectedMapCatchingError<Error>

    //TODO use inline value class when available for MPP
    public class Exception<Error>(
        public val exception: Throwable
    ) : ExpectedMapCatchingError<Error>
}

public inline fun <Error> ExpectedMapCatchingError<Error>.isFailed(): Boolean {
    contract {
        returns(true) implies (this@isFailed is ExpectedMapCatchingError.Failed)
        returns(false) implies (this@isFailed is ExpectedMapCatchingError.Exception)
    }
    return this is ExpectedMapCatchingError.Failed
}

public inline fun <Error> ExpectedMapCatchingError<Error>.isException(): Boolean {
    contract {
        returns(false) implies (this@isException is ExpectedMapCatchingError.Failed)
        returns(true) implies (this@isException is ExpectedMapCatchingError.Exception)
    }
    return this is ExpectedMapCatchingError.Exception
}


public inline fun <Value, Error> Expected<Value, Error>.applyIfSuccess(
    onSuccess: ReceiverFunctionUnit<Expected.Success<Value>>
): Expected<Value, Error> = apply {
    if (this.isSuccess()) {
        onSuccess()
    }
}

public inline fun <Value, Error> Expected<Value, Error>.applyIfFailed(
    onFailed: ReceiverFunctionUnit<Expected.Failed<Error>>
): Expected<Value, Error> = apply {
    if (this.isFailed()) {
        onFailed(this)
    }
}


