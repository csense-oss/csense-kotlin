package org.csenseoss.kotlin.extensions.collections.iterable
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class NotInTest {
    class NotIn{
        @Test
        fun sanity() {
            val empty: List<String> = listOf()
            "test".notIn(empty).assertTrue("nothing is in empty")
            val single: List<String> = listOf("42")
            "42".notIn(single).assertFalse()
            "XX".notIn(single).assertTrue()
        }

    }
}