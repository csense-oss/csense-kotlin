package csense.kotlin.logger.models

import csense.kotlin.extensions.collections.array.generic.*
import csense.kotlin.logger.extensions.*
import csense.kotlin.specificExtensions.string.*

public sealed class LogMessageFormat {

    public abstract val message: String

    public abstract val sensitivity: LogSensitivity

    abstract override fun toString(): String

    public fun replacePlaceholdersIndexed(onPlaceholder: (placeholderCount: Int) -> String): String =
        message.modifications.replaceEachOccurrenceIndexed(placeholderValue) { index: Int ->
            onPlaceholder(index)
        }


    public class InsensitiveValues(
        override val message: String,
        public val placeholders: Array<out String>,
        public val expectedSensitivity: LogSensitivity
    ) : LogMessageFormat() {

        override val sensitivity: LogSensitivity = expectedSensitivity

        override fun toString(): String {
            val prefix: String = containsSensitiveInformationMessageOrEmpty()
            return prefix + replacePlaceholdersIndexed { index: Int ->
                placeholders.getOr(
                    index = index,
                    defaultValue = missingPublicValue
                )
            }
        }

    }

    public class SensitiveValues(
        override val message: String,
    ) : LogMessageFormat() {
        override val sensitivity: LogSensitivity = LogSensitivity.Sensitive

        override fun toString(): String {
            return message.replace(oldValue = placeholderValue, newValue = sensitiveValue)
        }

    }

    public companion object {
        public const val placeholderValue: String = "{}"
        public const val sensitiveValue: String = "***"
        public const val missingPublicValue: String = "<Missing>"
    }
}
