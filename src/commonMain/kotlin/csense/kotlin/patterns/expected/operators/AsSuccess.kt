@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.patterns.expected.operators

import csense.kotlin.patterns.expected.*

public inline fun <T> Expected<T, T>.asSuccess(): Expected.Success<T> = when (this) {
    is Expected.Success -> this
    is Expected.Failed -> Expected.Success(value = this.error)
}