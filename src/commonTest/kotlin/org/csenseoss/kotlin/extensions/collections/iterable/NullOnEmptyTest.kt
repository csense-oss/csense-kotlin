package org.csenseoss.kotlin.extensions.collections.iterable

import org.csenseoss.kotlin.tests.assertions.collections.iterable.*
import org.csenseoss.kotlin.tests.assertions.general.*
import kotlin.test.*

class NullOnEmptyTest {
    @Test
    fun empty() {
        listOf<String>()
            .nullOnEmpty()
            .assertNull("as the name suggest, should be null on empty")
    }

    @Test
    fun single() {
        listOf("a")
            .nullOnEmpty()
            .assert("a", message = "should not be null and have a single element that is 'a'")
    }

    @Test
    fun multiple() {
        val lst: List<String>? = listOf("1", "b", "3").nullOnEmpty()
        lst.assert("1", "b", "3", message = "should not be null and have a 3 elements")
    }
}