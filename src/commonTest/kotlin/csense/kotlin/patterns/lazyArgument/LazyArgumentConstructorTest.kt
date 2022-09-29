package csense.kotlin.patterns.lazyArgument

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class LazyArgumentConstructorTest {
    @Test
    fun invoke() {
        var ctorCalls = 0
        val arg = LazyArgumentConstructor<String>()
        arg { ctorCalls += 1; "value" }.assert("value")
        ctorCalls.assert(1)
        arg.invoke { shouldNotBeCalled() }.assert("value")
        ctorCalls.assert(1)
    }

    @Test
    fun getValue() {
        var ctorCalls = 0
        val arg = LazyArgumentConstructor<String>()
        arg.getValue { ctorCalls += 1; "value" }.assert("value")
        ctorCalls.assert(1)
        arg.getValue { shouldNotBeCalled() }.assert("value")
        ctorCalls.assert(1)
    }

}