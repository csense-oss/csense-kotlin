package csense.kotlin.extensions

import java.io.*

/**
 *  Converts a throwable to a string with the stacktrace
 * @receiver Throwable
 * @return String
 */
fun Throwable.stackTraceToString(): String =
        StringWriter().use {
            printStackTrace(PrintWriter(it))
            return it.toString()
        }