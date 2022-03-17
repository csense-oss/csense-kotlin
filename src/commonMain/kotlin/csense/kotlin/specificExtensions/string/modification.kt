@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.specificExtensions.string

import csense.kotlin.annotations.numbers.*
import csense.kotlin.extensions.collections.generic.*
import csense.kotlin.extensions.primitives.*
import kotlin.jvm.*

@JvmInline
public value class StringModification(public val string: String) {
    public data class StringSplitAt(
        public val beforeIndex: String,
        public val afterIndex: String
    )
}

public inline val String.modifications: StringModification
    inline get() = StringModification(this)


/**
 * Limits this [String] to the given number of characters
 * @receiver [String] the [String] to limit
 * @param maxLength [Int] the max length
 * @return [String] a [String] at max the given length
 *
 * if [maxLength] [Int.isNegativeOrZero], an empty string is returned
 */
public inline fun StringModification.limitTo(@IntLimit(from = 0) maxLength: Int): String {
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
public inline fun StringModification.wrapIn(prefix: String, postFix: String): String =
    prefix + string + postFix

/**
 * As the name implies wraps the given receiver into a pair of quotes at each end
 * @receiver [String]
 * @return [String] the wrapped string.
 */
public inline fun StringModification.wrapInQuotes(): String = wrapIn("\"", "\"")

@Throws(IndexOutOfBoundsException::class)
public inline fun StringModification.replaceCharAt(index: Int, withChar: Char): String {
    return replaceCharAtOrNull(index = index, withChar = withChar)
        ?: throw IndexOutOfBoundsException("Index out of bounds. Index: $index, length: ${string.length}")
}


/**
 *
 * @receiver String
 * @param index Int
 * @param withChar Char
 * @return String?
 */
public inline fun StringModification.replaceCharAtOrNull(index: Int, withChar: Char): String? = with(string) {
    if (isIndex.outOfBounds(index, isEndOutOfBonds = true)) {
        return@with null
    }
    val split = splitAtOrNull(index) ?: return@with null
    return@with split.beforeIndex + withChar + split.afterIndex
}

public inline fun StringModification.splitAtOrNull(index: Int): StringModification.StringSplitAt? = with(string) {
    if (isIndex.outOfBounds(index, isEndOutOfBonds = true)) {
        return@with null
    }
    val before = substringOrNull(0, index) ?: ""
    val after = substringOrNull(index + 1, length) ?: ""
    return@with StringModification.StringSplitAt(before, after)
}

