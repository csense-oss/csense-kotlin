package csense.kotlin.extensions.collections.array.typed

import csense.kotlin.extensions.collections.array.typed.float.*
import csense.kotlin.extensions.mapping.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class FloatArrayTest {

    class FloatArrayForEachBackwards {
        @Test
        fun empty() {
            floatArrayOf().forEachBackwards { shouldNotBeCalled() }
        }

        @Test
        fun single() = assertCalled(times = 1) { shouldBeCalled ->
            floatArrayOf(11.2f).forEachBackwards {
                it.assert(11.2f)
                shouldBeCalled()
            }
        }

        @Test
        fun multiple() = assertCalled(times = 2) { shouldBeCalled ->
            var toggle = false
            floatArrayOf(11.2f, 0.5f).forEachBackwards {
                it.assert(toggle.map(11.2f, 0.5f))
                toggle = true
                shouldBeCalled()
            }
        }

    }

    class FloatArrayForEachDiscard {
        @Test
        fun empty() {
            floatArrayOf().forEachDiscard { shouldNotBeCalled() }
        }

        @Test
        fun single() = assertCalled(times = 1) { shouldBeCalled ->
            floatArrayOf(11.2f).forEachDiscard {
                it.assert(11.2f)
                shouldBeCalled()
                return@forEachDiscard ""
            }
        }

        @Test
        fun multiple() = assertCalled(times = 2) { shouldBeCalled ->
            var toggle = false
            floatArrayOf(11.2f, 0.5f).forEachDiscard {
                it.assert(toggle.map(0.5f, 11.2f))
                toggle = true
                shouldBeCalled()
                return@forEachDiscard ""
            }
        }
    }
}