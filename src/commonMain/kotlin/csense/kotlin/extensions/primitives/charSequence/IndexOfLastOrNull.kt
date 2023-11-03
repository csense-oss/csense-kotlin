package csense.kotlin.extensions.primitives.charSequence

import csense.kotlin.extensions.collections.*
import csense.kotlin.extensions.collections.generic.collection.*
import csense.kotlin.extensions.collections.generic.collection.operations.*

public inline fun CharSequence.indexOfLastOrNull(
    startIndex: Int = 0,
    predicate: Predicate<Char>
): Int? = indexOfLastIndexedOrNull(startFromEndIndex = startIndex) { _: Int, char: Char ->
    predicate(char)
}

public inline fun CharSequence.indexOfLastIndexedOrNull(
    startFromEndIndex: Int = 0,
    predicate: (Int, Char) -> Boolean
): Int? {
    return GenericCollections.indexOfLastIndexedOrNull(
        startIndex = startFromEndIndex,
        length = length,
        getElement = this::get,
        predicate = predicate
    )
}