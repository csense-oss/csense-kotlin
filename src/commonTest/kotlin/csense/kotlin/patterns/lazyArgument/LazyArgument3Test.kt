package csense.kotlin.patterns.lazyArgument

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class LazyArgument3Test {
    class Invoke {
        @Test
        fun shouldCacheValue() = assertCalled { shouldBeCalled ->
            val arg = LazyArgument3 { _: Int, _: String, _: Boolean ->
                shouldBeCalled()
                42
            }
            arg(123, "value", false).assert(42)
            arg(1234, "test", true).assert(42)
        }

        @Test
        fun shouldGetRightArgument() {
            val arg = LazyArgument3 { num: Int, it: String, myBool: Boolean ->
                it.assert("value")
                myBool.assertTrue()
                num.assert(111)
                22
            }
            arg(111, "value", true).assert(22)
        }
    }


    @Test
    fun getValue() = assertCalled { shouldBeCalled ->
        val arg = LazyArgument3 { _: Int, _: String, _: Boolean ->
            shouldBeCalled()
            11
        }
        arg.getValue(123, "", false).assert(11)
        arg.getValue(456, "", true).assert(11)
    }

    @Test
    fun lazyArgument() = assertCalled { shouldBeCalled ->
        val arg = lazyArgument { num: Int, _: String, _: Boolean ->
            shouldBeCalled()
            num
        }
        arg.getValue(42, "", false).assert(42)
        arg.getValue(1234, "message", true).assert(42, "should remember 42")
    }
}