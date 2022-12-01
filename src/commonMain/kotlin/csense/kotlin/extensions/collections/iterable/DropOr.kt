@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.extensions.collections.iterable

import csense.kotlin.extensions.collections.generic.*
import csense.kotlin.extensions.primitives.int.*


public inline fun <T> Iterable<T>.dropOrEmpty(
    dropCount: Int
): List<T> = dropOr(
    dropCount = dropCount,
    orValue = emptyList()
)

public inline fun <T> Iterable<T>.dropOrNull(
    dropCount: Int
): List<T>? = dropOr(dropCount = dropCount) {
    return@dropOrNull null
}


public inline fun <T> Iterable<T>.dropOr(
    dropCount: Int,
    orValue: List<T>
): List<T> = dropOr(dropCount = dropCount, orAction = { orValue })

public inline fun <T> Iterable<T>.dropOr(
    dropCount: Int,
    orAction: () -> List<T>
): List<T> {
    //TODO OPTIMIZE THIS....
    val count = count()
    if (dropCount.isNegative || dropCount >= count) {
        return orAction()
    }
    return drop(dropCount)
}

