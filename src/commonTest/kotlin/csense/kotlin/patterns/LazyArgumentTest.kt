package csense.kotlin.patterns

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class LazyArgumentTest {

    @Test
    fun invoke() {
        var ctorCalls = 0
        val arg = LazyArgument<String>()
        arg { ctorCalls += 1; "value" }.assert("value")
        ctorCalls.assert(1)
        arg.invoke { shouldNotBeCalled() }.assert("value")
        ctorCalls.assert(1)
    }

    @Test
    fun getValue() {
        var ctorCalls = 0
        val arg = LazyArgument<String>()
        arg.getValue { ctorCalls += 1; "value" }.assert("value")
        ctorCalls.assert(1)
        arg.getValue { shouldNotBeCalled() }.assert("value")
        ctorCalls.assert(1)
    }
}