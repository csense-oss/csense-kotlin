package csense.kotlin.extensions.ranges.progressions

import csense.kotlin.extensions.progressions.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class IntDownToExcluding {
    @Test
    fun excludes() {
        val input: IntProgression = 2.downToExcluding(0)
        input.first.assert(2)
        input.last.assert(1)
        input.length.assert(2)
    }
}

class IntLengthTo {

    @Test
    fun lengthShouldDecrementFromStarting() {
        val result: IntProgression = 10.lengthTo(0)
        result.first.assert(
            expected = 9,
            message = "should start at 9, due this being the length (10 would be index out of bounds for say a list / array"
        )
        result.last.assert(0)
    }

    @Test
    fun respectsEnd() {
        val result: IntProgression = 10.lengthTo(5)
        result.first.assert(expected = 9)
        result.last.assert(5)
    }
}
