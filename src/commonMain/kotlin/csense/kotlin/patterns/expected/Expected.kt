@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package csense.kotlin.patterns.expected

import kotlin.jvm.*

public sealed interface Expected<out Value, out Error> {
    public companion object {
        //TODO consider context receivers
        //used to limit the asSuccess and failed extensions (to avoid global namespace pollution)
        public object ExpectedContext {
            public fun <Value> Value.asSuccess(): Success<Value> {
                return Success(this)
            }

            public fun <Error> Error.asFailed(): Failed<Error> {
                return Failed(this)
            }

        }
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
