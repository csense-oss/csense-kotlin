package org.csenseoss.kotlin.extensions.collections.iterable

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class FilterMappedTest {
    class FilterMapped {
        @Test
        fun emptyNone() {
            val iterable: Iterable<String> = emptyList()
            iterable.filterMapped(
                predicate = { shouldNotBeCalled() },
                transform = { shouldNotBeCalled() }
            ).assertEmpty()
        }

        @Test
        fun singleCases() {
            val iterable: Iterable<String> = listOf("test")
            iterable.filterMapped(
                predicate = { it: String ->
                    it.assert("test")
                    false
                },
                transform = { it: String ->
                    it.assert("test")
                    it
                }
            ).assertEmpty()


            iterable.filterMapped(
                predicate = { it: String ->
                    it.assert("test")
                    true
                },
                transform = { it: String ->
                    it.assert("test")
                    it
                }
            ).assertSingle("test")

            iterable.filterMapped(
                predicate = { it: Int ->
                    it.assert(42)
                    true
                },
                transform = { it: String ->
                    it.assert("test")
                    42
                }
            ).assertSingle(42)

            iterable.filterMapped(
                predicate = { it: Int ->
                    it.assert(42)
                    false
                },
                transform = { it: String ->
                    it.assert("test")
                    42
                }
            ).assertEmpty()
        }
    }
}