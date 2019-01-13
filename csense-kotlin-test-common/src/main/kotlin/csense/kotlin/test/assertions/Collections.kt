@file:Suppress("unused", "NOTHING_TO_INLINE")
package csense.kotlin.test.assertions

import kotlin.test.*

/**
 * Asserts that the size of this array is the given size.
 * @receiver Array<T>
 * @param size Int
 * @param message String
 */
fun <T> Array<T>.assertSize(size: Int, message: String = "") {
    this.size.assert(size, message)
}

/**
 * Asserts that the size of this collection is the given size
 * @receiver Collection<T>
 * @param size Int
 * @param message String
 */
fun <T> Collection<T>.assertSize(size: Int, message: String = "") {
    this.size.assert(size, message)
}

/**
 * Asserts that this collection is empty
 * @receiver Collection<T>
 * @param message String
 */
fun <T> Collection<T>.assertEmpty(message: String = "") {
    assertSize(0, message)
}


/**
 * Asserts that this list is empty.
 * @receiver List<*>
 * @param message String
 */
fun List<*>.assertEmpty(message: String = "") {
    assertSize(0, message)
}

/**
 * Asserts that this list is the given size.
 * @receiver List<*>
 * @param size Int
 * @param message String
 */
fun List<*>.assertSize(size: Int, message: String = "") {
    assertEquals(size, this.size, message)
}