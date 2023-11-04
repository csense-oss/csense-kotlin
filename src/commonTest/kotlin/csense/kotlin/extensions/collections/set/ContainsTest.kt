@file:Suppress("unused")

package csense.kotlin.extensions.collections.set

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class ContainsTest {

    class SetTDoesNotContain {
        @Test
        fun empty() {
            val map: Set<String> = setOf()
            map.doesNotContain("1").assertTrue()
        }

        @Test
        fun single() {
            val map: Set<String> = setOf("1234")
            map.doesNotContain("1").assertTrue()
            map.doesNotContain("1234").assertFalse()
            map.doesNotContain("12345").assertTrue()
        }


        @Test
        fun multiple() {
            val map: Set<Int> = setOf(123, 444)
            map.doesNotContain(1).assertTrue()
            map.doesNotContain(2).assertTrue()
            map.doesNotContain(123).assertFalse()
            map.doesNotContain(444).assertFalse()
            map.doesNotContain(555).assertTrue()
        }

        @Test
        fun nullableShouldStillWork() {
            val map: Set<Int?> = setOf<Int?>(null)
            map.doesNotContain(null).assertFalse("contains null")
            map.doesNotContain(42).assertTrue()

        }
    }

    class SetEContainsAny {
        @Test
        fun emptySet() {
            setOf<String>().containsAny(listOf()).assertFalse()
            setOf<String>().containsAny(listOf("1")).assertFalse()
            setOf<String>().containsAny(listOf("1", "2")).assertFalse()
        }

        @Test
        fun emptyOther() {
            setOf("1", "2").containsAny(listOf()).assertFalse()
            setOf("1").containsAny(listOf()).assertFalse()
            setOf("1", "2", "3").containsAny(listOf()).assertFalse()
        }

        @Test
        fun singleItemInBoth() {
            setOf("1").containsAny(listOf("1")).assertTrue()
            setOf("1").containsAny(listOf("2")).assertFalse()
            setOf("3").containsAny(listOf("2")).assertFalse()
        }

        @Test
        fun multipleHaveItems() {

            setOf("1", "4").containsAny(listOf("1")).assertTrue()
            setOf("1", "4").containsAny(listOf("4")).assertTrue()
            setOf("1", "4").containsAny(listOf("5")).assertFalse()

            setOf("1", "2").containsAny(listOf("2", "1")).assertTrue()
            setOf("4", "6").containsAny(listOf("2", "1")).assertFalse()

            setOf("3", "9").containsAny(listOf("2", "1", "3")).assertTrue()
        }

        @Test
        fun sampleForSets() {
            setOf<String>().containsAny(setOf()).assertFalse()
            setOf("1").containsAny(setOf()).assertFalse()
            setOf<String>().containsAny(setOf("1")).assertFalse()
            setOf("1").containsAny(setOf("1")).assertTrue()
            setOf("1").containsAny(setOf("2")).assertFalse()

            setOf("2").containsAny(setOf("1", "3")).assertFalse()
            setOf("2").containsAny(setOf("1", "2")).assertTrue()

            setOf("2", "3").containsAny(setOf("1")).assertFalse()
            setOf("2", "3").containsAny(setOf("2")).assertTrue()
            setOf("2", "3").containsAny(setOf("3")).assertTrue()
            setOf("2", "3").containsAny(setOf("4")).assertFalse()
            setOf("2", "3").containsAny(setOf("4", "2")).assertTrue()
        }

        @Test
        fun nullableShouldWork() {
            setOf<String?>(null).containsAny(listOf()).assertFalse("null is not nothing")
            setOf<String?>(null).containsAny(listOf(null)).assertTrue("null is null")
            setOf<String?>(null).containsAny(listOf("a", null)).assertTrue("null is still contained")
        }
    }

    class SetEDoesNotContainAny {
        @Test
        fun empty() {
            setOf<String>().doesNotContainAny(listOf()).assertTrue("nothing does not contain anything")
            setOf<String>().doesNotContainAny(listOf("test")).assertTrue("nothing does not contain something")
        }

        @Test
        fun single() {
            setOf("test").doesNotContainAny(listOf()).assertTrue("something does not contains nothing")
            setOf("test").doesNotContainAny(listOf("test")).assertFalse("nothing does not contain something")
            setOf("test2").doesNotContainAny(listOf("test")).assertTrue("nothing does not contain something")
        }

        @Test
        fun multiple() {
            setOf("test", "1234").doesNotContainAny(listOf()).assertTrue("something does not contains nothing")
            setOf("test", "1234").doesNotContainAny(listOf("test")).assertFalse("nothing does not contain something")
            setOf("test", "1234").doesNotContainAny(listOf("1234")).assertFalse("nothing does not contain something")
            setOf("test", "1234").doesNotContainAny(listOf("a", "b")).assertTrue("nothing does not contain something")
            setOf("test", "1234").doesNotContainAny(listOf("a", "b", "test"))
                .assertFalse("nothing does not contain something")
            setOf("test2", "1234").doesNotContainAny(listOf("test")).assertTrue("nothing does not contain something")
        }

        @Test
        fun nullableShouldWork() {
            setOf<String?>(null).doesNotContainAny(listOf()).assertTrue("null is not nothing")
            setOf<String?>(null).doesNotContainAny(listOf(null)).assertFalse("null is null")
            setOf<String?>(null).doesNotContainAny(listOf("a", null)).assertFalse("null is still contained")
        }
    }

}