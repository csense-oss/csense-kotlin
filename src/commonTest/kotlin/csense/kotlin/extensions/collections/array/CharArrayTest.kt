package csense.kotlin.extensions.collections.array

import csense.kotlin.extensions.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class CharArrayTest {

    class CharArrayForEachBackwards {
        @Test
        fun empty() {
            charArrayOf().forEachBackwards { shouldNotBeCalled() }
        }

        @Test
        fun single() = assertCalled(times = 1) { shouldBeCalled ->
            charArrayOf('a').forEachBackwards {
                it.assert('a')
                shouldBeCalled()
            }
        }

        @Test
        fun multiple() = assertCalled(times = 2) { shouldBeCalled ->
            var toggle = false
            charArrayOf('a', 'b').forEachBackwards {
                it.assert(toggle.map('a', 'b'))
                toggle = true
                shouldBeCalled()
            }
        }

    }

    class CharArrayForEachDiscard {
        @Test
        fun empty() {
            charArrayOf().forEachDiscard { shouldNotBeCalled() }
        }

        @Test
        fun single() = assertCalled(times = 1) { shouldBeCalled ->
            charArrayOf('a').forEachDiscard {
                it.assert('a')
                shouldBeCalled()
                return@forEachDiscard ""
            }
        }

        @Test
        fun multiple() = assertCalled(times = 2) { shouldBeCalled ->
            var toggle = false
            charArrayOf('a', 'b').forEachDiscard {
                it.assert(toggle.map('b', 'a'))
                toggle = true
                shouldBeCalled()
                return@forEachDiscard ""
            }
        }

    }
}