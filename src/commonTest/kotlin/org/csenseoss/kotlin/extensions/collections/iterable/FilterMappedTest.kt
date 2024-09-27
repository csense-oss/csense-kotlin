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
        fun singleCases(){
            
        }
    }
}