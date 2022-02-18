@file:OptIn(ExperimentalContracts::class)
@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.patterns

import csense.kotlin.logger.*
import kotlin.contracts.*


public sealed interface Expected<out Error, out Value> {
    public fun isSuccess(): Boolean
    public fun isFailed(): Boolean

    public companion object
}

public interface ExpectedFailed<out Error> : Expected<Error, Nothing> {
    override fun isSuccess(): Boolean = false

    override fun isFailed(): Boolean = true

    public val error: Error

}

public interface ExpectedSuccess<out Value> : Expected<Nothing, Value> {
    override fun isSuccess(): Boolean = true

    override fun isFailed(): Boolean = false

    public val value: Value
}

public data class ExpectedSuccessImpl<out Value>(
    override val value: Value
) : ExpectedSuccess<Value>

public data class ExpectedFailedImpl<out Error>(
    override val error: Error
) : ExpectedFailed<Error>

public inline fun <Error, Data> Expected<Error, Data>.valueOrOnFailed(
    onFailed: (error: Error) -> Nothing
): Data {
    return when (this) {
        is ExpectedSuccess -> value
        is ExpectedFailed -> onFailed(error)
    }
}

public inline fun <Error, Data> Expected<Error, Data>.valueOrExpectedFailed(
    onFailed: ExpectedFailed<Error>.() -> Nothing
): Data {
    return when (this) {
        is ExpectedSuccess -> value
        is ExpectedFailed -> onFailed(this)
    }
}

public inline fun <Data> Expected<Throwable, Data>.valueOrDefault(default: Data): Data {
    return when (this) {
        is ExpectedSuccess -> value
        is ExpectedFailed -> default
    }
}

public inline fun <Data> Expected<Throwable, Data>.valueOrDefault(default: () -> Data): Data {
    return when (this) {
        is ExpectedSuccess -> value
        is ExpectedFailed -> default()
    }
}

public inline fun <Data> expectedCatching(
    noinline logger: LoggingFunctionType<*>? = null,
    action: ExpectedContext.() -> Expected<Throwable, Data>
): Expected<Throwable, Data> {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return expected(logger, action = {
        action()
    }, onException = {
        it
    })
}


public inline fun <Error, Data> expected(
    noinline logger: LoggingFunctionType<*>? = null,
    action: ExpectedContext.() -> Expected<Error, Data>,
    onException: (Throwable) -> Error
): Expected<Error, Data> {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return with(ExpectedContext.instance) {
        try {
            action()
        } catch (e: Throwable) {
            logger?.invoke("expected", "Failed with", e)
            onException(e).asFailed()
        }
    }

}

@Throws
public inline fun <Error, Data> expected(
    noinline logExceptionWith: LoggingFunctionType<*>? = null,
    action: ExpectedContext.() -> Expected<Error, Data>
): Expected<Error, Data> {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return expected(logger = logExceptionWith, action = action, onException = { throw it })
}

public class ExpectedContext private constructor() {
    public fun <Value> Value.asSuccess(): ExpectedSuccess<Value> {
        return Expected.asSuccess(this)
    }

    public fun <Error> Error.asFailed(): ExpectedFailed<Error> {
        return Expected.failed(this)
    }

    public companion object {
        public val instance: ExpectedContext = ExpectedContext()
    }
}

@Throws
public inline fun <reified Error, Data> Expected<Throwable, Data>.withErrorType(
): Expected<Error, Data> {
    return when (this) {
        is ExpectedSuccess -> this
        is ExpectedFailed -> asErrorTypeOrNull() ?: throw error
    }
}

public inline fun <reified Error, Data> Expected<Throwable, Data>.withErrorType(
    onWrongErrorType: (Throwable) -> Error
): Expected<Error, Data> {
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


public fun <T> Expected.Companion.asSuccess(value: T): ExpectedSuccess<T> {
    return ExpectedSuccessImpl(value)
}


public fun <Error> Expected.Companion.failed(error: Error): ExpectedFailed<Error> {
    return ExpectedFailedImpl(error)
}


//TODO map catching?
public inline fun <reified Error, InputData, OutputData> Expected<Error, InputData>.map(
    transform: (InputData) -> OutputData
): Expected<Error, OutputData> {
    val value = valueOrExpectedFailed { return@map this }
    return Expected.asSuccess(transform(value))
}

//TODO recover catching?
public inline fun <reified Error, Data> Expected<Error, Data>.recover(
    transform: (Error) -> Data
): ExpectedSuccess<Data> {
    return when (this) {
        is ExpectedSuccess -> this
        is ExpectedFailed -> Expected.asSuccess(transform(this.error))
    }
}


//STOPSHIP example / usage testing
@Suppress("unused")
private object TestMe {

    val qwe = 123

    fun computeFailableResult() = expectedCatching {
        if (42 == 42) {
            throw IllegalArgumentException()
        }
        "".asSuccess()
    }

    fun neverFails() = expected {
        "".asSuccess()
    }

    fun alwaysFails() = expected {
        "".asFailed()
    }

    fun computeFailableResultWithNoThrowing() = expected {
        if (42 == 42) {
            return@expected YError.Yaa().asFailed()
        }
        if("a".isEmpty()){
            return@expected YError.Weee.asFailed()
        }
        if(78 + 2 > 0){
            return@expected YError.Noo().asFailed()
        }

        "".asSuccess()
    }

    fun computeWee() = expected(action = {
        if (true) {
            throw RuntimeException()
        }
        return@expected "".asSuccess()
    }, onException = {
        YError.Weee
    })

    fun autoComplete() = expected<Throwable, String>(logExceptionWith = L::error) {
        "".asSuccess()
    }

    fun use() {
        val x = computeFailableResult()
        val y: Expected<YError, String> = computeFailableResultWithNoThrowing()
        val hmm = neverFails()
        hmm.value
        val qqq = alwaysFails()
        qqq.error
        val value = x.map { it.toInt() }.recover { 42 }.value
        val xValue = x.valueOrOnFailed {
            return@use
        }
        when (y) {
            is ExpectedFailed -> when (y.error) {
                is YError.Yaa -> TODO()
                is YError.Noo -> TODO()
                YError.Weee -> TODO()
            }
            is ExpectedSuccess -> return
        }
    }

}

public sealed class YError {
    public class Yaa : YError()
    public class Noo : YError()
    public object Weee : YError()
}

//Nothing as error type means it can never happen..
public inline val <Data> Expected<Nothing, Data>.value: Data
    get() = (this as ExpectedSuccess<Data>).value

//Nothing as value type means it can never happen..
public inline val <Error> Expected<Error, Nothing>.error: Error
    get() = (this as ExpectedFailed<Error>).error
