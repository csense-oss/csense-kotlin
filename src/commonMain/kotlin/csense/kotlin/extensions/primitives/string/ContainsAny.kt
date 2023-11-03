@file:Suppress("NOTHING_TO_INLINE")
package csense.kotlin.extensions.primitives.string


/**
 *
 * @receiver [String]
 * @param strings [Array]<out [String]>
 * @param ignoreCase [Boolean]  `true` to ignore character case when comparing strings. By default `false`.
 * @return [Boolean]
 * @timecomplexity O(n*m) where n is the length of this string, and m is the size of strings (times the average length)
 * This will be improved in later versions to use a better algorithm for larger data sets
 */
public inline fun String.containsAny(vararg strings: String, ignoreCase: Boolean = false): Boolean = strings.any {
    this.contains(it, ignoreCase)
}

/**
 *
 * @receiver [String]
 * @param collection [Iterable]<[String]>
 * @param ignoreCase [Boolean] `true` to ignore character case when comparing strings. By default `false`.
 * @return [Boolean]
 * @timecomplexity O(n*m) where n is the length of this string, and m is the size of strings (times the average length)
 * This will be improved in later versions to use a better algorithm for larger data sets
 */
public inline fun String.containsAny(
    collection: Iterable<String>,
    ignoreCase: Boolean = false
): Boolean = collection.any {
    this.contains(it, ignoreCase)
}