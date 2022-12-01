package csense.kotlin.logger.extensions

import csense.kotlin.extensions.collections.array.generic.*
import csense.kotlin.logger.models.*


public fun LogMessageFormat.containsSensitiveInformationMessageOrEmpty(): String {
    return if (containsSensitiveInformation()) {
        "(Contains sensitive information) "
    } else {
        ""
    }
}

public fun LogMessageFormat.toColoredOutput(): String {
    return when (this) {
        is LogMessageFormat.InsensitiveValues -> toColoredOutput()
        is LogMessageFormat.SensitiveValues -> toColoredOutput()
    }
}

public fun LogMessageFormat.InsensitiveValues.toColoredOutput(): String {
    val prefix = containsSensitiveInformationMessageOrEmpty()

    val color = if (containsSensitiveInformation()) {
        AnsiConsoleEscapeCodes.redTextColor
    } else {
        AnsiConsoleEscapeCodes.blueTextColor
    }

    return prefix + replacePlaceholdersIndexed { placeHolderIndex ->
        color + placeholders.getOr(
            placeHolderIndex,
            LogMessageFormat.missingPublicValue
        ) + AnsiConsoleEscapeCodes.resetCode
    }
}

public fun LogMessageFormat.SensitiveValues.toColoredOutput(): String {
    return message.replace(
        LogMessageFormat.placeholderValue,
        AnsiConsoleEscapeCodes.greenTextColor + LogMessageFormat.sensitiveValue + AnsiConsoleEscapeCodes.resetCode
    )
}

public fun LogMessage.toFullColoredLog(): String {
    val tagLog = "[" + AnsiConsoleEscapeCodes.cyanTextColor + tag + AnsiConsoleEscapeCodes.resetCode + "]"

    val messageLog = " " + message.toColoredOutput()

    //TODO consider sensitive stacktrace
    val exceptionLog = throwable?.stackTraceToString()?.let {
        "\n" + AnsiConsoleEscapeCodes.redTextColor + it + AnsiConsoleEscapeCodes.resetCode
    } ?: ""

    return tagLog + messageLog + exceptionLog
}
