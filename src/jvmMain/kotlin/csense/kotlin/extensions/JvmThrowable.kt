@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions

import java.io.*

/**
 * Converts a throwable to a string with the stacktrace and message
 * @receiver [Throwable]
 * @return [String]
 */
public inline fun Throwable.stackTraceToString(): String {
    val writer = StringWriter()
    writer.use {
        PrintWriter(writer).use {
            printStackTrace(it)
        }
    }
    return writer.toString()
}
