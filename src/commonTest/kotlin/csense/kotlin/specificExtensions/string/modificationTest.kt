package csense.kotlin.specificExtensions.string

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class modificationTest {
    @Test
    fun stringModificationLimitTo() {
        "".modifications.limitTo(0).assert("")
        "".modifications.limitTo(-1).assert("")
        "".modifications.limitTo(1).assert("")
        "abc".modifications.limitTo(0).assert("")
        "abc".modifications.limitTo(-2).assert("")
        "abc".modifications.limitTo(2).assert("ab")
        "abc".modifications.limitTo(1).assert("a")
        "abc".modifications.limitTo(3).assert("abc")
        "abc".modifications.limitTo(10).assert("abc")
    }
    
    @Test
    fun stringModificationWrapIn() {
        "".modifications.wrapIn("", "").assert("")
        "".modifications.wrapIn("aa", "").assert("aa")
        "".modifications.wrapIn("", "bb").assert("bb")
        "".modifications.wrapIn("a", "b").assert("ab")
        "1234".modifications.wrapIn("<", ">").assert("<1234>")
        "1234".modifications.wrapIn("<", "").assert("<1234")
        "1234".modifications.wrapIn("", ">").assert("1234>")
        "1234".modifications.wrapIn("", "").assert("1234")
        
    }
    
    @Test
    fun stringModificationWrapInQuotes() {
        "".modifications.wrapInQuotes().assert("\"\"")
        "a".modifications.wrapInQuotes().assert("\"a\"")
        "\"".modifications.wrapInQuotes().assert("\"\"\"")
        "yzz x".modifications.wrapInQuotes().assert("\"yzz x\"")
    }
}