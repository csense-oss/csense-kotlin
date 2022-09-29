package csense.kotlin.patterns.lazyArgument

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class LazyArgument2Test {
    class Invoke {
        @Test
        fun shouldCacheValue() = assertCalled { shouldBeCalled ->
            val arg = LazyArgument2 { _: Int, _: String ->
                shouldBeCalled()
                42
            }
            arg(123, "value").assert(42)
            arg(1234, "test").assert(42)
        }

        @Test
        fun shouldGetRightArgument() {
            val arg = LazyArgument2 { num: Int, it: String ->
                it.assert("value")
                num.assert(111)
                22
            }
            arg(111, "value").assert(22)
        }
    }


    @Test
    fun getValue() = assertCalled { shouldBeCalled ->
        val arg = LazyArgument2 { _: Int, _: String ->
            shouldBeCalled()
            11
        }
        arg.getValue(123, "").assert(11)
        arg.getValue(456, "").assert(11)
    }

    @Test
    fun lazyArgument() = assertCalled { shouldBeCalled ->
        val arg = lazyArgument { num: Int, _: String ->
            shouldBeCalled()
            num
        }
        arg.getValue(42, "").assert(42)
        arg.getValue(1234, "message").assert(42, "should remember 42")
    }
}