@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.extensions.collections.array.generic

import csense.kotlin.extensions.collections.generic.collectionBounds.operations.*

/**
 * Returns a list containing all elements except first [dropCount] elements.
 * if [dropCount] is greater than the size or negative, an empty array is returned
 */
public inline fun <T> Array<T>.dropOrEmpty(
    dropCount: Int
): List<T> = dropOr(
    dropCount = dropCount,
    orValue = emptyList()
)

/**
 * Returns a list containing all elements except first [dropCount] elements.
 * if [dropCount] is greater than the size or negative, null is returned
 */
public inline fun <T> Array<T>.dropOrNull(
    dropCount: Int
): List<T>? = dropOr(dropCount = dropCount) {
    return@dropOrNull null
}


/**
 * Returns a list containing all elements except first [dropCount] elements.
 * if [dropCount] is greater than the size or negative, [orValue] is returned
 */
public inline fun <T> Array<T>.dropOr(
    dropCount: Int,
    orValue: List<T>
): List<T> = dropOr(dropCount = dropCount, orAction = { orValue })

/**
 * Returns a list containing all elements except first [dropCount] elements.
 * if [dropCount] is greater than the size or negative, [orAction] is invoked and returned
 */
public inline fun <T> Array<T>.dropOr(
    dropCount: Int,
    orAction: () -> List<T>
): List<T> {
    if (isIndex.outOfBoundsEndOutOfBounds(dropCount)) {
        return orAction()
    }
    return drop(dropCount)
}

