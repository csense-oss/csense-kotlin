@file:Suppress("unused")
package csense.kotlin.extensions.collections.array.typed.char

import csense.kotlin.extensions.mapping.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class ForeachTest {

    class CharArrayForEachBackwards {
        @Test
        fun empty() {
            charArrayOf().forEachBackwards { shouldNotBeCalled() }
        }

        @Test
        fun single() = assertCalled(times = 1) { shouldBeCalled: () -> Unit ->
            charArrayOf('a').forEachBackwards {
                it.assert('a')
                shouldBeCalled()
            }
        }

        @Test
        fun multiple() = assertCalled(times = 2) { shouldBeCalled: () -> Unit ->
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
        fun single() = assertCalled(times = 1) { shouldBeCalled: () -> Unit ->
            charArrayOf('a').forEachDiscard {
                it.assert('a')
                shouldBeCalled()
                return@forEachDiscard ""
            }
        }

        @Test
        fun multiple() = assertCalled(times = 2) { shouldBeCalled: () -> Unit ->
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