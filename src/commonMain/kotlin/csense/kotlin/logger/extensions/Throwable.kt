package csense.kotlin.logger.extensions

import csense.kotlin.extensions.general.*

public fun Throwable.senstiveStackTraceToString(): String {
    val thisClassText = "${this::class.simpleNameOrUnknown} exception"
    val directCauseText: String = cause?.let { it: Throwable -> " due to exception of ${it::class.simpleName}" } ?: ""
    return thisClassText + directCauseText
}