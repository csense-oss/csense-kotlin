@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package csense.kotlin.extensions.collections.array.generic

import csense.kotlin.annotations.numbers.*
import csense.kotlin.extensions.collections.*
import csense.kotlin.extensions.primitives.int.*


@IntLimit(from = 0)
public inline fun <T> Array<out T>.indexOfFirstOrNull(predicate: Predicate<T>): Int? =
    indexOfFirst(predicate).indexOfExtensions.unwrapUnsafeIndexOf()


/**
 *
 * @receiver [Collection]<T>
 * @param predicate [Function1]<T, [Boolean]>
 * @return [Int]? null if not found, the index otherwise.
 */
@IntLimit(from = 0)
public inline fun <T> Array<T>.indexOfLastOrNull(predicate: Predicate<T>): Int? =
    indexOfLast(predicate).indexOfExtensions.unwrapUnsafeIndexOf()