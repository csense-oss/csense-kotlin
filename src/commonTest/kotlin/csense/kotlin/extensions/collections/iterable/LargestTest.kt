@file:Suppress("unused")

package csense.kotlin.extensions.collections.iterable

import csense.kotlin.tests.assertions.*
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