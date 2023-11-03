@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.extensions.primitives.string


/**
 * Tells if this string does not start with the given [prefix]
 * @receiver [String] the string to test
 * @param prefix [CharSequence] the value we want to know if the receiver starts with or not
 * @param ignoreCase [Boolean] whenever we should ignore casing
 * @return [Boolean] true if this has a different start from [prefix] false if not
 */
public inline fun String.doesNotStartsWith(
    prefix: CharSequence,
    ignoreCase: Boolean = false
): Boolean =
    !startsWith(prefix, ignoreCase)

/**
 * Tells if this string does not start with the given [prefix]
 * @receiver [String] the string to test
 * @param prefix [Char] the [Char] we want to know if the receiver starts with or not
 * @param ignoreCase [Boolean] whenever we should ignore casing
 * @return [Boolean] true if this has a different start from [prefix] false if not
 */
public inline fun String.doesNotStartsWith(
    prefix: Char,
    ignoreCase: Boolean = false
): Boolean =
    !startsWith(prefix, ignoreCase)


/**
 * Tells if this string starts with something different from the given or not
 * @receiver [String] the string to test
 * @param subStrings [Array]<out [String]> the items to test if the receiver starts with or not
 * @param ignoreCase [Boolean] whenever we should ignore casing
 * @return [Boolean] true if this string starts with something different from any of the given [subStrings]
 */
public inline fun String.doesNotStartsWithAny(
    vararg subStrings: String,
    ignoreCase: Boolean = false
): Boolean =
    !startsWithAny(*subStrings, ignoreCase = ignoreCase)

/**
 * Tells if this string starts with something different from the given or not
 * @receiver [String] the string to test
 * @param items [Collection]<[String]> the items to test if the receiver starts with or not
 * @param ignoreCase [Boolean] whenever we should ignore casing
 * @return [Boolean] true if this string starts with something different from any of the given [items]
 */
public inline fun String.doesNotStartsWithAny(
    items: Collection<String>,
    ignoreCase: Boolean = false
): Boolean =
    !startsWithAny(items, ignoreCase)
