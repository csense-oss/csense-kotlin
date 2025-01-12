@file:Suppress("unused")

package org.csenseoss.kotlin.extensions.collections.generic.collectionBounds.operations

import org.csenseoss.kotlin.extensions.collections.generic.collectionBounds.*
import org.csenseoss.kotlin.tests.assertions.*
import org.csenseoss.kotlin.tests.assertions.primitives.boolean.*
import kotlin.test.*

class InBoundsTest {


    class CollectionBoundsInBounds {

        @Test
        fun endInBounds() {
            CollectionBounds.CollectionBoundsAlwaysOutOfBounds.inBounds(index = 0, isEndInBounds = true).assertFalse()

            CollectionBounds.CollectionBoundsZeroBoundsChecker.inBounds(index = -1, isEndInBounds = true).assertFalse()
            CollectionBounds.CollectionBoundsZeroBoundsChecker.inBounds(index = 0, isEndInBounds = true).assertTrue()
            CollectionBounds.CollectionBoundsZeroBoundsChecker.inBounds(index = 1, isEndInBounds = true).assertFalse()

            CollectionBounds.CollectionBoundsChecker(collectionLength = 1).inBounds(index = -1, isEndInBounds = true).assertFalse()
            CollectionBounds.CollectionBoundsChecker(collectionLength = 1).inBounds(index = 0, isEndInBounds = true).assertTrue()
            CollectionBounds.CollectionBoundsChecker(collectionLength = 1).inBounds(index = 1, isEndInBounds = true).assertTrue()
            CollectionBounds.CollectionBoundsChecker(collectionLength = 1).inBounds(index = 2, isEndInBounds = true).assertFalse()
        }

        @Test
        fun endOutOfBounds() {
            CollectionBounds.CollectionBoundsAlwaysOutOfBounds.inBounds(index = 0, isEndInBounds = false).assertFalse()

            CollectionBounds.CollectionBoundsZeroBoundsChecker.inBounds(index = -1, isEndInBounds = false).assertFalse()
            CollectionBounds.CollectionBoundsZeroBoundsChecker.inBounds(index = 0, isEndInBounds = false).assertFalse()
            CollectionBounds.CollectionBoundsZeroBoundsChecker.inBounds(index = 1, isEndInBounds = false).assertFalse()

            CollectionBounds.CollectionBoundsChecker(collectionLength = 1).inBounds(index = -1, isEndInBounds = false).assertFalse()
            CollectionBounds.CollectionBoundsChecker(collectionLength = 1).inBounds(index = 0, isEndInBounds = false).assertTrue()
            CollectionBounds.CollectionBoundsChecker(collectionLength = 1).inBounds(index = 1, isEndInBounds = false).assertFalse()
            CollectionBounds.CollectionBoundsChecker(collectionLength = 1).inBounds(index = 2, isEndInBounds = false).assertFalse()
        }
    }
}