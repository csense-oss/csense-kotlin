package csense.kotlin.extensions.primitives

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class IntTest {

    @Test
    fun intNegative() {
        (-1).negative.assert(-1)
        0.negative.assert(-0)
        1.negative.assert(-1)
        (-50).negative.assert(-50)
        42.negative.assert(-42)
    }

    @Test
    fun intPositive() {
        (-1).positive.assert(1)
        0.positive.assert(0)
        1.positive.assert(1)
        (-50).positive.assert(50)
        42.positive.assert(42)
    }

    @Test
    fun intIsOdd() {
        //some examples
        0.isOdd.assertFalse()
        2.isOdd.assertFalse()
        4.isOdd.assertFalse()
        6.isOdd.assertFalse()
        (-0).isOdd.assertFalse()
        (-2).isOdd.assertFalse()
        (-4).isOdd.assertFalse()
        (-6).isOdd.assertFalse()


        (-5).isOdd.assertTrue()
        (-1).isOdd.assertTrue()
        (1).isOdd.assertTrue()
        (3).isOdd.assertTrue()
        (5).isOdd.assertTrue()
        (17).isOdd.assertTrue()
        (203).isOdd.assertTrue()
    }

    @Test
    fun intIsEven() {
        //some examples
        0.isEven.assertTrue()
        2.isEven.assertTrue()
        4.isEven.assertTrue()
        6.isEven.assertTrue()
        (-0).isEven.assertTrue()
        (-2).isEven.assertTrue()
        (-4).isEven.assertTrue()
        (-6).isEven.assertTrue()


        (-5).isEven.assertFalse()
        (-1).isEven.assertFalse()
        (1).isEven.assertFalse()
        (203).isEven.assertFalse()
    }

    @Test
    fun intIsPositive() {
        (-1).isPositive.assertFalse()
        0.isPositive.assertFalse()
        1.isPositive.assertTrue()
        (-50).isPositive.assertFalse()
        42.isPositive.assertTrue()
    }

    @Test
    fun intIsNegative() {
        (-1).isNegative.assertTrue()
        0.isNegative.assertFalse()
        1.isNegative.assertFalse()
        (-50).isNegative.assertTrue()
        42.isNegative.assertFalse()
    }

    @Test
    fun intIsPositiveOrZero() {
        (-1).isPositiveOrZero.assertFalse()
        0.isPositiveOrZero.assertTrue()
        1.isPositiveOrZero.assertTrue()
        (-50).isPositiveOrZero.assertFalse()
        42.isPositiveOrZero.assertTrue()
    }

    @Test
    fun intIsNegativeOrZero() {
        (-1).isNegativeOrZero.assertTrue()
        0.isNegativeOrZero.assertTrue()
        1.isNegativeOrZero.assertFalse()
        (-50).isNegativeOrZero.assertTrue()
        42.isNegativeOrZero.assertFalse()
    }

    @Test
    fun intIsZero() {
        0.isZero.assert(true, "zero is 0")
        1.isZero.assert(false)
        (-1).isZero.assert(false)
        Int.MAX_VALUE.isZero.assert(false)
        Int.MIN_VALUE.isZero.assert(false)
    }

    @Test
    fun intIsNotZero() {
        0.isNotZero.assert(false, "zero is \"not zero\"")
        1.isNotZero.assert(true)
        (-1).isNotZero.assert(true)
        Int.MAX_VALUE.isNotZero.assert(true)
        Int.MIN_VALUE.isNotZero.assert(true)
    }

    @Test
    fun intForEach() {
        (-50).forEach { failTest() }
        0.forEach { failTest() }
        run {
            var counter = 0
            1.forEach { counter += 1 }
            counter.assert(1)
        }
        run {
            var counter = 0
            55.forEach { counter += 1 }
            counter.assert(55)
        }
    }

    @Test
    fun indexOfExtensionsUnwrapUnsafeIndexOf() {
        (-59).indexOfExtensions.unwrapUnsafeIndexOf().assertNull()
        (-1).indexOfExtensions.unwrapUnsafeIndexOf().assertNull()
        (-0).indexOfExtensions.unwrapUnsafeIndexOf().assertNotNullAndEquals(0)
        (0).indexOfExtensions.unwrapUnsafeIndexOf().assertNotNullAndEquals(0)
        (1).indexOfExtensions.unwrapUnsafeIndexOf().assertNotNullAndEquals(1)
        (20).indexOfExtensions.unwrapUnsafeIndexOf().assertNotNullAndEquals(20)
        (8000).indexOfExtensions.unwrapUnsafeIndexOf().assertNotNullAndEquals(8000)
    }

    @Test
    fun intIndexOfExtensions() {
        (-1).indexOfExtensions.value.assert(-1)
        0.indexOfExtensions.value.assert(0)
        1.indexOfExtensions.value.assert(1)
        (-50).indexOfExtensions.value.assert(-50)
        42.indexOfExtensions.value.assert(42)
    }

    @Test
    fun intIsNotNullOrZero() {
        val nullable: Int? = null
        nullable.isNotNullOrZero().assertFalse("it is null")
        (-1).isNotNullOrZero().assertTrue("a nonnull or non zero number")
        0.isNotNullOrZero().assertFalse("this is zero")
        1.isNotNullOrZero().assertTrue("a nonnull or non zero number")
        9999.isNotNullOrZero().assertTrue("a nonnull or non zero number")
    }

    @Test
    fun intIsNullOrZero() {
        val nullable: Int? = null
        nullable.isNullOrZero().assertTrue("it is null")
        (-5).isNullOrZero().assertFalse("is actually a number")
        0.isNullOrZero().assertTrue("is zero")
        1.isNullOrZero().assertFalse("is a number")
        5000.isNullOrZero().assertFalse()
    }
}