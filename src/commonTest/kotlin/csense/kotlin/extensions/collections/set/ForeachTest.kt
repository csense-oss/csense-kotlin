package csense.kotlin.extensions.collections.set

import csense.kotlin.extensions.mapping.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class ForeachTest {

    class SetTForeachBackwards {
        @Test
        fun empty() {
            setOf<String>().foreachBackwards { shouldNotBeCalled() }
        }

        @Test
        fun single() = assertCalled { shouldBeCalled: () -> Unit ->
            setOf("test").foreachBackwards { it: String ->
                it.assert("test")
                shouldBeCalled()
            }
        }

        @Test
        fun multiple() = assertCalled(times = 2) { shouldBeCalled: () -> Unit ->
            var haveCalled = false
            setOf("first", "last").foreachBackwards { it: String ->
                it.assert(haveCalled.map(ifTrue = "first", ifFalse = "last"))
                haveCalled = true
                shouldBeCalled()
            }
        }
    }
}