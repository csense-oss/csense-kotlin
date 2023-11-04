package csense.kotlin.extensions.collections.collection

import csense.kotlin.extensions.mapping.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class SelectTest {
    @Test
    fun emptySelect() {
        listOf<String>().selectFirstOrNull {
            it.length
        }.assertNull("cannot find anything in an empty list")
    }

    @Test
    fun emptySelectNotFound() {
        listOf<String>().selectFirstOrNull<String, String?> {
            null
        }.assertNull("cannot find anything in an empty list")
    }

    @Test
    fun singleSelect() {
        listOf("test").selectFirstOrNull {
            it.length
        }.assert(
            4,
            "length of test is 4 , and there is a single element in the collection"
        )
    }

    @Test
    fun singleSelectNotFound() {
        listOf("test").selectFirstOrNull<String, String?> {
            null
        }.assertNull("Non is ever selected, so should be null")
    }

    @Test
    fun multipleFound() {
        listOf("test", "asd").selectFirstOrNull {
            it.firstOrNull()
        }.assert('t', "test is first")

        listOf("test", "2").selectFirstOrNull {
            if (it.length == 1) {
                it.firstOrNull()
            } else {
                null
            }
        }.assert('2', "since we only return the char when the string has a length of 1")
    }

    @Test
    fun multipleNotFound() {
        listOf("test", "asd", "1234").selectFirstOrNull {
            (it.length > 10).map(this, null)
        }.assertNull("no string is larger than 10 chars.")
    }
}