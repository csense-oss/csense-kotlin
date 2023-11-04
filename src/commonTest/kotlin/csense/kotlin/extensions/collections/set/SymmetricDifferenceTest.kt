@file:Suppress("unused")

package csense.kotlin.extensions.collections.set

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class SymmetricDifferenceTest {

    class SetTSymmetricDifference {
        @Test
        fun emptyFirst() {
            val empty: SymmetricSetDifferenceResult<String> = setOf<String>().symmetricDifference(setOf())
            empty.uniqueInFirst.assertEmpty()
            empty.uniqueInSecond.assertEmpty()

            val single: SymmetricSetDifferenceResult<String> = setOf<String>().symmetricDifference(setOf("1"))
            single.uniqueInFirst.assertEmpty()
            single.uniqueInSecond.assertContainsAll("1")

            val multiple: SymmetricSetDifferenceResult<String> = setOf<String>().symmetricDifference(setOf("1", "a"))

            multiple.uniqueInFirst.assertEmpty()
            multiple.uniqueInSecond.assertContainsAll("1", "a")
        }

        @Test
        fun singleFirst() {
            val empty: SymmetricSetDifferenceResult<String> = setOf("b").symmetricDifference(setOf())
            empty.uniqueInFirst.assertContainsAll("b")
            empty.uniqueInSecond.assertEmpty()

            val single: SymmetricSetDifferenceResult<String> = setOf("b").symmetricDifference(setOf("1"))
            single.uniqueInFirst.assertContainsAll("b")
            single.uniqueInSecond.assertContainsAll("1")

            val multipleWithCollision: SymmetricSetDifferenceResult<String> =
                setOf("b").symmetricDifference(setOf("1", "a", "b"))

            multipleWithCollision.uniqueInFirst.assertEmpty()
            multipleWithCollision.uniqueInSecond.assertContainsAll("1", "a")

            val multipleWithNoCollision: SymmetricSetDifferenceResult<String> =
                setOf("b").symmetricDifference(setOf("1", "a", "c"))
            multipleWithNoCollision.uniqueInFirst.assertContainsAll("b")
            multipleWithNoCollision.uniqueInSecond.assertContainsAll("1", "a", "c")
        }

        @Test
        fun multipleFirst() {
            val empty: SymmetricSetDifferenceResult<String> = setOf("b", "2").symmetricDifference(setOf())
            empty.uniqueInFirst.assertContainsAll("b", "2")
            empty.uniqueInSecond.assertEmpty()

            val single: SymmetricSetDifferenceResult<String> = setOf("b", "2").symmetricDifference(setOf("1"))
            single.uniqueInFirst.assertContainsAll("b", "2")
            single.uniqueInSecond.assertContainsAll("1")

            val singleCollision: SymmetricSetDifferenceResult<String> =
                setOf("b", "2", "1").symmetricDifference(setOf("1"))
            singleCollision.uniqueInFirst.assertContainsAll("b", "2")
            singleCollision.uniqueInSecond.assertEmpty()

            val multipleWithCollision: SymmetricSetDifferenceResult<String> =
                setOf("b", "2").symmetricDifference(setOf("1", "a", "b"))
            multipleWithCollision.uniqueInFirst.assertContainsAll("2")
            multipleWithCollision.uniqueInSecond.assertContainsAll("1", "a")

            val firstOnlyCollision: SymmetricSetDifferenceResult<String> =
                setOf("a", "1").symmetricDifference(setOf("1", "a", "b"))
            firstOnlyCollision.uniqueInFirst.assertEmpty()
            firstOnlyCollision.uniqueInSecond.assertContainsAll("b")

            val multipleWithNoCollision: SymmetricSetDifferenceResult<String> =
                setOf("b", "2").symmetricDifference(setOf("1", "a", "c"))
            multipleWithNoCollision.uniqueInFirst.assertContainsAll("b", "2")
            multipleWithNoCollision.uniqueInSecond.assertContainsAll("1", "a", "c")

        }

        @Test
        fun nullableShouldStillWork() {
            val lhsNull: SymmetricSetDifferenceResult<String?> = setOf<String?>(null).symmetricDifference(setOf("a"))
            lhsNull.uniqueInFirst.assertSingle(null)
            lhsNull.uniqueInSecond.assertSingle("a")

            val rhsNull: SymmetricSetDifferenceResult<String?> = setOf<String?>("a").symmetricDifference(setOf(null))
            rhsNull.uniqueInFirst.assertSingle("a")
            rhsNull.uniqueInSecond.assertSingle(null)

            val nullOnlyInBoth: SymmetricSetDifferenceResult<String?> =
                setOf<String?>(null).symmetricDifference(setOf(null))
            nullOnlyInBoth.uniqueInFirst.assertEmpty()
            nullOnlyInBoth.uniqueInSecond.assertEmpty()

            val nullInBoth: SymmetricSetDifferenceResult<String?> =
                setOf(null, "a").symmetricDifference(setOf(null, "b"))
            nullInBoth.uniqueInFirst.assertSingle("a")
            nullInBoth.uniqueInSecond.assertSingle("b")
        }

    }

}