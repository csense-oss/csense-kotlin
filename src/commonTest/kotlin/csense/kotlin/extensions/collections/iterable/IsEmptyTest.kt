package csense.kotlin.extensions.collections.iterable

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class IsEmptyTest {
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
}