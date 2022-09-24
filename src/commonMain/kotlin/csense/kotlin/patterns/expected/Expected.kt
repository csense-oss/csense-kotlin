@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package csense.kotlin.patterns.expected

import csense.kotlin.*
import kotlin.contracts.*
import kotlin.jvm.*

public sealed interface Expected<out Value, out Error> {
    public companion object {
        //used to limit the asSuccess and failed extensions (to avoid global namespace pollution)
        public class ExpectedContext private constructor() {
            public fun <Value> Value.asSuccess(): Success<Value> {
                return Success(this)
            }

            public fun <Error> Error.asFailed(): Failed<Error> {
                return Failed(this)
            }

            public companion object {
                public val instance: ExpectedContext = ExpectedContext()
            }
        }
    }

    //TODO use inline value class when available for MPP
    public class Failed<out Error>(
        public val error: Error
    ) : Expected<Nothing, Error>

    //TODO use inline value class when available for MPP
    public class Success<out Value>(
        public val value: Value
    ) : Expected<Value, Nothing>

}


public inline fun <Value, Error> expected(
    action: Expected.Companion.ExpectedContext.() -> Expected<Value, Error>,
    onException: (Throwable) -> Error
): Expected<Value, Error> {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
        callsInPlace(onException, InvocationKind.AT_MOST_ONCE)
    }
    return with(Expected.Companion.ExpectedContext.instance) {
        try {
            action()
        } catch (e: Throwable) {
            onException(e).asFailed()
        }
    }
}

//TODO improve with annotations & exceptions plugin to only "throw" iff the action can throw.
@Throws
public inline fun <Value, Error> expected(
    action: Expected.Companion.ExpectedContext.() -> Expected<Value, Error>
): Expected<Value, Error> {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return expected(action = action, onException = { throw it })
}

public inline fun <Data> expectedCatching(
    action: Expected.Companion.ExpectedContext.() -> Expected<Data, Throwable>
): Expected<Data, Throwable> {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return expected(action = {
        action()
    }, onException = {
        it
    })
}


//Nothing as error type means it can never happen.
public inline val <Value> Expected<Value, Nothing>.value: Value
    get() = asSuccess.value

//Nothing as value type means it can never happen.
public inline val <Error> Expected<Nothing, Error>.error: Error
    get() = asFailed.error

public inline val <Error> Expected<Nothing, Error>.asFailed: Expected.Failed<Error>
    get() = this as Expected.Failed<Error>

public inline val <Value> Expected<Value, Nothing>.asSuccess: Expected.Success<Value>
    get() = this as Expected.Success<Value>


