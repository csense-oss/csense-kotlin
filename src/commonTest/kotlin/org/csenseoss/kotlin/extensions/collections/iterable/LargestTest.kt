@file:Suppress("unused")

package org.csenseoss.kotlin.extensions.collections.iterable

import org.csenseoss.kotlin.tests.assertions.*
import org.csenseoss.kotlin.tests.assertions.comparable.*
import org.csenseoss.kotlin.tests.assertions.general.*
import org.csenseoss.kotlin.tests.assertions.primitives.charSequence.*
import org.csenseoss.kotlin.tests.assertions.primitives.charSequence.assert
import kotlin.test.*

class LargestTest {
    class IterableELargest {
        @Test
        fun empty() {
            listOf<String>().largest { shouldNotBeCalled() }
        }

        @Test
        fun single() {
            listOf("test")
                .largest { it: String ->
                    it.assert("test")
                    it.length
                }.assert("test")
        }

        @Test
        fun multiple() {
            listOf(42, 100, 1, 102).largest { it }.assert(102)
            listOf(42, 100, 1, 102).largest { 0 - it }.assert(1)
        }
    }

}