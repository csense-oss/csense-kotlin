package csense.kotlin.extensions.progressions

import csense.kotlin.test.assertions.*
import kotlin.test.*

class IntProgressionTest {

    @Test
    fun intProgressionToIntArray() {
        IntProgression.fromClosedRange(0, 9, 1) //[0, .. 9]
                .toIntArray().apply {
                    assertSize(10)
                    first().assert(0)
                    last().assert(9)
                }

        IntProgression.fromClosedRange(0, 9, 5)
                .toIntArray().apply {
                    assertSize(2)
                    first().assert(0)
                    last().assert(5)
                }
    }

    @Test
    fun intProgressionLength() {
        IntProgression.fromClosedRange(0, -50, 1).length.assert(-49)
        IntProgression.fromClosedRange(1, 0, 1).length.assert(0)
        IntProgression.fromClosedRange(0, 0, 1).length.assert(1)
        IntProgression.fromClosedRange(0, 1, 1).length.assert(2)
        IntProgression.fromClosedRange(0, 9, 5).length.assert(2)
        IntProgression.fromClosedRange(0, 9, 1).length.assert(10)
    }
}