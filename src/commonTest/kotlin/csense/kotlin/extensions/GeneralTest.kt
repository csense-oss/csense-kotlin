package csense.kotlin.extensions

import csense.kotlin.tests.assertions.*
import kotlin.test.*


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
        val data: Unit =
            fnc1("") //this would case a complie time error, and thus validating that the function does what
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

        @Suppress("RedundantNullableReturnType")
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

    @Test
    fun tOrIfNull() {
        val optString: String? = null
        optString.orIfNull("test").assert("test")
        optString.orIfNull("1234").assert("1234")

        @Suppress("RedundantNullableReturnType")
        val str: String? = "test"
        str.orIfNull("1234").assert("test")

        @Suppress("RedundantNullableReturnType")
        val number: Int? = 42
        number.orIfNull(11).assert(42)
        val nullNumber: Int? = null
        nullNumber.orIfNull(11).assert(11)
    }

    @Test
    fun tOrIfNullLazy() {
        val optInt: Int? = null
        optInt.orIfNullLazy { 99 }.assert(99)

        @Suppress("RedundantNullableReturnType")
        val optInt2: Int? = 42
        optInt2.orIfNullLazy { 111 }.assert(42)
    }

    @Test
    fun anyCastMap() {
        "".castMap<String, Int> { 32 }.assertNotNullAndEquals(32)
        "".castMap<Int, Int> { 32 }.assertNull()
        80.castMap<Number, Int> { this.toInt() }.assertNotNullAndEquals(80)
    }

    class TApplyIf {
        @Test
        fun shouldNotApply() {
            "value".applyIf(false) {
                shouldNotBeCalled()
            }.assert("value")
        }

        @Test
        fun shouldApply() = assertCalled { shouldBeCalled ->
            "test".applyIf(true) {
                shouldBeCalled()
                it.assert("test")
                "1234"
            }.assert("1234")
        }
    }
}