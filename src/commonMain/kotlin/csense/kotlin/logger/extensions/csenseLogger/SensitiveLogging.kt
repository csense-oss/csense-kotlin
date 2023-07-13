@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.logger.extensions.csenseLogger

import csense.kotlin.logger.*

public inline fun CsenseLogger.disableSensitiveLogging() {
    isSensitiveLoggingEnabled = false
}

public inline fun CsenseLogger.enableSensitiveLogging() {
    isSensitiveLoggingEnabled = true
}