package csense.kotlin.extensions.primitives

import csense.kotlin.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class LongTest {


    @Test
    fun longNegative() {
        (-1L).negative.assert(-1L)
        0L.negative.assert(-0L)
        1L.negative.assert(-1L)
        (-50L).negative.assert(-50L)
        42L.negative.assert(-42L)
    }

    @Test
    fun longIsOdd() {
        (-1L).isOdd.assertTrue()
        0L.isOdd.assertFalse()
        1L.isOdd.assertTrue()
        (-50L).isOdd.assertFalse()
        42L.isOdd.assertFalse()
    }

    @Test
    fun longIsEven() {
        (-1L).isEven.assertFalse()
        0L.isEven.assertTrue()
        1L.isEven.assertFalse()
        (-50L).isEven.assertTrue()
        42L.isEven.assertTrue()
    }

    @Test
    fun longIsPositive() {
        (-1L).isPositive.assertFalse()
        0L.isPositive.assertFalse()
        1L.isPositive.assertTrue()
        (-50L).isPositive.assertFalse()
        42L.isPositive.assertTrue()
    }

    @Test
    fun longIsNegative() {
        (-1L).isNegative.assertTrue()
        0L.isNegative.assertFalse()
        1L.isNegative.assertFalse()
        (-50L).isNegative.assertTrue()
        42L.isNegative.assertFalse()
    }

    @Test
    fun longIsPositiveOrZero() {
        (-1L).isPositiveOrZero.assertFalse()
        0L.isPositiveOrZero.assertTrue()
        1L.isPositiveOrZero.assertTrue()
        (-50L).isPositiveOrZero.assertFalse()
        42L.isPositiveOrZero.assertTrue()
    }

    @Test
    fun longIsNegativeOrZero() {
        (-1L).isNegativeOrZero.assertTrue()
        0L.isNegativeOrZero.assertTrue()
        1L.isNegativeOrZero.assertFalse()
        (-50L).isNegativeOrZero.assertTrue()
        42L.isNegativeOrZero.assertFalse()
    }

    @Test
    fun longIsZero() {
        0L.isZero.assert(true, "zero is 0")
        1L.isZero.assert(false)
        (-1L).isZero.assert(false)
        Long.MAX_VALUE.isZero.assert(false)
        Long.MIN_VALUE.isZero.assert(false)
    }

    @Test
    fun longIsNotZero() {
        0L.isNotZero.assert(false, "zero is \"not zero\"")
        1L.isNotZero.assert(true)
        (-1L).isNotZero.assert(true)
        Long.MAX_VALUE.isNotZero.assert(true)
        Long.MIN_VALUE.isNotZero.assert(true)
    }

    @Test
    fun longPositive() {
        (-1L).positive.assert(1L)
        0L.positive.assert(0L)
        1L.positive.assert(1L)
        (-50L).positive.assert(50L)
        42L.positive.assert(42L)
    }


    @Test
    fun longIsNotNullOrZero() {
        val nullable: Long? = null
        nullable.isNotNullOrZero().assertFalse("it is null")
        (-1L).nullable().isNotNullOrZero().assertTrue("a nonnull or non zero number")
        0L.nullable().isNotNullOrZero().assertFalse("this is zero")
        1L.nullable().isNotNullOrZero().assertTrue("a nonnull or non zero number")
        9999L.nullable().isNotNullOrZero().assertTrue("a nonnull or non zero number")
    }

    @Test
    fun longIsNullOrZero() {
        val nullable: Long? = null
        nullable.isNullOrZero().assertTrue("it is null")
        (-5L).nullable().isNullOrZero().assertFalse("is actually a number")
        0L.nullable().isNullOrZero().assertTrue("is zero")
        1L.nullable().isNullOrZero().assertFalse("is a number")
        5000L.nullable().isNullOrZero().assertFalse()
    }
}