package csense.kotlin.logger


public fun LogMessage.containsSensitiveInformation(): Boolean {
    return message.containsSensitiveInformation()
}

public fun LogMessageFormat.containsSensitiveInformation(): Boolean {
    return when (this) {
        is LogMessageFormat.InsensitiveValues -> expectedSensitivity == LogSensitivity.Sensitive
        is LogMessageFormat.SensitiveValues -> true
    }
}

public fun LogMessage.doesNotContainSensitiveInformation(): Boolean {
    return !containsSensitiveInformation()
}

public fun LogMessageFormat.doesNotContainSensitiveInformation(): Boolean {
    return !containsSensitiveInformation()
}
