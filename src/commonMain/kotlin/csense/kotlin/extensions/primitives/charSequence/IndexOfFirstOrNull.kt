package csense.kotlin.extensions.primitives.charSequence

import csense.kotlin.annotations.numbers.*
import csense.kotlin.extensions.collections.*
import csense.kotlin.extensions.collections.generic.collection.*
import csense.kotlin.extensions.collections.generic.collection.operations.*


@IntLimit(from = 0)
public inline fun CharSequence.indexOfFirstOrNull(
    @IntLimit(from = 0) startIndex: Int = 0,
    predicate: Predicate<Char>
): Int? = indexOfFirstIndexedOrNull(startIndex = startIndex) { _: Int, char: Char ->
    predicate(char)
}

@IntLimit(from = 0)
public inline fun CharSequence.indexOfFirstIndexedOrNull(
    @IntLimit(from = 0) startIndex: Int = 0,
    predicate: PredicateIndexed<Char>
): Int? {
    return GenericCollections.indexOfFirstIndexedOrNull(
        startIndex = startIndex,
        length = length,
        getElement = this::get,
        predicate = predicate
    )
}

