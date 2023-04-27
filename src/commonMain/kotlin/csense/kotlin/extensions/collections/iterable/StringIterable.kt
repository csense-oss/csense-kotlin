package csense.kotlin.extensions.collections.iterable

/**
 * Does this iterable contains the given string (potentially ignoring the casing)
 * @receiver [Iterable]<[String]> the collection to search tough
 * @param other [String] the string to find
 * @param ignoreCase [Boolean] if true will ignore casing
 * @return [Boolean] true if other is contained (depending on ignore case) or false if not.
 * @timecomplexity O(n)
 */
public fun Iterable<String>.contains(
    other: String,
    ignoreCase: Boolean
): Boolean = any { ourString: String ->
    ourString.equals(other, ignoreCase)
}

/**
 * Does this iterable NOT contain the given string (potentially ignoring the casing)
 * @receiver [Iterable]<[String]> the collection to search tough
 * @param other [String] the string to find
 * @param ignoreCase [Boolean] if true will ignore casing
 * @return [Boolean] true if other is NOT contained (depending on ignore case) or false if contained.
 * @timecomplexity O(n)
 */
public fun Iterable<String>.doesNotContain(
    other: String,
    ignoreCase: Boolean
): Boolean = !contains(
    other = other,
    ignoreCase = ignoreCase
)