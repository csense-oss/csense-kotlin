package csense.kotlin.extensions

import csense.kotlin.tests.assertions.*
import kotlin.test.Test


class GeneralTest {

    @Test
    fun cast() {
        val test: Any = ""
        test.cast<String>().assertNotNull()
        test.cast<Number>().assertNull()

    }


    @Test
    fun toUnit() {
        ("".toUnit() == Unit).assertTrue()
        (null.toUnit() == Unit).assertTrue()
    }

    @Test
    fun toUnitFunction() {
        var counter = 0
        val fnc1 = { _: String -> counter += 1; "" }.toUnitFunction()

        @Suppress("UNUSED_VARIABLE")
        val data: Unit = fnc1("") //this would case a complie time error, and thus validating that the function does what
        //its supposed to type wise.
        counter.assert(1, "should still execute function even though we discard the result")
    }

    @Test
    fun useOr() {
        var optStringCounter = 0
        val optString: String? = null
        optString.useOr({ failTest("null is not a string") }, { optStringCounter += 1 })
        optStringCounter.assert(1, "should run the ifNull Callback")

        var stringCounter = 0
        val stringValue: String? = "magic test"
        stringValue.useOr({ stringCounter += length }, { failTest("magic test is not null") })
        stringCounter.assert(stringValue?.length ?: 0, "should get the right string back.")
    }

    @Test
    fun anyIsNot() {
        42.isNot<Int>().assertFalse()
        "".isNot<String>().assertFalse()
        "".isNot<CharSequence>().assertFalse()
        42.isNot<Char>().assertTrue()
        42.0.isNot<Char>().assertTrue()
        42.0.isNot<Number>().assertFalse()
        listOf<String>().isNot<List<Double>>().assertFalse("Type erasure...")
    }

    @Test
    fun anyInvokeIsInstanceActionUnit() {
        "test".invokeIsInstance<Int> {
            failTest("should not be called")
        }
        assertCalled { didCall ->
            "testString".invokeIsInstance<String> {
                it.assert("testString")
                didCall()
            }
        }
    }

    @Test
    fun anyInvokeIsInstanceAction() {
        "test".invokeIsInstance<Int, Int> { it }.assertNull("string and int are not same type")
        "test".invokeIsInstance<String, String> { it }.assertNotNullAndEquals("test")
        42.invokeIsInstance<String, Int> { 42 }.assertNull()
    }
}