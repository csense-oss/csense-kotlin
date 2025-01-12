package org.csenseoss.kotlin.extensions.collections.collection

import org.csenseoss.kotlin.tests.assertions.*
import org.csenseoss.kotlin.tests.assertions.collections.iterable.*
import kotlin.test.*

class SortedByTest {
    class SortedByFalseFirst {

        @Test
        fun empty() {
            emptyList<Boolean>().sortedByFalseFirst().assertEmpty()
        }

        @Test
        fun single() {
            listOf(false).sortedByFalseFirst().assert(false)
        }

        @Test
        fun multiple() {
            listOf(true, false, true, false)
                .sortedByFalseFirst()
                .assert(false, false, true, true)
        }
    }

    class SortedByTrueFirst {

        @Test
        fun empty() {
            emptyList<Boolean>().sortedByTrueFirst().assertEmpty()
        }

        @Test
        fun single() {
            listOf(true).sortedByTrueFirst().assert(true)
        }

        @Test
        fun multiple() {
            listOf(true, false, true, false)
                .sortedByTrueFirst()
                .assert(true, true, false, false)
        }
    }
}