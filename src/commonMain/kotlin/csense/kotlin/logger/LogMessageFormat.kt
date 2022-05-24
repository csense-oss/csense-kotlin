package csense.kotlin.logger

import csense.kotlin.extensions.collections.array.*
import csense.kotlin.specificExtensions.string.*

public sealed class LogMessageFormat {
    public abstract val message: String


    public class InsensitiveValues(
        override val message: String,
        public val placeholders: Array<out String>,
        public val expectedSensitivity: LogSensitivity
    ) : LogMessageFormat() {
        override fun toString(): String {
            val prefix = if (containsSensitiveInformation()) {
                "(Contains sensitive data)\t"
            } else {
                ""
            }
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

    public fun replacePlaceholdersIndexed(onPlaceholder: (placeholderCount: Int) -> String): String {
        var count = 0
        return message.modifications.replaceEachOccurence(placeholderValue) {
            val placeholder = onPlaceholder(count)
            count += 1
            placeholder
        }

    }


    public companion object {
        public const val placeholderValue: String = "{}"
        public const val sensitiveValue: String = "***"
        public const val missingPublicValue: String = "<Missing>"

    }


}
