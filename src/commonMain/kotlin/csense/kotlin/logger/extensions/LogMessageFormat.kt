package csense.kotlin.logger.extensions

import csense.kotlin.logger.models.*


public fun LogMessageFormat.doesNotContainSensitiveInformation(): Boolean {
    return !sensitivity.isSensitive()
}

public fun LogMessageFormat.containsSensitiveInformationMessageOrEmpty(): String = when (sensitivity) {
    LogSensitivity.Sensitive -> "(Contains sensitive information) "
    LogSensitivity.Insensitive -> ""
}
