@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.test.assertions

import kotlin.test.*


fun <U : Comparable<U>> U.assertLargerOrEqualTo(i: U, optMessage: String = "") {
    assertTrue(this >= i, "$this should be larger or equal to $i, but it is not.\n$optMessage")
}

fun <U : Comparable<U>> U.assertLessOrEqualTo(i: U, optMessage: String = "") {
    assertTrue(this <= i, "$this should be smaller or equal to $i, but it is not.\n$optMessage")
}


fun <U : Comparable<U>> U.assertLessThan(i: U, optMessage: String = "") {
    assertTrue(this < i, "$this should be smaller than $i, but it is not.\n$optMessage")
}

fun <U : Comparable<U>> U.assertLargerThan(i: U, optMessage: String = "") {
    assertTrue(this > i, "$this should be larger than $i, but it is not.\n$optMessage")
}


fun <T : Comparable<T>> T.assertNotEquals(other: T, message: String = "expected not to be equal, but $this === $other") {
    assertNotEquals(other, this, message)
}

