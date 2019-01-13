@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.test.assertions

fun Map<*, *>.assertSize(expectedSize: Int, message: String = "") = this.size.assert(expectedSize, message)