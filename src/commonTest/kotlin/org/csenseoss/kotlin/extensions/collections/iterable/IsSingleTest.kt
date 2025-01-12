@file:Suppress("unused")

package org.csenseoss.kotlin.extensions.collections.iterable

import org.csenseoss.kotlin.tests.assertions.*
import org.csenseoss.kotlin.tests.assertions.primitives.boolean.*
import kotlin.test.*

class IsSingleTest {
    class IsSingle {
        @Test
        fun empty() {
            listOf<String>().asIterable().isSingle().assertFalse()
        }

        @Test
        fun single() {
            listOf("test").asIterable().isSingle().assertTrue()
        }

        @Test
        fun multiple() {
            listOf("test", "1234").asIterable().isSingle().assertFalse()
        }

    }

    class IsSingleOrEmpty {

        @Test
        fun empty() {
            listOf<String>().asIterable().isSingleOrEmpty().assertTrue()
        }

        @Test
        fun single() {
            listOf("test").asIterable().isSingleOrEmpty().assertTrue()
        }

        @Test
        fun multiple() {
            listOf("test", "1234").asIterable().isSingleOrEmpty().assertFalse()
        }
    }


}