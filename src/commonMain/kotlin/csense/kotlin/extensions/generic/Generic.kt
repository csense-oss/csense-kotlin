@file:Suppress("unused")

package csense.kotlin.extensions.generic

import csense.kotlin.*
import csense.kotlin.annotations.numbers.*

public object Generic

//TODO "buildWith" ish???
public inline fun <Element, Result> Generic.forEachWith(
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
 * A very generic foreach, should work with any kind of design.
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
): Unit = forEachWith(Unit, length, retriever, startIndex) {
    onEach(it)
}

public inline fun <ElementIn, ElementOut> Generic.map(
    @IntLimit(from = 0) length: Int,
    retriever: Function1<@IntLimit(from = 0) Int, ElementIn>,
    @IntLimit(from = 0) startIndex: Int = 0,
    mapper: Function1<ElementIn, ElementOut>
): List<ElementOut> = forEachWith(ArrayList(length), length, retriever, startIndex) {
    this += mapper(it)
}


public inline fun <Element> Generic.filter(
    @IntLimit(from = 0) length: Int,
    retriever: Function1<@IntLimit(from = 0) Int, Element>,
    @IntLimit(from = 0) startIndex: Int = 0,
    predicate: Function1<Element, Boolean>
): List<Element> = forEachWith(ArrayList(length), length, retriever, startIndex) {
    if (predicate(it)) {
        this += it
    }
}