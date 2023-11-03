@file:Suppress("NOTHING_TO_INLINE")
package csense.kotlin.extensions.primitives.string

import csense.kotlin.extensions.mapping.*
import csense.kotlin.extensions.primitives.char.*
import csense.kotlin.extensions.primitives.charSequence.*
import csense.kotlin.specificExtensions.string.*


/**
 * Title-cases this string's first word if it is not already title cased.
 * @return this string if empty or if the first char is title-cased.
 * otherwise, title-cases the first char
 */
public inline fun String.titleCaseFirstWord(): String = caseFirstWord(shouldBeTitleCase = true)

/**
 * lower-cases this string's first word if it is not already lower cased.
 * @return this string if empty or if the first char is lowercase.
 * otherwise, lowercase the first char
 */
public inline fun String.lowerCaseFirstWord(): String = caseFirstWord(shouldBeTitleCase = false)

/**
 * apply either a Title case or a lowercase to this string's first word
 * @param shouldBeTitleCase [Boolean] true means to title case, false means to lowercase
 * @return this string if empty or if already the given casing;  otherwise applies the given casing.
 */
public inline fun String.caseFirstWord(shouldBeTitleCase: Boolean): String {
    val firstCharIndex = indexOfFirstOrNull { it.isNotWhitespace() } ?: return this
    val firstChar = this[firstCharIndex]
    if (firstChar.isTitleCase()) {
        return this
    }
    val withChar = shouldBeTitleCase.mapLazy(
        ifTrue = firstChar::titlecaseChar,
        ifFalse = firstChar::lowercaseChar
    )
    return modifications.replaceCharAt(index = firstCharIndex, withChar = withChar)
}

