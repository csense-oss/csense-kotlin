@file:Suppress("UnusedReceiverParameter")

package csense.kotlin.extensions.collections.generic.collection.operations

import csense.kotlin.extensions.collections.generic.collection.*

//TODO merge with traversal..
public inline fun <T> GenericCollections.walkUpFirstOrNull(
    startingPoint: T,
    getToNextLevelOrNull: (T) -> T?,
    predicate: (T) -> Boolean
): T? {
    walkUp(startingPoint, getToNextLevelOrNull) { it: T ->
        if (predicate(it)) {
            return@walkUpFirstOrNull it
        }
    }
    return null
}

//TODO merge with traversal..
public inline fun <T> GenericCollections.walkUp(
    startingPoint: T,
    getToNextLevelOrNull: (T) -> T?,
    onEachLevel: (T) -> Unit
) {
    var currentElement: T? = getToNextLevelOrNull(startingPoint)
    while (currentElement != null) {
        onEachLevel(currentElement)
        currentElement = getToNextLevelOrNull(currentElement)
    }
}