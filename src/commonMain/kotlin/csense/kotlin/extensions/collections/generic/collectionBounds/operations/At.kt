package csense.kotlin.extensions.collections.generic.collectionBounds.operations

import csense.kotlin.extensions.collections.generic.collectionBounds.*


@Suppress("UnusedReceiverParameter")
public fun CollectionBounds.atStart(index: Int): Boolean {
    return index == 0
}

public fun CollectionBounds.atLast(index: Int): Boolean {
    return inBoundsEndNotInBounds(index) && !inBoundsEndNotInBounds(index + 1)
}

public fun CollectionBounds.atAnyEnds(index: Int): Boolean {
    return atStart(index) || atLast(index)
}
