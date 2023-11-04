@file:Suppress("unused")

package csense.kotlin.extensions.collections.array.typed.byte

import csense.kotlin.extensions.mapping.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class ForeachTest {




    class ByteArrayForEachBackwards {
        @Test
        fun empty() {
            byteArrayOf().forEachBackwards { shouldNotBeCalled() }
        }

        @Test
        fun single() = assertCalled(times = 1) { shouldBeCalled: () -> Unit ->
            byteArrayOf(42).forEachBackwards {
                it.assert(42)
                shouldBeCalled()
            }
        }

        @Test
        fun multiple() = assertCalled(times = 2) { shouldBeCalled: () -> Unit ->
            var toggle = false
            byteArrayOf(42, 11).forEachBackwards {
                it.assert(toggle.map(42, 11))
                toggle = true
                shouldBeCalled()
            }
        }

    }

    class ByteArrayForEachDiscard {
        @Test
        fun empty() {
            byteArrayOf().forEachDiscard { shouldNotBeCalled() }
        }

        @Test
        fun single() = assertCalled(times = 1) { shouldBeCalled: () -> Unit ->
            byteArrayOf(42).forEachDiscard {
                it.assert(42)
                shouldBeCalled()
                return@forEachDiscard ""
            }
        }

        @Test
        fun multiple() = assertCalled(times = 2) { shouldBeCalled: () -> Unit ->
            var toggle = false
            byteArrayOf(42, 11).forEachDiscard {
                it.assert(toggle.map(11, 42))
                toggle = true
                shouldBeCalled()
                return@forEachDiscard ""
            }
        }

    }

}