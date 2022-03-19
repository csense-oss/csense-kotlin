@file:OptIn(ExperimentalContracts::class)
@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.patterns

import csense.kotlin.logger.*
import kotlin.contracts.*


public sealed interface Expected<out Value, out Error> {
    public companion object {
        //used to limit the asSuccess and failed extensions (to avoid global namespace pollution)
        public class ExpectedContext private constructor() {
            public fun <Value> Value.asSuccess(): ExpectedSuccess<Value> {
                return Expected.success(this)
            }

            public fun <Error> Error.asFailed(): ExpectedFailed<Error> {
                return Expected.failed(this)
            }

            public companion object {
                public val instance: ExpectedContext = ExpectedContext()
            }
        }

        public data class ExpectedSuccessImpl<out Value>(
            override val value: Value
        ) : ExpectedSuccess<Value>

        public data class ExpectedFailedImpl<out Error>(
            override val error: Error
        ) : ExpectedFailed<Error>

    }
}

public interface ExpectedFailed<out Error> : Expected<Nothing, Error> {
    public val error: Error
}

public interface ExpectedSuccess<out Value> : Expected<Value, Nothing> {
    public val value: Value
}


public fun <Value, Error> Expected<Value, Error>.isSuccess(): Boolean {
    contract {
        returns(true) implies (this@isSuccess is ExpectedSuccess<Value>)
        returns(false) implies (this@isSuccess is ExpectedFailed<Error>)
    }
    return this is ExpectedSuccess<Value>
}

public fun <Value, Error> Expected<Value, Error>.isFailed(): Boolean {
    contract {
        returns(true) implies (this@isFailed is ExpectedFailed<Error>)
        returns(false) implies (this@isFailed is ExpectedSuccess<Value>)

    }
    return this is ExpectedFailed<Error>
}


public inline fun <Value, Error> Expected<Value, Error>.valueOrOnFailed(
    onFailed: (error: Error) -> Nothing
): Value {
    return when (this) {
        is ExpectedSuccess -> value
        is ExpectedFailed -> onFailed(error)
    }
}

public inline fun <Value, Error> Expected<Value, Error>.valueOrExpectedFailed(
    onFailed: ExpectedFailed<Error>.() -> Nothing
): Value {
    return when (this) {
        is ExpectedSuccess -> value
        is ExpectedFailed -> onFailed(this)
    }
}

public inline fun <Data, Error> Expected<Data, Error>.valueOrDefault(default: Data): Data {
    return when (this) {
        is ExpectedSuccess -> value
        is ExpectedFailed -> default
    }
}

public inline fun <Data, Error> Expected<Data, Error>.valueOrDefault(default: () -> Data): Data {
    return when (this) {
        is ExpectedSuccess -> value
        is ExpectedFailed -> default()
    }
}

public inline fun <Data> expectedCatching(
    noinline logger: LoggingFunctionType<*>? = null,
    action: Expected.Companion.ExpectedContext.() -> Expected<Data, Throwable>
): Expected<Data, Throwable> {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return expected(logger, action = {
        action()
    }, onException = {
        it
    })
}


public inline fun <Value, Error> expected(
    noinline logger: LoggingFunctionType<*>? = null,
    action: Expected.Companion.ExpectedContext.() -> Expected<Value, Error>,
    onException: (Throwable) -> Error
): Expected<Value, Error> {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return with(Expected.Companion.ExpectedContext.instance) {
        try {
            action()
        } catch (e: Throwable) {
            logger?.invoke("expected", "Failed with", e)
            onException(e).asFailed()
        }
    }

}

//TODO improve with annotations & exceptions plugin to only "throw" iff the action can throw.
@Throws
public inline fun <Value, Error> expected(
    noinline logExceptionWith: LoggingFunctionType<*>? = null,
    action: Expected.Companion.ExpectedContext.() -> Expected<Value, Error>
): Expected<Value, Error> {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return expected(logger = logExceptionWith, action = action, onException = { throw it })
}


