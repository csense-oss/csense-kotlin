package csense.kotlin

import csense.kotlin.tests.assertions.*

//use from csense kotlin test when available
@Suppress("RedundantNullableReturnType", "NOTHING_TO_INLINE")
public inline fun <T> (T & Any).nullable(): T? {
    return this
}

//use from csense kotlin test when available
public fun <T : Comparable<T>> Array<out T>.assert(
    expected: Array<out T>,
    message: String = "Expected this Array to be the same as expected but was different"
) {
    ArrayAssertions.AssertArrays(
        givenArray = this,
        expected = expected,
        message = message,
        getSize = { size },
        getElementAt = { get(it) }
    )
}
