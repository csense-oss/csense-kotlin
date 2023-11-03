@file:Suppress("UnusedReceiverParameter")

package csense.kotlin.extensions.collections.generic.collection.operations

import csense.kotlin.extensions.collections.generic.collection.*

public inline fun <T> GenericCollections.indexOfOrNull(
    byRange: IntProgression,
    getElement: (index: Int) -> T,
    predicate: (element: T) -> Boolean
): Int? = indexOfIndexedOrNull(
    byRange = byRange,
    getElement = getElement,
    predicate = { _: Int, element: T ->
        predicate(element)
    }
)