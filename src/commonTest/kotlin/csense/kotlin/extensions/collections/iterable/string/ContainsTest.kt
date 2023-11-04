@file:Suppress("unused")

package csense.kotlin.extensions.collections.iterable.string

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class ContainsTest {
    class IterableStringContains {
        @Test
        fun empty() {
            listOf<String>().contains(other = "", ignoreCase = false).assertFalse()
            listOf<String>().contains(other = "", ignoreCase = true).assertFalse()
        }

        @Test
        fun single() {
            //all can find the a
            listOf("a").contains(other = "a", ignoreCase = false).assertTrue()
            listOf("a").contains(other = "a", ignoreCase = true).assertTrue()

            // all casing combinations
            listOf("a").contains(other = "A", ignoreCase = false).assertFalse()
            listOf("a").contains(other = "A", ignoreCase = true).assertTrue()

            listOf("A").contains(other = "a", ignoreCase = false).assertFalse()
            listOf("A").contains(other = "a", ignoreCase = true).assertTrue()

            listOf("A").contains(other = "A", ignoreCase = false).assertTrue()
            listOf("A").contains(other = "A", ignoreCase = true).assertTrue()

            //not there
            listOf("b").contains(other = "a", ignoreCase = false).assertFalse()
            listOf("b").contains(other = "a", ignoreCase = true).assertFalse()

            listOf("b").contains(other = "A", ignoreCase = false).assertFalse()
            listOf("b").contains(other = "A", ignoreCase = true).assertFalse()
        }

        @Test
        fun multiple() {
            //not there
            listOf("a", "b").contains(other = "c", ignoreCase = true).assertFalse()
            listOf("a", "b").contains(other = "c", ignoreCase = true).assertFalse()

            listOf("a", "b").contains(other = "C", ignoreCase = true).assertFalse()
            //there
            listOf("a", "b", "c").contains(other = "C", ignoreCase = true).assertTrue()

            //and some more for sanity’s sake.
            listOf("1234", "12345").contains(other = "12345", ignoreCase = true).assertTrue()
            listOf("1234", "12345").contains(other = "12345", ignoreCase = false).assertTrue()

            listOf("1234", "12345").contains(other = "1234", ignoreCase = true).assertTrue()
            listOf("1234", "12345").contains(other = "1234", ignoreCase = false).assertTrue()

            listOf("12345", "1234").contains(other = "1234", ignoreCase = true).assertTrue()
            listOf("12345", "1234").contains(other = "1234", ignoreCase = false).assertTrue()

        }
    }
    @Test
    fun doesNotContain(){
        TODO()
    }
}