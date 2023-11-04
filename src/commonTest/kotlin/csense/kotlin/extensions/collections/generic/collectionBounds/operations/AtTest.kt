package csense.kotlin.extensions.collections.generic.collectionBounds.operations

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class AtTest {

    @Test
    fun collectionBoundsAtStart() {

        intArrayOf().isIndex.atStart(0).assertTrue()

        intArrayOf().isIndex.atStart(1).assertFalse()
        intArrayOf().isIndex.atStart(-1).assertFalse()

        intArrayOf(1).isIndex.atStart(0).assertTrue()
        intArrayOf(1).isIndex.atStart(-1).assertFalse()
        intArrayOf(1).isIndex.atStart(1).assertFalse()
        intArrayOf(1).isIndex.atStart(2).assertFalse()
    }

    @Test
    fun collectionBoundsAtLast() {
        intArrayOf().isIndex.atLast(-1).assertFalse()
        intArrayOf().isIndex.atLast(0).assertFalse()
        intArrayOf().isIndex.atLast(1).assertFalse()

        intArrayOf(1).isIndex.atLast(-1).assertFalse()
        intArrayOf(1).isIndex.atLast(0).assertTrue()
        intArrayOf(1).isIndex.atLast(1).assertFalse()

        intArrayOf(1,2,3,4).isIndex.atLast(-1).assertFalse()
        intArrayOf(1,2,3,4).isIndex.atLast(0).assertFalse()
        intArrayOf(1,2,3,4).isIndex.atLast(1).assertFalse()
        intArrayOf(1,2,3,4).isIndex.atLast(2).assertFalse()
        intArrayOf(1,2,3,4).isIndex.atLast(3).assertTrue()
        intArrayOf(1,2,3,4).isIndex.atLast(4).assertFalse()

    }

    @Test
    fun collectionBoundsAtAnyEnds() {

        intArrayOf().isIndex.atAnyEnds(-1).assertFalse()
        intArrayOf().isIndex.atAnyEnds(0).assertTrue()
        intArrayOf().isIndex.atAnyEnds(1).assertFalse()

        intArrayOf(1).isIndex.atAnyEnds(-1).assertFalse()
        intArrayOf(1).isIndex.atAnyEnds(0).assertTrue()
        intArrayOf(1).isIndex.atAnyEnds(1).assertFalse()

        intArrayOf(1,2,3,4).isIndex.atAnyEnds(-1).assertFalse()
        intArrayOf(1,2,3,4).isIndex.atAnyEnds(0).assertTrue()
        intArrayOf(1,2,3,4).isIndex.atAnyEnds(1).assertFalse()
        intArrayOf(1,2,3,4).isIndex.atAnyEnds(2).assertFalse()
        intArrayOf(1,2,3,4).isIndex.atAnyEnds(3).assertTrue()
        intArrayOf(1,2,3,4).isIndex.atAnyEnds(4).assertFalse()
    }

}