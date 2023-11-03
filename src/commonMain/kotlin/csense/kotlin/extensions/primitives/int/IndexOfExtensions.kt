@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.extensions.primitives.int

import csense.kotlin.annotations.numbers.*
import kotlin.jvm.*


@JvmInline
public value class IndexOfExtensions(public val value: Int)

public inline val Int.indexOfExtensions: IndexOfExtensions
    get() = IndexOfExtensions(this)

/**
 * Unwraps an unsafe index of int. everything below 0 becomes null otherwise the given number
 * @receiver [Int]
 * @return [Int]? null if negative, the value otherwise
 */
@IntLimit(from = 0)
public inline fun IndexOfExtensions.unwrapUnsafeIndexOf(): Int? = when (value.isNegative) {
    true -> null
    else -> value
}