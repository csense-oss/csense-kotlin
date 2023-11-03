package csense.kotlin.logger.extensions

import csense.kotlin.logger.models.*


public fun LogMessage.containsSensitiveInformation(): Boolean =
    message.sensitivity == LogSensitivity.Sensitive

public fun LogMessage.doesNotContainSensitiveInformation(): Boolean {
    return !containsSensitiveInformation()
}