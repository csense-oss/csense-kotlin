package csense.kotlin.extensions.collections.generic.collectionBounds.operations

import csense.kotlin.extensions.collections.generic.collectionBounds.*
import csense.kotlin.extensions.mapping.*


public fun CollectionBounds.outOfBoundsEndOutOfBounds(index: Int): Boolean {
    return !inBoundsEndNotInBounds(index)
}

public fun CollectionBounds.outOfBoundsEndInBounds(index: Int): Boolean {
    return !inBoundsEndInBounds(index)
}


public fun CollectionBounds.outOfBounds(index: Int, isEndOutOfBonds: Boolean): Boolean {
    return isEndOutOfBonds.map(
        ifTrue = ::outOfBoundsEndOutOfBounds,
        ifFalse = ::outOfBoundsEndInBounds
    ).invoke(index)
}
