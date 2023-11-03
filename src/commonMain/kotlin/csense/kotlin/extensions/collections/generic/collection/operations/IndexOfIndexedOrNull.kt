@file:Suppress("UnusedReceiverParameter")

package csense.kotlin.extensions.collections.generic.collection.operations

import csense.kotlin.annotations.numbers.*
import csense.kotlin.extensions.collections.generic.collection.*

@IntLimit(from = 0)
public inline fun <T> GenericCollections.indexOfIndexedOrNull(
    byRange: IntProgression,
    getElement: (index: @IntLimit(from = 0) Int) -> T,
    predicate: (index:@IntLimit(from = 0)  Int, element: T) -> Boolean
): Int? {
    if (byRange.isEmpty()) {
        return null
    }
    for (index: Int in byRange) {
        val element: T = getElement(index)
        if (predicate(index, element)) {
            return index
        }
    }
    return null
}