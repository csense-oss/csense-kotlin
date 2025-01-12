@file:Suppress("unused")

package org.csenseoss.kotlin.extensions.collections.set

import org.csenseoss.kotlin.tests.assertions.collections.iterable.*
import org.csenseoss.kotlin.tests.assertions.collections.iterable.nullable.*
import org.csenseoss.kotlin.tests.assertions.comparable.*
import org.csenseoss.kotlin.tests.assertions.general.*
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
            single.uniqueInSecond.assert("1")

            val multiple: SymmetricSetDifferenceResult<String> = setOf<String>().symmetricDifference(setOf("1", "a"))

            multiple.uniqueInFirst.assertEmpty()
            multiple.uniqueInSecond.assert("1", "a")
        }

        @Test
        fun singleFirst() {
            val empty: SymmetricSetDifferenceResult<String> = setOf("b").symmetricDifference(setOf())
            empty.uniqueInFirst.assert("b")
            empty.uniqueInSecond.assertEmpty()

            val single: SymmetricSetDifferenceResult<String> = setOf("b").symmetricDifference(setOf("1"))
            single.uniqueInFirst.assert("b")
            single.uniqueInSecond.assert("1")

            val multipleWithCollision: SymmetricSetDifferenceResult<String> =
                setOf("b").symmetricDifference(setOf("1", "a", "b"))

            multipleWithCollision.uniqueInFirst.assertEmpty()
            multipleWithCollision.uniqueInSecond.assert("1", "a")

            val multipleWithNoCollision: SymmetricSetDifferenceResult<String> =
                setOf("b").symmetricDifference(setOf("1", "a", "c"))
            multipleWithNoCollision.uniqueInFirst.assert("b")
            multipleWithNoCollision.uniqueInSecond.assert("1", "a", "c")
        }

        @Test
        fun multipleFirst() {
            val empty: SymmetricSetDifferenceResult<String> = setOf("b", "2").symmetricDifference(setOf())
            empty.uniqueInFirst.assert("b", "2")
            empty.uniqueInSecond.assertEmpty()

            val single: SymmetricSetDifferenceResult<String> = setOf("b", "2").symmetricDifference(setOf("1"))
            single.uniqueInFirst.assert("b", "2")
            single.uniqueInSecond.assert("1")

            val singleCollision: SymmetricSetDifferenceResult<String> =
                setOf("b", "2", "1").symmetricDifference(setOf("1"))
            singleCollision.uniqueInFirst.assert("b", "2")
            singleCollision.uniqueInSecond.assertEmpty()

            val multipleWithCollision: SymmetricSetDifferenceResult<String> =
                setOf("b", "2").symmetricDifference(setOf("1", "a", "b"))
            multipleWithCollision.uniqueInFirst.assert("2")
            multipleWithCollision.uniqueInSecond.assert("1", "a")

            val firstOnlyCollision: SymmetricSetDifferenceResult<String> =
                setOf("a", "1").symmetricDifference(setOf("1", "a", "b"))
            firstOnlyCollision.uniqueInFirst.assertEmpty()
            firstOnlyCollision.uniqueInSecond.assert("b")

            val multipleWithNoCollision: SymmetricSetDifferenceResult<String> =
                setOf("b", "2").symmetricDifference(setOf("1", "a", "c"))
            multipleWithNoCollision.uniqueInFirst.assert("b", "2")
            multipleWithNoCollision.uniqueInSecond.assert("1", "a", "c")

        }

        @Test
        fun nullableShouldStillWork() {
            val lhsNull: SymmetricSetDifferenceResult<String?> = setOf<String?>(null).symmetricDifference(setOf("a"))
            lhsNull.uniqueInFirst.assert(null)
            lhsNull.uniqueInSecond.assert("a")

            val rhsNull: SymmetricSetDifferenceResult<String?> = setOf<String?>("a").symmetricDifference(setOf(null))
            rhsNull.uniqueInFirst.assert("a")
            rhsNull.uniqueInSecond.assert(null)

            val nullOnlyInBoth: SymmetricSetDifferenceResult<String?> =
                setOf<String?>(null).symmetricDifference(setOf(null))
            nullOnlyInBoth.uniqueInFirst.assertEmpty()
            nullOnlyInBoth.uniqueInSecond.assertEmpty()

            val nullInBoth: SymmetricSetDifferenceResult<String?> =
                setOf(null, "a").symmetricDifference(setOf(null, "b"))
            nullInBoth.uniqueInFirst.assert("a")
            nullInBoth.uniqueInSecond.assert("b")
        }

    }
}