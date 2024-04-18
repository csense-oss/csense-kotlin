@file:Suppress("NOTHING_TO_INLINE")

package org.csenseoss.kotlin.patterns.expected.expectedMapCatchingError.operations

import org.csenseoss.kotlin.patterns.expected.expectedMapCatchingError.*


public inline val ExpectedMapCatchingError<Throwable>.throwable: Throwable
    get() = when (this) {
        is ExpectedMapCatchingError.Exception -> exception
        is ExpectedMapCatchingError.Failed -> error
    }