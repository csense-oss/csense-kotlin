@file:Suppress("UnusedReceiverParameter")

package csense.kotlin.extensions.collections.generic.collection.operations

import csense.kotlin.extensions.collections.generic.collection.*
//TODO merge with walkup..

/**
 * Traverse a given hierarchy while the [getNextLevel] gives the next/parent level (depending on traversal direction)
 * @param start Element Where to start
 * @param processCurrentLevel Function1<Element, Unit> What to do for each level in the hierarchy
 * @param getNextLevel Function1<Element, Element?> how to progress in the hierarchy
 */
public inline fun <Element> GenericCollections.traverseWhileNotNull(
    start: Element,
    processCurrentLevel: (Element) -> Unit,
    getNextLevel: (Element) -> Element?,
) {
    var current: Element = start
    while (true) {
        processCurrentLevel(current)
        current = getNextLevel(current) ?: break
    }
}

//TODO merge with walkup..
//TODO hm!?
public inline fun <Element> GenericCollections.traverseWithPreviousWhileNotNull(
    start: Element,
    processCurrentLevel: (previous: Element, current: Element) -> Element,
    getNextLevel: (processResult: Element) -> Element?
): Element {
    var current: Element = start
    var previous: Element = start
    while (true) {
        current = getNextLevel(current) ?: break
        current = processCurrentLevel(previous, current)
        previous = current
    }
    return previous //or current?? would they not be the same here? .. hmm
}
//TODO merge with walkup..
/**
 * Traverse a given hierarchy while the [getNextLevel] gives the next level /parent and while the element is unique via hashcode & equals.
 * @param start Element
 * @param processCurrentLevel Function1<Element, Unit>  What to do for each level in the hierarchy
 * @param getNextLevel Function1<Element, Element?>how to progress in the hierarchy
 */
public inline fun <Element> GenericCollections.traverseWhileNotNullAndNoCycles(
    start: Element,
    processCurrentLevel: (Element) -> Unit,
    getNextLevel: (Element) -> Element?
) {
    val seenElements: MutableSet<Element> = mutableSetOf<Element>()
    traverseWhileNotNull(
        start = start,
        processCurrentLevel = { element: Element ->
            val isElementNew = seenElements.add(element)
            if (!isElementNew) {
                return@traverseWhileNotNullAndNoCycles
            }
            processCurrentLevel(element)
        }, getNextLevel = getNextLevel
    )
}
