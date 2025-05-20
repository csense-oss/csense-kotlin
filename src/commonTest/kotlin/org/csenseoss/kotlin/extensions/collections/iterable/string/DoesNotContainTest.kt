package org.csenseoss.kotlin.extensions.collections.iterable.string

import org.csenseoss.kotlin.tests.assertions.primitives.boolean.*
import kotlin.contracts.*
import kotlin.test.*

class DoesNotContainTest {

    @Test
    fun empty() {
        val list: Iterable<String> = listOf()
        list.doesNotContain("", ignoreCase = false).assertTrue()
    }

    @Test
    fun singleNotContained() {
        val list: Iterable<String> = listOf("test")
        list.doesNotContain("1234", ignoreCase = false).assertTrue()
    }

    @Test
    fun singleContained() {
        val list: Iterable<String> = listOf("test")
        list.doesNotContain("test", ignoreCase = false).assertFalse()
    }

    @Test
    fun singleContainedByCase() {
        val list: Iterable<String> = listOf("TEST")
        list.doesNotContain("test", ignoreCase = false).assertTrue()
        list.doesNotContain("test", ignoreCase = true).assertFalse()
    }
}