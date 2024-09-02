package org.csenseoss.kotlin.extensions.collections.iterable

import org.csenseoss.kotlin.extensions.collections.iterable.string.*


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