package csense.kotlin.extensions

import csense.kotlin.extensions.general.*
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
        //It's supposed to type wise.
        counter.assert(1, "should still execute function even though we discard the result")
    }

    @Test
    fun tUseOr() {
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
        "test".invokeIsInstance<Int, Any> {
            failTest("should not be called")
        }
        assertCalled { didCall ->
            "testString".invokeIsInstance<String, Any> {
                it.assert("testString")
                didCall()
            }
        }
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
                assert("test")
            }.assert("test")
        }
    }

    @Test
    fun isAnyNullFirst() {
        isAnyNull(null).assertTrue()
        isAnyNull(42).assertFalse()
    }

    @Test
    fun isAnyNullFirstSecond() {
        isAnyNull(null, null).assertTrue()
        isAnyNull(null, 42).assertTrue()
        isAnyNull(42, null).assertTrue()
        isAnyNull("", 42).assertFalse()
    }

    @Test
    fun isAnyNullFirstSecondThird() {
        isAnyNull(null, null, null).assertTrue()
        isAnyNull(null, null, 42).assertTrue()
        isAnyNull(null, 42, null).assertTrue()
        isAnyNull(42, null, null).assertTrue()

        isAnyNull(42, 42, null).assertTrue()
        isAnyNull(42, null, 42).assertTrue()
        isAnyNull(null, 42, 42).assertTrue()

        isAnyNull(42, 42, 42).assertFalse()
    }

    //at this point it is just a huge mess, thus instead of testing all variations, just do some edge and regular tests

    @Test
    fun isAnyNullFirstSecondThirdFourth() {
        isAnyNull(null, null, null, null).assertTrue()
        isAnyNull(true, "a", 2, listOf<String>()).assertFalse()

        isAnyNull(42, null, 42, null).assertTrue()
        isAnyNull(null, 42, null, 42).assertTrue()
    }


    @Test
    fun isAnyNullFirstSecondThirdFourthFifth() {
        isAnyNull(null, null, null, null, null).assertTrue()
        isAnyNull(true, "a", 2, listOf<String>(), "5").assertFalse()

        isAnyNull(42, null, 42, null, 11).assertTrue()
        isAnyNull(null, 42, null, 42, null).assertTrue()
    }


    @Test
    fun isAnyNullFirstSecondThirdFourthFifthSixth() {
        isAnyNull(null, null, null, null, null, null).assertTrue()
        isAnyNull(true, "a", 2, listOf<String>(), "5", "6").assertFalse()

        isAnyNull(42, null, 42, null, 11, null).assertTrue()
        isAnyNull(null, 42, null, 42, null, 11).assertTrue()
    }

    @Test
    fun isAnyNotNullFirst() {
        isAnyNotNull(42).assertTrue()
        isAnyNotNull(null).assertFalse()
    }

    @Test
    fun isAnyNotNullFirstSecond() {
        isAnyNotNull(null, 42).assertTrue()
        isAnyNotNull(42, 42).assertTrue()
        isAnyNotNull(42, null).assertTrue()
        isAnyNotNull(null, null).assertFalse()
    }

    @Test
    fun isAnyNotNullFirstSecondThird() {
        isAnyNotNull(null, null, 42).assertTrue()
        isAnyNotNull(null, 42, null).assertTrue()
        isAnyNotNull(null, 42, 42).assertTrue()
        isAnyNotNull(42, null, null).assertTrue()
        isAnyNotNull(42, null, 42).assertTrue()
        isAnyNotNull(42, 42, null).assertTrue()
        isAnyNotNull(42, 42, 42).assertTrue()
        isAnyNotNull(null, null, null).assertFalse()
    }

    @Test
    fun isAnyNotNullFirstSecondThirdFourth() {
        isAnyNotNull(null, null, null, null).assertFalse()
        isAnyNotNull(true, "a", 2, listOf<String>()).assertTrue()

        isAnyNotNull(42, null, 42, null).assertTrue()
        isAnyNotNull(null, 42, null, 42).assertTrue()
    }

    @Test
    fun isAnyNotNullFirstSecondThirdFourthFifth() {
        isAnyNotNull(null, null, null, null, null).assertFalse()
        isAnyNotNull(true, "a", 2, listOf<String>(), 42).assertTrue()

        isAnyNotNull(42, null, 42, null, 42).assertTrue()
        isAnyNotNull(null, 42, null, 42, null).assertTrue()
    }

    @Test
    fun isAnyNotNullFirstSecondThirdFourthFifthSixth() {
        isAnyNotNull(null, null, null, null, null, null).assertFalse()
        isAnyNotNull(true, "a", 2, listOf<String>(), 42, null).assertTrue()

        isAnyNotNull(42, null, 42, null, 42, null).assertTrue()
        isAnyNotNull(null, 42, null, 42, null, 42).assertTrue()

    }
}