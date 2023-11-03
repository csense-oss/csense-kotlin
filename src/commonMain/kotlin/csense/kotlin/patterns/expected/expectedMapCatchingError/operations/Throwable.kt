@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.patterns.expected.expectedMapCatchingError.operations

import csense.kotlin.patterns.expected.expectedMapCatchingError.*


public inline val ExpectedMapCatchingError<Throwable>.throwable: Throwable
    get() = when (this) {
        is ExpectedMapCatchingError.Exception -> exception
        is ExpectedMapCatchingError.Failed -> error
    }