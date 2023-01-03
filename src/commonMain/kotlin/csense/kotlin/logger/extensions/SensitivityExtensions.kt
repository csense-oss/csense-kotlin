package csense.kotlin.logger.extensions

import csense.kotlin.logger.models.*


public fun LogMessage.containsSensitiveInformation(): Boolean =
    message.containsSensitiveInformation

public fun LogMessage.doesNotContainSensitiveInformation(): Boolean {
    return !containsSensitiveInformation()
}

public fun LogMessageFormat.doesNotContainSensitiveInformation(): Boolean {
    return !containsSensitiveInformation
}
