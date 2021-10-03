@file:Suppress("unused")

package csense.kotlin.extensions.collections.set

import csense.kotlin.extensions.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class SetTest {

    class SetTSymmetricDifference {
        @Test
        fun emptyFirst() {
            val empty = setOf<String>().symmetricDifference(setOf())
            empty.uniqueInFirst.assertEmpty()
            empty.uniqueInSecond.assertEmpty()

            val single = setOf<String>().symmetricDifference(setOf("1"))
            single.uniqueInFirst.assertEmpty()
            single.uniqueInSecond.assertContainsAll("1")

            val multiple = setOf<String>().symmetricDifference(setOf("1", "a"))

            multiple.uniqueInFirst.assertEmpty()
            multiple.uniqueInSecond.assertContainsAll("1", "a")
        }

        @Test
        fun singleFirst() {
            val empty = setOf("b").symmetricDifference(setOf())
            empty.uniqueInFirst.assertContainsAll("b")
            empty.uniqueInSecond.assertEmpty()

            val single = setOf("b").symmetricDifference(setOf("1"))
            single.uniqueInFirst.assertContainsAll("b")
            single.uniqueInSecond.assertContainsAll("1")

            val multipleWithCollision = setOf("b").symmetricDifference(setOf("1", "a", "b"))

            multipleWithCollision.uniqueInFirst.assertEmpty()
            multipleWithCollision.uniqueInSecond.assertContainsAll("1", "a")

            val multipleWithNoCollision = setOf("b").symmetricDifference(setOf("1", "a", "c"))
            multipleWithNoCollision.uniqueInFirst.assertContainsAll("b")
            multipleWithNoCollision.uniqueInSecond.assertContainsAll("1", "a", "c")
        }

        @Test
        fun multipleFirst() {
            val empty = setOf("b", "2").symmetricDifference(setOf())
            empty.uniqueInFirst.assertContainsAll("b", "2")
            empty.uniqueInSecond.assertEmpty()

            val single = setOf("b", "2").symmetricDifference(setOf("1"))
            single.uniqueInFirst.assertContainsAll("b", "2")
            single.uniqueInSecond.assertContainsAll("1")

            val singleCollision = setOf("b", "2", "1").symmetricDifference(setOf("1"))
            singleCollision.uniqueInFirst.assertContainsAll("b", "2")
            singleCollision.uniqueInSecond.assertEmpty()

            val multipleWithCollision = setOf("b", "2").symmetricDifference(setOf("1", "a", "b"))
            multipleWithCollision.uniqueInFirst.assertContainsAll("2")
            multipleWithCollision.uniqueInSecond.assertContainsAll("1", "a")

            val firstOnlyCollision = setOf("a", "1").symmetricDifference(setOf("1", "a", "b"))
            firstOnlyCollision.uniqueInFirst.assertEmpty()
            firstOnlyCollision.uniqueInSecond.assertContainsAll("b")

            val multipleWithNoCollision = setOf("b", "2").symmetricDifference(setOf("1", "a", "c"))
            multipleWithNoCollision.uniqueInFirst.assertContainsAll("b", "2")
            multipleWithNoCollision.uniqueInSecond.assertContainsAll("1", "a", "c")

        }

        @Test
        fun nullableShouldStillWork() {
            val lhsNull = setOf<String?>(null).symmetricDifference(setOf("a"))
            lhsNull.uniqueInFirst.assertSingle(null)
            lhsNull.uniqueInSecond.assertSingle("a")

            val rhsNull = setOf<String?>("a").symmetricDifference(setOf(null))
            rhsNull.uniqueInFirst.assertSingle("a")
            rhsNull.uniqueInSecond.assertSingle(null)

            val nullOnlyInBoth = setOf<String?>(null).symmetricDifference(setOf(null))
            nullOnlyInBoth.uniqueInFirst.assertEmpty()
            nullOnlyInBoth.uniqueInSecond.assertEmpty()

            val nullInBoth: SymmetricDifferenceResult<String?> = setOf(null, "a").symmetricDifference(setOf(null, "b"))
            nullInBoth.uniqueInFirst.assertSingle("a")
            nullInBoth.uniqueInSecond.assertSingle("b")
        }

    }

    class SetTDoesNotContain {
        @Test
        fun empty() {
            val map = setOf<String>()
            map.doesNotContain("1").assertTrue()
        }

        @Test
        fun single() {
            val map = setOf("1234")
            map.doesNotContain("1").assertTrue()
            map.doesNotContain("1234").assertFalse()
            map.doesNotContain("12345").assertTrue()
        }


        @Test
        fun multiple() {
            val map = setOf(123, 444)
            map.doesNotContain(1).assertTrue()
            map.doesNotContain(2).assertTrue()
            map.doesNotContain(123).assertFalse()
            map.doesNotContain(444).assertFalse()
            map.doesNotContain(555).assertTrue()
        }

        @Test
        fun nullableShouldStillWork() {
            val map = setOf<Int?>(null)
            map.doesNotContain(null).assertFalse("contains null")
            map.doesNotContain(42).assertTrue()

        }
    }

    class SetEContainsAny {
        @Test
        fun emptySet() {
            setOf<String>().containsAny(listOf()).assertFalse()
            setOf<String>().containsAny(listOf("1")).assertFalse()
            setOf<String>().containsAny(listOf("1", "2")).assertFalse()
        }

        @Test
        fun emptyOther() {
            setOf("1", "2").containsAny(listOf()).assertFalse()
            setOf("1").containsAny(listOf()).assertFalse()
            setOf("1", "2", "3").containsAny(listOf()).assertFalse()
        }

        @Test
        fun singleItemInBoth() {
            setOf("1").containsAny(listOf("1")).assertTrue()
            setOf("1").containsAny(listOf("2")).assertFalse()
            setOf("3").containsAny(listOf("2")).assertFalse()
        }

        @Test
        fun multipleHaveItems() {

            setOf("1", "4").containsAny(listOf("1")).assertTrue()
            setOf("1", "4").containsAny(listOf("4")).assertTrue()
            setOf("1", "4").containsAny(listOf("5")).assertFalse()

            setOf("1", "2").containsAny(listOf("2", "1")).assertTrue()
            setOf("4", "6").containsAny(listOf("2", "1")).assertFalse()

            setOf("3", "9").containsAny(listOf("2", "1", "3")).assertTrue()
        }

        @Test
        fun sampleForSets() {
            setOf<String>().containsAny(setOf()).assertFalse()
            setOf("1").containsAny(setOf()).assertFalse()
            setOf<String>().containsAny(setOf("1")).assertFalse()
            setOf("1").containsAny(setOf("1")).assertTrue()
            setOf("1").containsAny(setOf("2")).assertFalse()

            setOf("2").containsAny(setOf("1", "3")).assertFalse()
            setOf("2").containsAny(setOf("1", "2")).assertTrue()

            setOf("2", "3").containsAny(setOf("1")).assertFalse()
            setOf("2", "3").containsAny(setOf("2")).assertTrue()
            setOf("2", "3").containsAny(setOf("3")).assertTrue()
            setOf("2", "3").containsAny(setOf("4")).assertFalse()
            setOf("2", "3").containsAny(setOf("4", "2")).assertTrue()
        }

        @Test
        fun nullableShouldWork() {
            setOf<String?>(null).containsAny(listOf()).assertFalse("null is not nothing")
            setOf<String?>(null).containsAny(listOf(null)).assertTrue("null is null")
            setOf<String?>(null).containsAny(listOf("a", null)).assertTrue("null is still contained")
        }
    }

    class SetEDoesNotContainAny {
        @Test
        fun empty() {
            setOf<String>().doesNotContainAny(listOf()).assertTrue("nothing does not contain anything")
            setOf<String>().doesNotContainAny(listOf("test")).assertTrue("nothing does not contain something")
        }

        @Test
        fun single() {
            setOf("test").doesNotContainAny(listOf()).assertTrue("something does not contains nothing")
            setOf("test").doesNotContainAny(listOf("test")).assertFalse("nothing does not contain something")
            setOf("test2").doesNotContainAny(listOf("test")).assertTrue("nothing does not contain something")
        }

        @Test
        fun multiple() {
            setOf("test", "1234").doesNotContainAny(listOf()).assertTrue("something does not contains nothing")
            setOf("test", "1234").doesNotContainAny(listOf("test")).assertFalse("nothing does not contain something")
            setOf("test", "1234").doesNotContainAny(listOf("1234")).assertFalse("nothing does not contain something")
            setOf("test", "1234").doesNotContainAny(listOf("a", "b")).assertTrue("nothing does not contain something")
            setOf("test", "1234").doesNotContainAny(listOf("a", "b", "test"))
                .assertFalse("nothing does not contain something")
            setOf("test2", "1234").doesNotContainAny(listOf("test")).assertTrue("nothing does not contain something")
        }

        @Test
        fun nullableShouldWork() {
            setOf<String?>(null).doesNotContainAny(listOf()).assertTrue("null is not nothing")
            setOf<String?>(null).doesNotContainAny(listOf(null)).assertFalse("null is null")
            setOf<String?>(null).doesNotContainAny(listOf("a", null)).assertFalse("null is still contained")
        }
    }


    class SetTForeachBackwards {
        @Test
        fun empty() {
            setOf<String>().foreachBackwards { shouldNotBeCalled() }
        }

        @Test
        fun single() = assertCalled { shouldBeCalled ->
            setOf("test").foreachBackwards {
                it.assert("test")
                shouldBeCalled()
            }
        }

        @Test
        fun multiple() = assertCalled(times = 2) { shouldBeCalled ->
            var haveCalled = false
            setOf("first", "last").foreachBackwards {
                it.assert(haveCalled.map("first", "last"))
                haveCalled = true
                shouldBeCalled()
            }
        }
    }
}