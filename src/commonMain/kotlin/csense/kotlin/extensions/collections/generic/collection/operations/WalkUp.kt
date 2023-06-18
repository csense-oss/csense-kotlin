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

//TODO merge with traversal..
public fun <T> GenericCollections.walkUpAny(
    startingPoint: T,
    getToNextLevelOrNull: (T) -> T?,
    predicate: (T) -> Boolean
): Boolean {
    GenericCollections.walkUp(
        startingPoint = startingPoint,
        getToNextLevelOrNull = getToNextLevelOrNull,
        onEachLevel = { it: T ->
            val has: Boolean = predicate(it)
            if (has) {
                return@walkUpAny true
            }
        }
    )
    return false
}