@file:Suppress("unused")

package org.csenseoss.kotlin.extensions.collections.iterable

import org.csenseoss.kotlin.tests.assertions.*
import org.csenseoss.kotlin.tests.assertions.collections.iterable.*
import org.csenseoss.kotlin.tests.assertions.general.*
import kotlin.test.*

class TakeOrNullTest {

    class IterableTTakeOrNull {
        @Test
        fun empty() {
            val lst: Iterable<String> = listOf()
            lst.takeOrNull(0).assertEmpty()
            lst.takeOrNull(1).assertEmpty()
            lst.takeOrNull(-1).assertNull()
        }

        @Test
        fun single() {
            val lst: Iterable<String> = listOf("abc")
            lst.takeOrNull(0).assertEmpty()
            lst.takeOrNull(1).assert("abc")
            lst.takeOrNull(-1).assertNull()
        }

        @Test
        fun multiple() {
            val lst: Iterable<String> = listOf("abc", "123")
            lst.takeOrNull(-1).assertNull()
            lst.takeOrNull(0).assertEmpty()
            lst.takeOrNull(1).assert("abc")
            lst.takeOrNull(2).assert("abc", "123")
            lst.takeOrNull(3).assert("abc", "123")
        }
    }
}