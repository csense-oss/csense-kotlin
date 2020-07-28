package csense.kotlin.specificExtensions.string

import csense.kotlin.annotations.numbers.*
import csense.kotlin.extensions.primitives.*

inline class StringModification(val string: String)

inline val String.modifications: StringModification
    inline get() = StringModification(this)


//region Modification / computing
/**
 * Limits this [String] to the given number of characters
 * @receiver [String] the [String] to limit
 * @param maxLength [Int] the max length
 * @return [String] a [String] at max the given length
 *
 * if [maxLength] [Int.isNegativeOrZero], an empty string is returned
 */
inline fun StringModification.limitTo(@IntLimit(from = 0) maxLength: Int): String {
    if (maxLength.isNegativeOrZero) {
        return ""
    }
    return string.substring(0, minOf(string.length, maxLength))
}


/**
 * Wraps this string with the given prefix and postfix strings.
 * @receiver [String]
 * @param prefix [String]
 * @param postFix [String]
 * @return [String] the combined result (prefix + this + postfix)
 */
inline fun StringModification.wrapIn(prefix: String, postFix: String): String =
        prefix + string + postFix

/**
 * As the name implies wraps the given receiver into a pair of quotes at each end
 * @receiver [String]
 * @return [String] the wrapped string.
 */
inline fun StringModification.wrapInQuotes(): String = wrapIn("\"", "\"")

//endregion