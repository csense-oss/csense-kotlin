package csense.kotlin.logger.extensions

import csense.kotlin.logger.models.*


public fun LogMessageFormat.doesNotContainSensitiveInformation(): Boolean {
    return sensitivity.isNotSensitive()
}

public fun LogMessageFormat.containsSensitiveInformationMessageOrEmpty(): String = when (sensitivity) {
    LogSensitivity.Sensitive -> "(Contains sensitive information) "
    LogSensitivity.Insensitive -> ""
}
