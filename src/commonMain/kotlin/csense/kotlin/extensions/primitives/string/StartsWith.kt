@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")
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


@kotlin.internal.LowPriorityInOverloadResolution
public inline fun String.startsWith(
    prefix: String,
    ignoreCase: Boolean = false,
    ignoreWhitespace: Boolean = false
): Boolean {
    if (this === prefix) {
        return true
    }

    return if (!ignoreWhitespace) {
        startsWith(prefix = prefix, ignoreCase = ignoreCase)
    } else {
        val firstNonWhitespaceIndex = indexOfFirstOrNull { it.isNotWhitespace() } ?: return false
        return comparison.containsStringAt(
            startIndex = firstNonWhitespaceIndex,
            other = prefix,
            ignoreCase = ignoreCase
        )
    }
}
