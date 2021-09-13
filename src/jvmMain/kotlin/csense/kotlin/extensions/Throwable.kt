@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions

import java.io.*

/**
 * Converts a throwable to a string with the stacktrace
 * @receiver [Throwable]
 * @return [String]
 */
public inline fun Throwable.stackTraceToString(): String = StringWriter().use {
    printStackTrace(PrintWriter(it))
    it.toString()
}
