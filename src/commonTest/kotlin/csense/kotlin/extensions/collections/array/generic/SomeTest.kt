@file:Suppress("unused")

package csense.kotlin.extensions.collections.array.generic

import csense.kotlin.extensions.collections.generic.collection.operations.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class SomeTest {
    class SatisfyPredicate {
        @Test
        fun empty() {
            arrayOf<Int>().satisfyPredicate { _: Int ->
                shouldNotBeCalled()
            }.assertByEquals(SatisfyPredicateResult.Empty)
        }

        @Test
        fun singleNone() {
            arrayOf(11).satisfyPredicate { it: Int ->
                it.assert(11)
                false
            }.assertByEquals(SatisfyPredicateResult.None)
        }

        @Test
        fun singleDoes() {
            arrayOf(42).satisfyPredicate { it: Int ->
                it.assert(42)
                true
            }.assertByEquals(SatisfyPredicateResult.All)
        }

        @Test
        fun multipleNoneMatches() {
            arrayOf(42, 0).satisfyPredicate { _: Int ->
                false
            }.assertByEquals(SatisfyPredicateResult.None)
        }

        @Test
        fun multipleAllMatches() {
            arrayOf(42, 112).satisfyPredicate { _: Int ->
                true
            }.assertByEquals(SatisfyPredicateResult.All)
        }

        @Test
        fun multipleSomeMatches() {
            assertCalled(times = 3) { shouldBeCalled: () -> Unit ->
                arrayOf(42, 0, 42).satisfyPredicate { it: Int ->
                    shouldBeCalled()
                    it == 0
                }.assertByEquals(SatisfyPredicateResult.Some)
            }


            arrayOf(42, 0, 42).satisfyPredicate { it: Int ->
                it == 42
            }.assertByEquals(SatisfyPredicateResult.Some)
        }
    }
}
