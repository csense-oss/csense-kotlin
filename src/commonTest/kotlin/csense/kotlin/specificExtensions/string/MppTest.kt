package csense.kotlin.specificExtensions.string

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class MppTest {
    class StringMppInsertInto {
        @Test
        fun empty() {
            "".mpp.insertInto().assert("")
        }

        @Test
        fun singleInsertAfter() {
            "a".mpp.insertInto(
                StringMpp.StringInserts(
                    "b", 1
                )
            ).assert("ab")
        }

        @Test
        fun singleInsertBefore() {
            "a".mpp.insertInto(
                StringMpp.StringInserts(
                    "b", 0
                )
            ).assert("ba")
        }

        @Test
        fun singleInsertNegativeIndex() {
            "a".mpp.insertInto(
                StringMpp.StringInserts(
                    "b", -1
                )
            ).assertNull("negative insert index")
        }

        @Test
        fun singleInsertTooLargeIndex() {
            "a".mpp.insertInto(
                StringMpp.StringInserts(
                    "b", 5
                )
            ).assertNull("index 5 is way overflowing \"a\"")
        }


    }

    @Test
    fun stringMpp() {
        val first = "".mpp
        val second = " ".mpp
        first.string.assert("")
        second.string.assert(" ")
        assertNotEquals(first, second)
    }
}