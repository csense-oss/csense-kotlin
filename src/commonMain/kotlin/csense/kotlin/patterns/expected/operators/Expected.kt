package csense.kotlin.patterns.expected.operators

import csense.kotlin.patterns.expected.*
import kotlin.contracts.*


public inline fun <Value, Error> expected(
    action: Expected.Companion.ExpectedContext.() -> Expected<Value, Error>,
    onException: (Throwable) -> Error
): Expected<Value, Error> {
    contract {
        callsInPlace(onException, InvocationKind.AT_MOST_ONCE)
    }
    return with(Expected.Companion.ExpectedContext) {
        try {
            action()
        } catch (e: Throwable) {
            onException(e).asFailed()
        }
    }
}

@Throws(Throwable::class)
public inline fun <Value, Error> expected(
    action: Expected.Companion.ExpectedContext.() -> Expected<Value, Error>
): Expected<Value, Error> {
    return expected(action = action, onException = { throw it })
}

public inline fun <Data> expectedCatching(
    action: Expected.Companion.ExpectedContext.() -> Expected<Data, Throwable>
): Expected<Data, Throwable> {
    return expected(action = {
        action()
    }, onException = {
        it
    })
}

