package org.csenseoss.kotlin.extensions.collections.collection

import csense.kotlin.tests.assertions.assertContainsInOrder
import csense.kotlin.tests.assertions.assertEmpty
import csense.kotlin.tests.assertions.assertSingle
import kotlin.test.Test

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