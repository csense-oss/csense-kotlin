@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.specificExtensions.string

import csense.kotlin.annotations.numbers.*
import csense.kotlin.extensions.*
import csense.kotlin.extensions.collections.generic.*
import csense.kotlin.extensions.primitives.*
import kotlin.jvm.*


@JvmInline
public value class StringComparison(public val string: String)

public inline val String.comparison: StringComparison
    inline get() = StringComparison(this)

public inline fun StringComparison.compareTo(
    startingIndexInThisString: Int,
    other: String,
    startIndexInOtherString: Int,
    length: Int,
    ignoreCase: Boolean = false
): Boolean {
    for (i in 0 until length) {
        val thisChar = string.getOrNull(startingIndexInThisString + i)
        val otherChar = other.getOrNull(startIndexInOtherString + i)
        if (isAnyNull(thisChar, otherChar)) {
            return false
        }
        if (thisChar.isNotEqual(other = otherChar, ignoreCase = ignoreCase)) {
            return false
        }
    }
    return true
}

/**
 * Tells if [other] ends (inclusively) at [endIndex]
 * if [endIndex] is out of bounds it will return false
 * if [other] is empty it will return true
 * @param endIndex [Int] inclusive index where the string should end
 * @param other [String]
 * @param ignoreCase [Boolean]
 * @return [Boolean] returns true if [other] ends (inclusive) at [endIndex], false otherwise.
 */
public inline fun StringComparison.containsStringEndingAt(
    @IntLimit(from = 0) endIndex: Int,
    other: String,
    ignoreCase: Boolean = false
): Boolean {
    //+1 is to make the end index "inclusive"
    val startIndex = (endIndex + 1) - other.length
    return containsStringAt(
        startIndex = startIndex,
        other = other,
        ignoreCase = ignoreCase
    )
}


/**
 * Tests whenever [other] is in this string at [startIndex]
 * if the [startIndex] is out of bounds false is returned
 * if [other] is empty ([String.isEmpty]) then true is returned
 * @param startIndex [Int] where in [this] string to start the test
 * @param other [String] the string to test for
 * @param ignoreCase [Boolean] whenever casing should be ignored
 * @return [Boolean] true if other was found starting at [startIndex], false if not
 */

public inline fun StringComparison.containsStringAt(
    @IntLimit(from = 0) startIndex: Int,
    other: String,
    ignoreCase: Boolean = false
): Boolean = with(string) {
    if (isIndex.outOfBounds(index = startIndex, isEndOutOfBonds = true)) {
        return@with false
    }
    if (other.isEmpty()) {
        return@with true
    }
    val remainingLength = length - startIndex
    val canNotContainOtherString = remainingLength < other.length
    if (canNotContainOtherString) {
        return@with false
    }
    other.forEachIndexed { indexInOtherString, otherCharAtIndex ->
        val indexInThis = indexInOtherString + startIndex
        val areEqual = this.getOrNull(indexInThis)?.equals(other = otherCharAtIndex, ignoreCase = ignoreCase)
        if (areEqual.isNullOrFalse()) {
            return@with false
        }
    }
    return@with true
}