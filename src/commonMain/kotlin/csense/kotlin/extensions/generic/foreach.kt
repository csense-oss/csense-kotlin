@file:Suppress("unused")

package csense.kotlin.extensions.generic

import csense.kotlin.Function0
import csense.kotlin.Function1
import csense.kotlin.annotations.numbers.IntLimit

/**
 * A very generic foreach, should work with any kind of design.
 * @receiver [Generic]
 * @param onEach [Function0]<[Element]>
 * @param length [Int]
 * @param retriever [Function1]<[Int], [Element]>
 * @param startIndex [Int]
 */
inline fun <Element> Generic.forEach(
        @IntLimit(from = 0) length: Int,
        retriever: Function1<Int, Element>,
        @IntLimit(from = 0) startIndex: Int = 0,
        onEach: Function0<Element>
) {
    for (i in startIndex until length) {
        onEach(retriever(i))
    }
}

inline fun <ElementIn, ElementOut> Generic.map(
        @IntLimit(from = 0) length: Int,
        retriever: Function1<Int, ElementIn>,
        @IntLimit(from = 0) startIndex: Int = 0,
        mapper: Function1<ElementIn, ElementOut>
): List<ElementOut> {

    val result = mutableListOf<ElementOut>()
    for (i in startIndex until length) {
        result += mapper(retriever(i))
    }
    return result
}


inline fun <Element> Generic.filter(
        @IntLimit(from = 0) length: Int,
        retriever: Function1<Int, Element>,
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