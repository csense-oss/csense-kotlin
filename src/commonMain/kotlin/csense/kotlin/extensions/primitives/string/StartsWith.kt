@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.primitives.string

import csense.kotlin.extensions.primitives.char.*
import csense.kotlin.extensions.primitives.charSequence.*
import csense.kotlin.specificExtensions.string.*


/**
 * Returns whenever this string starts with at least one of the given collection
 * @receiver C
 * @param strings [Array]<out C>
 * @param ignoreCase [Boolean]
 * @return [Boolean]
 */
public inline fun String.startsWithAny(
    vararg strings: String,
    ignoreCase: Boolean = false
): Boolean =
    strings.any { this.startsWith(it, ignoreCase) }

/**
 * Returns whenever this string starts with at least one of the given collection
 * @receiver C
 * @param collection [Collection]<C>
 * @param ignoreCase [Boolean]
 * @return [Boolean]
 */
public inline fun String.startsWithAny(
    collection: Collection<String>,
    ignoreCase: Boolean = false
): Boolean =
    collection.any { this.startsWith(it, ignoreCase) }


public inline fun String.startsWith(
    prefix: String,
    ignoreCase: Boolean = false,
    ignoreWhitespace: Boolean
): Boolean {
    if (this === prefix) {
        return true
    }

    if (!ignoreWhitespace) {
        return startsWith(prefix = prefix, ignoreCase = ignoreCase)
    }
    val firstNonWhitespaceIndex: Int = indexOfFirstOrNull { it.isNotWhitespace() } ?: return false
    return comparison.containsStringAt(
        startIndex = firstNonWhitespaceIndex,
        other = prefix,
        ignoreCase = ignoreCase
    )
}
