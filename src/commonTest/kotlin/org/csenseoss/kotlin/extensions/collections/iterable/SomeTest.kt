package org.csenseoss.kotlin.extensions.collections.iterable

import org.csenseoss.kotlin.extensions.collections.generic.collection.operations.*
import org.csenseoss.kotlin.tests.assertions.*
import org.csenseoss.kotlin.tests.assertions.general.*
import kotlin.test.*

class SomeTest {
    @Test
    fun empty() {
        val iterable: Iterable<String> = listOf()
        iterable.some { shouldNotBeCalled() }.assertByEquals(SatisfyPredicateResult.Empty)
    }

    @Test
    fun singleCases() {
        val iterable: Iterable<String> = listOf("test")
        iterable.some { false }.assertByEquals(SatisfyPredicateResult.None)
        iterable.some { true }.assertByEquals(SatisfyPredicateResult.All)
    }

    @Test
    fun multipleCases() {
        val iterable: Iterable<String> = listOf("test", "1234")
        iterable.some { false }.assertByEquals(SatisfyPredicateResult.None)
        iterable.some { true }.assertByEquals(SatisfyPredicateResult.All)
        iterable.some { it.toIntOrNull() != null }.assertByEquals(SatisfyPredicateResult.Some)
    }

}