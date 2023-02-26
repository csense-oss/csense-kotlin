@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.throwable

import csense.kotlin.extensions.collections.generic.collection.*
import csense.kotlin.extensions.collections.generic.collection.operations.*
import csense.kotlin.extensions.stringBuilder.*


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
    GenericCollections.traverseWhileNotNullAndNoCycles(
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