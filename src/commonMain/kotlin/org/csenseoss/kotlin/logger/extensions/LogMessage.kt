package org.csenseoss.kotlin.logger.extensions

import org.csenseoss.kotlin.logger.models.*


public fun LogMessage.containsSensitiveInformation(): Boolean =
    message.sensitivity == LogSensitivity.Sensitive

public fun LogMessage.doesNotContainSensitiveInformation(): Boolean {
    return !containsSensitiveInformation()
}