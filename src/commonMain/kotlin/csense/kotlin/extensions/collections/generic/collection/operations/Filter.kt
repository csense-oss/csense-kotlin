package csense.kotlin.extensions.collections.generic.collection.operations

import csense.kotlin.annotations.numbers.*
import csense.kotlin.extensions.collections.*
import csense.kotlin.extensions.collections.generic.collection.*


public inline fun <Element> GenericCollections.filter(
    @IntLimit(from = 0) length: Int,
    retriever: Function1<@IntLimit(from = 0) Int, Element>,
    @IntLimit(from = 0) startIndex: Int = 0,
    predicate: Predicate<Element>
): List<Element> = mapEachWith(ArrayList(length), length, retriever, startIndex) {
    if (predicate(it)) {
        this += it
    }
}