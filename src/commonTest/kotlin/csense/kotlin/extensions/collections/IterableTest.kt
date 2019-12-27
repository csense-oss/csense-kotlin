package csense.kotlin.extensions.collections

import csense.kotlin.tests.assertions.*
import kotlin.test.Test

class IterableTest {

    @Test
    fun iterableTIsEmpty() {
        val empty: Iterable<String> = arrayListOf()
        empty.isEmpty().assertTrue()
        val single: Iterable<String> = arrayListOf("1")
        single.isEmpty().assertFalse()
        val multiple: Iterable<String> = arrayListOf("a", "b")
        multiple.isEmpty().assertFalse()
    }

    @Test
    fun iterableTIsNotEmpty() {
        val empty: Iterable<String> = arrayListOf()
        empty.isNotEmpty().assertFalse()
        val single: Iterable<String> = arrayListOf("1")
        single.isNotEmpty().assertTrue()
        val multiple: Iterable<String> = arrayListOf("a", "b")
        multiple.isNotEmpty().assertTrue()

    }

    @Test
    fun iterableTSkipIfEmptyOr() {
        val empty: Iterable<String> = arrayListOf()
        empty.skipIfEmptyOr { failTest("should not be called") }

        val single: Iterable<String> = arrayListOf("1")
        assertCalled { single.skipIfEmptyOr(it) }

        val multiple: Iterable<String> = arrayListOf("a", "b")
        assertCalled { multiple.skipIfEmptyOr(it) }
    }

    class iterableEForEachNotNull {
        @Test
        fun empty() {
            val itt: Iterable<String?> = listOf()
            itt.forEachNotNull { failTest("should not be called") }
        }

        @Test
        fun singelNull() {
            val itt: Iterable<String?> = listOf(null)
            itt.forEachNotNull { failTest("should not be called") }
        }

        @Test
        fun singleNotNull() = assertCalled {
            val itt: Iterable<String?> = listOf("asd")
            itt.forEachNotNull { it() }
        }

        @Test
        fun multiple() = assertCalled(times = 2) {
            val itt: Iterable<String?> = listOf("asd", "1234")
            itt.forEachNotNull { it() }
        }

        @Test
        fun multipleNull() = assertNotCalled {
            val itt: Iterable<String?> = listOf(null, null)
            itt.forEachNotNull { it() }
        }

        @Test
        fun multipleMixed() = assertCalled(times = 3) {
            val itt: Iterable<String?> =
                    listOf(null, "asd", "1234", null, "1")
            itt.forEachNotNull { it() }
        }
    }

}