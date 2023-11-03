@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.extensions.primitives.string


/**
 * Tells if this string ends with something different from the given [sequence] or not
 * @receiver [String]
 * @param sequence [String]
 * @param ignoreCase [Boolean]
 * @return [Boolean]
 */
public inline fun String.doesNotEndsWith(
    sequence: CharSequence,
    ignoreCase: Boolean = false
): Boolean =
    !endsWith(sequence, ignoreCase)

public inline fun String.doesNotEndsWith(
    char: Char,
    ignoreCase: Boolean = false
): Boolean =
    !endsWith(char, ignoreCase)

public inline fun String.doesNotEndsWithAny(
    vararg subStrings: String,
    ignoreCase: Boolean = false
): Boolean =
    !endsWithAny(*subStrings, ignoreCase = ignoreCase)

public inline fun String.doesNotEndsWithAny(
    items: Collection<String>,
    ignoreCase: Boolean = false
): Boolean =
    !endsWithAny(items, ignoreCase)