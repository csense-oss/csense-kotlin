@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.extensions.collections.array.generic

import csense.kotlin.extensions.collections.generic.collectionBounds.*

public inline fun <T> Array<T>.dropOrEmpty(
    dropCount: Int
): List<T> = dropOr(
    dropCount = dropCount,
    orValue = emptyList()
)

public inline fun <T> Array<T>.dropOrNull(
    dropCount: Int
): List<T>? = dropOr(dropCount = dropCount) {
    return@dropOrNull null
}


public inline fun <T> Array<T>.dropOr(
    dropCount: Int,
    orValue: List<T>
): List<T> = dropOr(dropCount = dropCount, orAction = { orValue })

public inline fun <T> Array<T>.dropOr(
    dropCount: Int,
    orAction: () -> List<T>
): List<T> {
    if (isIndex.outOfBoundsEndOutOfBounds(dropCount)) {
        return orAction()
    }
    return drop(dropCount)
}

