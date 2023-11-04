package csense.kotlin.extensions.collections.array

import csense.kotlin.extensions.collections.array.typed.double.*
import csense.kotlin.extensions.mapping.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class DoubleArrayTest {
    class DoubleArrayForEachBackwards {
        @Test
        fun empty() {
            doubleArrayOf().forEachBackwards { shouldNotBeCalled() }
        }

        @Test
        fun single() = assertCalled(times = 1) { shouldBeCalled ->
            doubleArrayOf(11.2).forEachBackwards {
                it.assert(11.2)
                shouldBeCalled()
            }
        }

        @Test
        fun multiple() = assertCalled(times = 2) { shouldBeCalled ->
            var toggle = false
            doubleArrayOf(11.2, 0.5).forEachBackwards {
                it.assert(toggle.map(11.2, 0.5))
                toggle = true
                shouldBeCalled()
            }
        }

    }

    class DoubleArrayForEachDiscard {
        @Test
        fun empty() {
            doubleArrayOf().forEachDiscard { shouldNotBeCalled() }
        }

        @Test
        fun single() = assertCalled(times = 1) { shouldBeCalled ->
            doubleArrayOf(11.2).forEachDiscard {
                it.assert(11.2)
                shouldBeCalled()
                return@forEachDiscard ""
            }
        }

        @Test
        fun multiple() = assertCalled(times = 2) { shouldBeCalled ->
            var toggle = false
            doubleArrayOf(11.2, 0.5).forEachDiscard {
                it.assert(toggle.map(0.5, 11.2))
                toggle = true
                shouldBeCalled()
                return@forEachDiscard ""
            }
        }
    }
}