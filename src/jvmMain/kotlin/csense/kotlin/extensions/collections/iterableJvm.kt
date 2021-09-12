@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.extensions.collections

/**
 * Creates a string from all the elements separated using the [System.lineSeparator] and using the given [prefix] and [postfix] if supplied.
 *
 * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
 * elements will be appended, followed by the [truncated] string (which defaults to "...").
 *
 * @sample samples.collections.Collections.Transformations.joinToString
 */
public inline fun <T> Iterable<T>.joinToStringNewLine(
    prefix: CharSequence = "",
    postfix: CharSequence = "",
    limit: Int = -1,
    truncated: CharSequence = "...",
    noinline transform: ((T) -> CharSequence)? = null
): String {
    return joinToString(System.lineSeparator(), prefix, postfix, limit, truncated, transform)
}
