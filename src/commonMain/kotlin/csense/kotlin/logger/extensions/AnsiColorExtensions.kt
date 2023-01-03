package csense.kotlin.logger.extensions

import csense.kotlin.extensions.collections.array.generic.*
import csense.kotlin.logger.models.*


public fun LogMessageFormat.containsSensitiveInformationMessageOrEmpty(): String {
    //TODO consider moving into classe(s) as this is core functionality
    return if (containsSensitiveInformation) {
        "(Contains sensitive information) "
    } else {
        ""
    }
}

public fun LogMessageFormat.toColoredOutput(): String = when (this) {
    is LogMessageFormat.InsensitiveValues -> toColoredOutput()
    is LogMessageFormat.SensitiveValues -> toColoredOutput()
}

public fun LogMessageFormat.InsensitiveValues.toColoredOutput(): String {
    val prefix = containsSensitiveInformationMessageOrEmpty()

    val color = if (containsSensitiveInformation) {
        AnsiConsoleEscapeCodes.redTextColor
    } else {
        AnsiConsoleEscapeCodes.blueTextColor
    }

    return prefix + replacePlaceholdersIndexed { placeHolderIndex ->
        color + placeholders.getOr(
            index = placeHolderIndex,
            defaultValue = LogMessageFormat.missingPublicValue
        ) + AnsiConsoleEscapeCodes.resetCode
    }
}

public fun LogMessageFormat.SensitiveValues.toColoredOutput(): String = replacePlaceholdersIndexed {
    AnsiConsoleEscapeCodes.greenTextColor + LogMessageFormat.sensitiveValue + AnsiConsoleEscapeCodes.resetCode
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

/**
 * A simple container for ANSI escape codes + some colors (not a complete list)
 * eg see https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
 */
public object AnsiConsoleEscapeCodes {
    public const val redTextColor: String = "\u001B[31m"
    public const val greenTextColor: String = "\u001B[32m"
    public const val yellowTextColor: String = "\u001B[33m"
    public const val blueTextColor: String = "\u001B[34m"
    public const val purpleTextColor: String = "\u001B[35m"
    public const val cyanTextColor: String = "\u001B[36m"
    public const val whiteTextColor: String = "\u001B[36m"
    public const val resetCode: String = "\u001B[0m"
}