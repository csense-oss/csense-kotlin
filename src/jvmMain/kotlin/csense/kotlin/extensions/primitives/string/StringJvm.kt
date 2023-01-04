@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.extensions.primitives.string

import csense.kotlin.extensions.primitives.char.*
import csense.kotlin.extensions.primitives.charSequence.*
import csense.kotlin.specificExtensions.string.*
import java.util.*


public fun String(codepoints: IntArray): String {
    if (codepoints.isEmpty()) {
        return ""
    }
    return String(codePoints = codepoints, offset = 0, length = codepoints.size)
}

/**
 *
 * Does NOT handle surrogate chars / utf16
 * @receiver String
 * @param locale Locale
 * @return String
 */
@Throws(IndexOutOfBoundsException::class)
public inline fun String.titleCaseFirstWord(locale: Locale): String {
    val firstCharIndex = indexOfFirstOrNull { it.isNotWhitespace() } ?: return this
    val firstChar = this[firstCharIndex]
    val firstCharString = firstChar.toString()
    val firstAsTitleCase = firstChar.titlecase(locale)
    if (firstCharString == firstAsTitleCase) {
        return this
    }
    val split = modifications.splitAtOrNull(firstCharIndex)
        ?: throw IndexOutOfBoundsException("index $firstCharIndex >= $length")
    return split.beforeIndex + firstAsTitleCase + split.afterIndex
}
