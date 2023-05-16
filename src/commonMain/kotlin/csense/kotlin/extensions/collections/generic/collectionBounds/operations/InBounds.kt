package csense.kotlin.extensions.collections.generic.collectionBounds.operations

import csense.kotlin.extensions.collections.generic.collectionBounds.*
import csense.kotlin.extensions.mapping.*


public fun CollectionBounds.inBounds(index: Int, isEndInBounds: Boolean): Boolean {
    return isEndInBounds.map(
        ifTrue = ::inBoundsEndInBounds,
        ifFalse = ::inBoundsEndNotInBounds
    ).invoke(index)
}
