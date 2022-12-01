package csense.kotlin.logger.models

import csense.kotlin.extensions.collections.array.generic.*
import csense.kotlin.logger.extensions.*
import csense.kotlin.specificExtensions.string.*

public sealed class LogMessageFormat {

    public abstract val message: String

    abstract override fun toString(): String

    public fun replacePlaceholdersIndexed(onPlaceholder: (placeholderCount: Int) -> String): String =
        message.modifications.replaceEachOccurrenceIndexed(placeholderValue) { index ->
            onPlaceholder(index)
        }


    public class InsensitiveValues(
        override val message: String,
        public val placeholders: Array<out String>,
        public val expectedSensitivity: LogSensitivity
    ) : LogMessageFormat() {
        override fun toString(): String {
            val prefix = containsSensitiveInformationMessageOrEmpty()
            return prefix + replacePlaceholdersIndexed { index -> placeholders.getOr(index, missingPublicValue) }
        }
    }

    public class SensitiveValues(
        override val message: String,
    ) : LogMessageFormat() {
        override fun toString(): String {
            return message.replace(placeholderValue, sensitiveValue)
        }
    }

    public companion object {
        public const val placeholderValue: String = "{}"
        public const val sensitiveValue: String = "***"
        public const val missingPublicValue: String = "<Missing>"
    }
}
