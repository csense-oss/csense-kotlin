@file:Suppress("NOTHING_TO_INLINE")
package csense.kotlin.patterns.expected.operators

import csense.kotlin.patterns.expected.*

@Suppress("FunctionName")
public inline fun Expected.Companion.FailedException(
    message: String,
    cause: Throwable? = null
): Expected.Failed<Exception> = Expected.Failed(
    Exception(
        message,
        cause
    )
)