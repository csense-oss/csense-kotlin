@file:Suppress("unused")
package csense.kotlin.extensions.collections.array.typed.long

import csense.kotlin.extensions.mapping.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class ForeachTest {

    class LongArrayForEachBackwards {
        @Test
        fun empty() {
            longArrayOf().forEachBackwards { shouldNotBeCalled() }
        }

        @Test
        fun single() = assertCalled(times = 1) { shouldBeCalled: () -> Unit ->
            longArrayOf(11L).forEachBackwards {
                it.assert(11L)
                shouldBeCalled()
            }
        }

        @Test
        fun multiple() = assertCalled(times = 2) { shouldBeCalled: () -> Unit ->
            var toggle = false
            longArrayOf(11L, 1L).forEachBackwards {
                it.assert(toggle.map(11L, 1L))
                toggle = true
                shouldBeCalled()
            }
        }

    }

    class LongArrayForEachDiscard {
        @Test
        fun empty() {
            longArrayOf().forEachDiscard { shouldNotBeCalled() }
        }

        @Test
        fun single() = assertCalled(times = 1) { shouldBeCalled: () -> Unit ->
            longArrayOf(11L).forEachDiscard {
                it.assert(11L)
                shouldBeCalled()
                return@forEachDiscard ""
            }
        }

        @Test
        fun multiple() = assertCalled(times = 2) { shouldBeCalled: () -> Unit ->
            var toggle = false
            longArrayOf(11L, 1L).forEachDiscard {
                it.assert(toggle.map(1L, 11L))
                toggle = true
                shouldBeCalled()
                return@forEachDiscard ""
            }
        }
    }
}