package csense.kotlin.extensions.collections.typed

/**
 * Does this iterable contains the given string (potentially ignoring the casing)
 * @receiver [Iterable]<[String]> the collection to search tough
 * @param other [String] the string to find
 * @param ignoreCase [Boolean] if true will ignore casing
 * @return [Boolean] true if other is contained (depending on ignore case) or false if not.
 * @timecomplexity O(n)
 */
fun Iterable<String>.contains(other: String, ignoreCase: Boolean): Boolean = any { ourString ->
    ourString.equals(other, ignoreCase)
}