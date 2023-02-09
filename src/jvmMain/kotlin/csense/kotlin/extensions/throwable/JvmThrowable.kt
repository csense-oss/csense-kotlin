@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.throwable

import csense.kotlin.extensions.generic.*
import csense.kotlin.extensions.stringBuilder.*
import csense.kotlin.patterns.generic.*


public inline fun Throwable.toSensitiveStackTraceString(): String {
    return formatToString(
        onException = { "${it::class.java.canonicalName} -message excluded-" },
        onStackTrace = {
            "\t at $it"
        },
        onSuppressed = {
            "Suppressed - ${it::class.java.canonicalName}"
        }
    )
}


public inline fun Throwable.formatToString(
    onException: (Throwable) -> String,
    onStackTrace: (StackTraceElement) -> String,
    onSuppressed: (Throwable) -> String,
): String {
    val result = StringBuilder()
    Generic.traverseWhileNotNullAndNoCycles(
        start = this,
        processCurrentLevel = {
            result.appendLineIfNotBlank(onException(it))
            it.stackTrace.forEach { stackTraceElement ->
                result.appendLineIfNotBlank(onStackTrace(stackTraceElement))
            }
            it.suppressed.forEach { suppressedElement ->
                result.appendLineIfNotBlank(onSuppressed(suppressedElement))
            }
        },
        getNextLevel = { it.cause }
    )
    return result.toString()
}