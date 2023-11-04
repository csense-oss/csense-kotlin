@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package csense.kotlin.extensions.collections.array.generic

import csense.kotlin.annotations.numbers.*
import csense.kotlin.extensions.collections.*
import csense.kotlin.extensions.collections.generic.collection.*
import csense.kotlin.extensions.collections.generic.collection.operations.*
import csense.kotlin.extensions.primitives.int.*
import csense.kotlin.*

/**
 * Finds the index for the given predicate from the first element and forward
 * @receiver Array<out T>
 * @param predicate Function1<T, Boolean>
 * @return [Int]? null if not found, the index otherwise.
 */
@IntLimit(from = 0)
public inline fun <T> Array<out T>.indexOfFirstOrNull(
    predicate: Predicate<T>,
): Int? = indexOfFirstOrNull(
    startIndex = 0,
    predicate = predicate
)

/**
 * Finds the index for the given predicate from the given [startIndex] element and forward
 * @receiver Array<out T>
 * @param startIndex Int
 * @param predicate Function1<T, Boolean>
 * @return [Int]? null if not found, the index otherwise.
 */
@IntLimit(from = 0)
public inline fun <T> Array<out T>.indexOfFirstOrNull(
    @IntLimit(from = 0) startIndex: Int,
    predicate: Predicate<T>,
): Int? = GenericCollections.indexOfFirstOrNull(
    startIndex = startIndex,
    length = size,
    getElement = this::elementAt,
    predicate = predicate
)


/**
 * Finds the index for the given predicate from the last element and back
 * @receiver [Collection]<T>
 * @param predicate [Function1]<T, [Boolean]>
 * @return [Int]? null if not found, the index otherwise.
 */
@IntLimit(from = 0)
public inline fun <T> Array<out T>.indexOfLastOrNull(predicate: Predicate<T>): Int? =
    indexOfLast(predicate).indexOfExtensions.unwrapUnsafeIndexOf()

