package org.csenseoss.kotlin.extensions.collections.set

import org.csenseoss.kotlin.extensions.mapping.*
import org.csenseoss.kotlin.tests.assertions.comparable.*
import org.csenseoss.kotlin.tests.assertions.general.*
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