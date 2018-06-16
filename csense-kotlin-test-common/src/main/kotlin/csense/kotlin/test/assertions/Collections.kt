package csense.kotlin.test.assertions

import kotlin.test.assertEquals

fun <T> Array<T>.assertSize(size: Int, message: String = "") {
    this.size.assert(size, message)
}

fun <T> Collection<T>.assertSize(size: Int, message: String = "") {
    this.size.assert(size, message)
}

fun <T> Collection<T>.assertEmpty(message: String = "") {
    assertSize(0, message)
}


fun List<*>.assertEmpty(message: String = "") {
    assertSize(0, message)
}

fun List<*>.assertSize(size: Int, message: String = "") {
    assertEquals(this.size, size, message)
}