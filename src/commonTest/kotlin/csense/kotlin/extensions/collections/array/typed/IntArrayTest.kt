package csense.kotlin.extensions.collections.array

import csense.kotlin.extensions.collections.array.typed.int.*
import csense.kotlin.extensions.mapping.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class IntArrayTest {

    class IntArrayForEachBackwards {
        @Test
        fun empty() {
            intArrayOf().forEachBackwards { shouldNotBeCalled() }
        }

        @Test
        fun single() = assertCalled(times = 1) { shouldBeCalled ->
            intArrayOf(11).forEachBackwards {
                it.assert(11)
                shouldBeCalled()
            }
        }

        @Test
        fun multiple() = assertCalled(times = 2) { shouldBeCalled ->
            var toggle = false
            intArrayOf(11, 1).forEachBackwards {
                it.assert(toggle.map(11, 1))
                toggle = true
                shouldBeCalled()
            }
        }

    }

    class IntArrayForEachDiscard {
        @Test
        fun empty() {
            intArrayOf().forEachDiscard { shouldNotBeCalled() }
        }

        @Test
        fun single() = assertCalled(times = 1) { shouldBeCalled ->
            intArrayOf(11).forEachDiscard {
                it.assert(11)
                shouldBeCalled()
                return@forEachDiscard ""
            }
        }

        @Test
        fun multiple() = assertCalled(times = 2) { shouldBeCalled ->
            var toggle = false
            intArrayOf(11, 1).forEachDiscard {
                it.assert(toggle.map(1, 11))
                toggle = true
                shouldBeCalled()
                return@forEachDiscard ""
            }
        }
    }
}