@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.patterns.expected.operators

import csense.kotlin.patterns.expected.*

public inline fun <T> Expected<T, T>.asFailed(): Expected.Failed<T> = when (this) {
    is Expected.Failed -> this
    is Expected.Success -> Expected.Failed(error = this.value)
}


public inline val <Error> Expected<Nothing, Error>.asFailed: Expected.Failed<Error>
    get() = this as Expected.Failed<Error>
