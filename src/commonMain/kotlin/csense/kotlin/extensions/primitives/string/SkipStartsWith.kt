@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.extensions.primitives.string

import csense.kotlin.extensions.mapping.*


/**
 * Skips the given part if it starts with it.
 * @receiver [String]
 * @param prefix [String] the prefix we are looking for (and the part that will be skipped iff there.)
 * @param ignoreCase [Boolean] how we should compare prefix with this string
 * @return [String] the resulting string, either the original or substring by the prefix length
 */
public inline fun String.skipStartsWith(
    prefix: String,
    ignoreCase: Boolean = false
): String {
    val startsWith = startsWith(prefix, ignoreCase)
    return startsWith.mapLazy(
        ifTrue = { substring(prefix.length) },
        ifFalse = { this })
}