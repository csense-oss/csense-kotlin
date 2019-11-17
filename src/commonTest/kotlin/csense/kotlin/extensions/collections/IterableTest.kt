package csense.kotlin.extensions.collections

import csense.kotlin.tests.assertions.assertCalled
import csense.kotlin.tests.assertions.assertFalse
import csense.kotlin.tests.assertions.assertTrue
import csense.kotlin.tests.assertions.failTest
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
}