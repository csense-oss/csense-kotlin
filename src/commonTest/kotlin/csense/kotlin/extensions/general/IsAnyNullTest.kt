package csense.kotlin.extensions.general

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class IsAnyNullTest {

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