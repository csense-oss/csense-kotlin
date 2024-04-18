package org.csenseoss.kotlin.patterns.expected.operators

import org.csenseoss.kotlin.patterns.expected.*

public inline fun <T> tryOrFailed(
    action: () -> T
): Expected<T, Throwable> = expectedCatching {
    action().asSuccess()
}