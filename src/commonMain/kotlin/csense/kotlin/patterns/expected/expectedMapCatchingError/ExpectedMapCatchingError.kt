@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.patterns.expected.expectedMapCatchingError

import kotlin.jvm.*


public sealed interface ExpectedMapCatchingError<Error> {
    @JvmInline
    public value class Failed<Error>(
        public val error: Error
    ) : ExpectedMapCatchingError<Error>

    @JvmInline
    public value class Exception<Error>(
        public val exception: Throwable
    ) : ExpectedMapCatchingError<Error>
}


