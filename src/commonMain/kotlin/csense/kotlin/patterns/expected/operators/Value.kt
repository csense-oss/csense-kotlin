@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.patterns.expected.operators

import csense.kotlin.patterns.expected.*

public inline fun <T> Expected<T, T>.value(): T = when (this) {
    is Expected.Failed -> error
    is Expected.Success -> value
}


//Nothing as error type means it can never happen.
public inline val <Value> Expected<Value, Nothing>.value: Value
    get() = asSuccess.value
