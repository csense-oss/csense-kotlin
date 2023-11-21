@file:Suppress("unused", "NOTHING_TO_INLINE")


package csense.kotlin.extensions.primitives.string

import csense.kotlin.extensions.primitives.char.*
import csense.kotlin.extensions.primitives.charSequence.*
import csense.kotlin.specificExtensions.string.*


/**
 * Returns whenever this string ends with at least one of the given collection
 * @receiver C
 * @param collection [Collection]<C>
 * @param ignoreCase [Boolean]
 * @return [Boolean]
 */
public inline fun String.endsWithAny(
    collection: Collection<String>,
    ignoreCase: Boolean = false
): Boolean =
    collection.any { this.endsWith(it, ignoreCase) }

/**
 * Returns whenever this string ends with at least one of the given Array
 * @receiver [String] the string to ask if it ends with any of [strings]
 * @param strings [Array]<out C> the strings we want to tell if this ends with
 * @param ignoreCase [Boolean]
 * @return [Boolean]
 * @timecomplexity o(n*m) where n is this strings length and m is the size of all any strings
 */
public inline fun String.endsWithAny(
    vararg strings: String,
    ignoreCase: Boolean = false
): Boolean =
    strings.any { this.endsWith(it, ignoreCase) }


public inline fun String.endsWith(
    suffix: String,
    ignoreCase: Boolean = false,
    ignoreWhitespace: Boolean
): Boolean {

    if (this === suffix) {
        return true
    }

    if (!ignoreWhitespace) {
        return endsWith(suffix = suffix, ignoreCase = ignoreCase)
    }

    val lastNonWhitespaceIndexInclusive = indexOfLastOrNull { it.isNotWhitespace() } ?: return false
    return comparison.containsStringEndingAt(
        endIndex = lastNonWhitespaceIndexInclusive,
        other = suffix,
        ignoreCase = ignoreCase
    )
}