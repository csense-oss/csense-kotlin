package csense.kotlin.test.assertions

import kotlin.test.assertNotEquals
import kotlin.test.assertTrue


fun <U : Comparable<U>> U.assertLargerOrEqualTo(i: U, optMessage: String = "") {
    assertTrue(this >= i, "$this should be larger or equal to $i, but it is not.\n$optMessage")
}

fun <U : Comparable<U>> U.assertLargerThan(i: U, optMessage: String = "") {
    assertTrue(this > i, "$this should be larger than $i, but it is not.\n$optMessage")
}


fun <T : Comparable<T>> T.assertNotEquals(other: T, message: String = "") {
    assertNotEquals(other, this, message)
}