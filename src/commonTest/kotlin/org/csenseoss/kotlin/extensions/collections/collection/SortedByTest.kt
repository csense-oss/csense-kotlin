package org.csenseoss.kotlin.extensions.collections.collection

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class SortedByTest {
    class SortedByFalseFirst {

        @Test
        fun empty() {
            emptyList<Boolean>().sortedByFalseFirst().assertEmpty()
        }

        @Test
        fun single() {
            listOf(false).sortedByFalseFirst().assertSingle(false)
        }

        @Test
        fun multiple() {
            listOf(true, false, true, false)
                .sortedByFalseFirst()
                .assertContainsInOrder(false, false, true, true)
        }
    }

    class SortedByTrueFirst {

        @Test
        fun empty() {
            emptyList<Boolean>().sortedByTrueFirst().assertEmpty()
        }

        @Test
        fun single() {
            listOf(true).sortedByTrueFirst().assertSingle(true)
        }

        @Test
        fun multiple() {
            listOf(true, false, true, false)
                .sortedByTrueFirst()
                .assertContainsInOrder(true, true, false, false)
        }
    }
}