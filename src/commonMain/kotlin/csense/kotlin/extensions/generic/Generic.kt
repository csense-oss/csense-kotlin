@file:Suppress("unused", "UnusedReceiverParameter")

package csense.kotlin.extensions.generic

import csense.kotlin.annotations.numbers.*
import csense.kotlin.extensions.collections.*
import csense.kotlin.Function0
import csense.kotlin.Function1

//TODO consider moving...
public object Generic

/**
 * A very generic "mapEachWith" where access is delegated (via [retriever])
 * @receiver Generic
 * @param result Result
 * @param length Int
 * @param retriever Function1<Int, Element>
 * @param startIndex Int
 * @param append Function2<Result, Element, Unit>
 * @return Result
 */
public inline fun <Element, Result> Generic.mapEachWith(
    result: Result,
    @IntLimit(from = 0) length: Int,
    retriever: Function1<@IntLimit(from = 0) Int, Element>,
    @IntLimit(from = 0) startIndex: Int = 0,
    append: Result.(Element) -> Unit
): Result {
    for (i in startIndex until length) {
        result.append(retriever(i))
    }
    return result
}

/**
 * A very generic foreach where the access of items is delegated (via [retriever]).
 * @receiver [Generic]
 * @param onEach [Function0]<[Element]>
 * @param length [Int]
 * @param retriever [Function1]<[Int], [Element]>
 * @param startIndex [Int]
 */
public inline fun <Element> Generic.forEach(
    @IntLimit(from = 0) length: Int,
    retriever: Function1<@IntLimit(from = 0) Int, Element>,
    @IntLimit(from = 0) startIndex: Int = 0,
    onEach: Function0<Element>
): Unit = mapEachWith(Unit, length, retriever, startIndex) {
    onEach(it)
}

public inline fun <ElementIn, ElementOut> Generic.map(
    @IntLimit(from = 0) length: Int,
    retriever: Function1<@IntLimit(from = 0) Int, ElementIn>,
    @IntLimit(from = 0) startIndex: Int = 0,
    mapper: Function1<ElementIn, ElementOut>
): List<ElementOut> = mapEachWith(ArrayList(length), length, retriever, startIndex) {
    this += mapper(it)
}


public inline fun <Element> Generic.filter(
    @IntLimit(from = 0) length: Int,
    retriever: Function1<@IntLimit(from = 0) Int, Element>,
    @IntLimit(from = 0) startIndex: Int = 0,
    predicate: Predicate<Element>
): List<Element> = mapEachWith(ArrayList(length), length, retriever, startIndex) {
    if (predicate(it)) {
        this += it
    }
}

/**
 * Traverse a given hierarchy while the [getNextLevel] gives the next/parent level (depending on traversal direction)
 * @param start Element Where to start
 * @param processCurrentLevel Function1<Element, Unit> What to do for each level in the hierarchy
 * @param getNextLevel Function1<Element, Element?> how to progress in the hierarchy
 */
public inline fun <Element> Generic.traverseWhileNotNull(
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


//TODO hm!?
public inline fun <Element> Generic.traverseWithPreviousWhileNotNull(
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

/**
 * Traverse a given hierarchy while the [getNextLevel] gives the next level /parent and while the element is unique via hashcode & equals.
 * @param start Element
 * @param processCurrentLevel Function1<Element, Unit>  What to do for each level in the hierarchy
 * @param getNextLevel Function1<Element, Element?>how to progress in the hierarchy
 */
public inline fun <Element> Generic.traverseWhileNotNullAndNoCycles(
    start: Element,
    processCurrentLevel: (Element) -> Unit,
    getNextLevel: (Element) -> Element?
) {
    val seenElements = mutableSetOf<Element>()
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
