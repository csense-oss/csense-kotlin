package csense.kotlin.patterns.expected.operators

import csense.kotlin.patterns.expected.*

public inline fun <T> tryOrFailed(
    action: () -> T
): Expected<T, Throwable> = expectedCatching {
    action().asSuccess()
}
