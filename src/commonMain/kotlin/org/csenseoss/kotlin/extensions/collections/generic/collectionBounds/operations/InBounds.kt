package org.csenseoss.kotlin.extensions.collections.generic.collectionBounds.operations

import org.csenseoss.kotlin.extensions.collections.generic.collectionBounds.*
import org.csenseoss.kotlin.extensions.mapping.*


public fun CollectionBounds.inBounds(index: Int, isEndInBounds: Boolean): Boolean {
    return isEndInBounds.map(
        ifTrue = ::inBoundsEndInBounds,
        ifFalse = ::inBoundsEndNotInBounds
    ).invoke(index)
}