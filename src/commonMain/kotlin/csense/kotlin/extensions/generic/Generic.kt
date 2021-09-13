@file:Suppress("unused")

package csense.kotlin.extensions.generic

import csense.kotlin.*
import csense.kotlin.annotations.numbers.*

public object Generic


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
) {
    for (i in startIndex until length) {
        onEach(retriever(i))
    }
}

public inline fun <ElementIn, ElementOut> Generic.map(
    @IntLimit(from = 0) length: Int,
    retriever: Function1<@IntLimit(from = 0) Int, ElementIn>,
    @IntLimit(from = 0) startIndex: Int = 0,
    mapper: Function1<ElementIn, ElementOut>
): List<ElementOut> {

    val result = mutableListOf<ElementOut>()
    for (i in startIndex until length) {
        result += mapper(retriever(i))
    }
    return result
}


public inline fun <Element> Generic.filter(
    @IntLimit(from = 0) length: Int,
    retriever: Function1<@IntLimit(from = 0) Int, Element>,
    @IntLimit(from = 0) startIndex: Int = 0,
    filterFunction: Function1<Element, Boolean>
): List<Element> {
    val result = mutableListOf<Element>()
    forEach(length, retriever, startIndex) {
        if (filterFunction(it)) {
            result += it
        }
    }
    return result
}