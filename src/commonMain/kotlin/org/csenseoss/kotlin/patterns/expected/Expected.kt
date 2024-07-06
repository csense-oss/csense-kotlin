@file:Suppress("unused", "NOTHING_TO_INLINE")

package org.csenseoss.kotlin.patterns.expected

import kotlin.jvm.*

public sealed interface Expected<out Value, out Error> {
    public companion object {
        public interface ExpectedContext {
            public fun <Value> Value.asSuccess(): Success<Value> {
                return Success(this)
            }

            public fun <Error> Error.asFailed(): Failed<Error> {
                return Failed(this)
            }
        }
        public object ExpectedContextContainer: ExpectedContext
    }

    @JvmInline
    public value class Failed<out Error>(
        public val error: Error
    ) : Expected<Nothing, Error>

    @JvmInline
    public value class Success<out Value>(
        public val value: Value
    ) : Expected<Value, Nothing>
}