@Throws
public inline fun <Value, reified Error> Expected<Value, Throwable>.withErrorType(
): Expected<Value, Error> {
    return when (this) {
        is ExpectedSuccess -> this
        is ExpectedFailed -> asErrorTypeOrNull() ?: throw error
    }
}

public inline fun <Value, reified Error> Expected<Value, Throwable>.withErrorType(
    onWrongErrorType: (Throwable) -> Error
): Expected<Value, Error> {
    return when (this) {
        is ExpectedSuccess -> this
        is ExpectedFailed -> asErrorTypeOrNull() ?: Expected.failed(onWrongErrorType(error))
    }
}

public inline fun <reified Error> ExpectedFailed<Throwable>.asErrorTypeOrNull(): ExpectedFailed<Error>? {
    return if (error is Error) {
        @Suppress("UNCHECKED_CAST")
        return this as ExpectedFailed<Error>
    } else {
        null
    }
}


public fun <T> Expected.Companion.success(value: T): ExpectedSuccess<T> {
    return Expected.Companion.ExpectedSuccessImpl(value)
}


public fun <Error> Expected.Companion.failed(error: Error): ExpectedFailed<Error> {
    return Expected.Companion.ExpectedFailedImpl(error)
}


public inline fun <InputValue, OutputValue, reified Error> Expected<InputValue, Error>.map(
    transform: (InputValue) -> OutputValue
): Expected<OutputValue, Error> {
    val value = valueOrExpectedFailed { return@map this }
    return Expected.success(transform(value))
}

public inline fun <InputValue, OutputValue, reified Error> Expected<InputValue, Error>.tryMap(
    transform: (InputValue) -> Expected<OutputValue, Error>
): Expected<OutputValue, Error> {
    val value = valueOrExpectedFailed { return@tryMap this }
    return transform(value)
}

public inline fun <InputValue, OutputValue, reified Error> Expected<InputValue, Error>.mapCatching(
    transform: (InputValue) -> OutputValue
): Expected<OutputValue, ExpectedMapCatchingError<Error>> {
    val value = valueOrOnFailed {
        return@mapCatching Expected.failed(ExpectedMapCatchingError.Failed(it))
    }
    return try {
        Expected.success(transform(value))
    } catch (e: Throwable) {
        Expected.failed(ExpectedMapCatchingError.Exception(e))
    }
}


public inline fun <Value, reified Error> Expected<Value, Error>.recover(
    transform: (Error) -> Value
): ExpectedSuccess<Value> {
    return when (this) {
        is ExpectedSuccess -> this
        is ExpectedFailed -> Expected.success(transform(this.error))
    }
}

public inline fun <Value, reified Error> Expected<Value, Error>.tryRecover(
    transform: (Error) -> Expected<Value, Error>
): Expected<Value, Error> {
    return when (this) {
        is ExpectedSuccess -> this
        is ExpectedFailed -> transform(this.error)
    }
}

public inline fun <Value, reified Error> Expected<Value, Error>.recoverCatching(
    transform: (Error) -> Value
): Expected<Value, ExpectedExceptionFailed<Error>> {
    return when (this) {
        is ExpectedSuccess -> this
        is ExpectedFailed -> try {
            Expected.success(transform(this.error))
        } catch (e: Throwable) {
            Expected.failed(ExpectedExceptionFailed(this, e))
        }
    }
}


//Nothing as error type means it can never happen.
public inline val <Value> Expected<Value, Nothing>.value: Value
    get() = (this as ExpectedSuccess<Value>).value

//Nothing as value type means it can never happen.
public inline val <Error> Expected<Nothing, Error>.error: Error
    get() = (this as ExpectedFailed<Error>).error


public class ExpectedExceptionFailed<Error>(
    public val failed: ExpectedFailed<Error>,
    public val exception: Throwable
)

public sealed class ExpectedMapCatchingError<Error> {
    public class Failed<Error>(public val error: Error) : ExpectedMapCatchingError<Error>()
    public class Exception<Error>(public val exception: Throwable) : ExpectedMapCatchingError<Error>()
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