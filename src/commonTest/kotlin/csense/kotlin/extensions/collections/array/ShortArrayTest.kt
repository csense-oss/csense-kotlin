package csense.kotlin.extensions.collections.array

import csense.kotlin.extensions.collections.array.typed.short.*
import csense.kotlin.extensions.mapping.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class ShortArrayTest {

    class ShortArrayForEachBackwards {
        @Test
        fun empty() {
            shortArrayOf().forEachBackwards { shouldNotBeCalled() }
        }

        @Test
        fun single() = assertCalled(times = 1) { shouldBeCalled ->
            shortArrayOf(11).forEachBackwards {
                it.assert(11.toShort())
                shouldBeCalled()
            }
        }

        @Test
        fun multiple() = assertCalled(times = 2) { shouldBeCalled ->
            var toggle = false
            shortArrayOf(11, 1).forEachBackwards {
                it.assert(toggle.map(11.toShort(), 1.toShort()))
                toggle = true
                shouldBeCalled()
            }
        }

    }

    class ShortArrayForEachDiscard {
        @Test
        fun empty() {
            shortArrayOf().forEachDiscard { shouldNotBeCalled() }
        }

        @Test
        fun single() = assertCalled(times = 1) { shouldBeCalled ->
            shortArrayOf(11).forEachDiscard {
                it.assert(11.toShort())
                shouldBeCalled()
                return@forEachDiscard ""
            }
        }

        @Test
        fun multiple() = assertCalled(times = 2) { shouldBeCalled ->
            var toggle = false
            shortArrayOf(11, 1).forEachDiscard {
                it.assert(toggle.map(1.toShort(), 11.toShort()))
                toggle = true
                shouldBeCalled()
                return@forEachDiscard ""
            }
        }
    }
}