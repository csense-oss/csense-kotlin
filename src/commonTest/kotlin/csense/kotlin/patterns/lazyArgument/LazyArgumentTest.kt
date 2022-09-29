package csense.kotlin.patterns.lazyArgument

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class LazyArgumentTest {

    class Invoke {
        @Test
        fun shouldCacheValue() = assertCalled { shouldBeCalled ->
            val arg = LazyArgument { _: String ->
                shouldBeCalled()
                42
            }
            arg("value").assert(42)
            arg("test").assert(42)
        }

        @Test
        fun shouldGetRightArgument() {
            val arg = LazyArgument { it: String ->
                it.assert("value")
                11
            }
            arg("value").assert(11)
        }
    }


    @Test
    fun getValue() = assertCalled { shouldBeCalled ->
        val arg = LazyArgument { _: String ->
            shouldBeCalled()
            11
        }
        arg.getValue("").assert(11)
        arg.getValue("").assert(11)
    }

    @Test
    fun lazyArgument() = assertCalled { shouldBeCalled ->
        val arg = lazyArgument { num: Int ->
            shouldBeCalled()
            num
        }
        arg.getValue(42).assert(42)
        arg.getValue(1234).assert(42, "should remember 42")
    }
}