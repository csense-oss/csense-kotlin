@file:Suppress("unused")

package csense.kotlin.extensions.collections.iterable

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class StringIterableTest {
    class IterableStringContains {
        @Test
        fun empty() {
            listOf<String>().contains("", false).assertFalse()
            listOf<String>().contains("", true).assertFalse()
        }

        @Test
        fun single() {
            //all can find the a
            listOf("a").contains("a", false).assertTrue()
            listOf("a").contains("a", true).assertTrue()

            // all casing combinations
            listOf("a").contains("A", false).assertFalse()
            listOf("a").contains("A", true).assertTrue()

            listOf("A").contains("a", false).assertFalse()
            listOf("A").contains("a", true).assertTrue()

            listOf("A").contains("A", false).assertTrue()
            listOf("A").contains("A", true).assertTrue()

            //not there
            listOf("b").contains("a", false).assertFalse()
            listOf("b").contains("a", true).assertFalse()

            listOf("b").contains("A", false).assertFalse()
            listOf("b").contains("A", true).assertFalse()
        }

        @Test
        fun multiple() {
            //not there
            listOf("a", "b").contains("c", true).assertFalse()
            listOf("a", "b").contains("c", true).assertFalse()

            listOf("a", "b").contains("C", true).assertFalse()
            //there
            listOf("a", "b", "c").contains("C", true).assertTrue()

            //and some more for sanity’s sake.
            listOf("1234", "12345").contains("12345", true).assertTrue()
            listOf("1234", "12345").contains("12345", false).assertTrue()

            listOf("1234", "12345").contains("1234", true).assertTrue()
            listOf("1234", "12345").contains("1234", false).assertTrue()

            listOf("12345", "1234").contains("1234", true).assertTrue()
            listOf("12345", "1234").contains("1234", false).assertTrue()

        }
    }
}