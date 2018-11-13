package csense.kotlin.test.assertions

import kotlin.test.*


fun <T : Enum<T>> Enum<T>.assert(other: Enum<T>) {
    assertEquals(this, other)
}