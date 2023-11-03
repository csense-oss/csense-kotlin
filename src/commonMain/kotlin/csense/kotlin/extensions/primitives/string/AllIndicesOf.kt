@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.extensions.primitives.string

import csense.kotlin.annotations.numbers.*
import csense.kotlin.specificExtensions.string.*


/**
 * Finds all indices of the given substring
 * @receiver [String] The string we are searching in
 * @param subString [String] the substring we are searching for
 * @param searchByWord [Boolean]
 * @param ignoreCase [Boolean] if we should ignore casing
 * @return [Set]<[Int]> a set of indices
 */
public inline fun String.allIndicesOf(
    subString: String,
    searchByWord: Boolean,
    ignoreCase: Boolean = false
): Set<@IntLimit(from = 0) Int> {
    return modifications.mapEachMatching(
        subString = subString,
        searchByWord = searchByWord,
        ignoreCase = ignoreCase
    ) { start: Int -> start }.toSet()
}
