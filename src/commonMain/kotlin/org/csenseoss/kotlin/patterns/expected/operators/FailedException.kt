@file:Suppress("NOTHING_TO_INLINE")
package org.csenseoss.kotlin.patterns.expected.operators

import org.csenseoss.kotlin.patterns.expected.*

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