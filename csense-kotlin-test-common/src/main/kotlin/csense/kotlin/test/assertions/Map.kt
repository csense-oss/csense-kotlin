@file:Suppress("unused", "NOTHING_TO_INLINE", "MissingTestFunction")

package csense.kotlin.test.assertions

/**
 * Assert that this map have the given size.
 * @receiver Map<*, *>
 * @param expectedSize Int
 * @param message String
 */
fun Map<*, *>.assertSize(expectedSize: Int, message: String = "") = this.size.assert(expectedSize, message)

/**
 * Asserts that this map is empty.
 * @receiver Map<*, *>
 * @param message String
 */
fun Map<*, *>.assertEmpty(message: String = "Map should be empty but is not.") = this.isEmpty().assertTrue(message)