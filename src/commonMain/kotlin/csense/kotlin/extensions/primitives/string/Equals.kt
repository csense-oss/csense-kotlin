@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package csense.kotlin.extensions.primitives.string

import csense.kotlin.extensions.general.*
import csense.kotlin.extensions.primitives.char.*
import csense.kotlin.extensions.primitives.charSequence.*
import csense.kotlin.specificExtensions.string.*


//TODO clean / simplify?
/**
 * todo comment
 * something about this only "ignoring" whitespace at the ends (starting / ending)
 */
@kotlin.internal.LowPriorityInOverloadResolution
public inline fun String.equals(
    other: String?,
    ignoreCase: Boolean = false,
    ignoreWhitespace: Boolean = false
): Boolean {
    if (this === other) {
        return true
    }
    if (!ignoreWhitespace) {
        return equals(other = other, ignoreCase = ignoreCase)
    }

    //will be null if empty or all is whitespace
    val firstNonWhitespaceInThis = this.indexOfFirstOrNull { it.isNotWhitespace() }
    val firstNonWhitespaceInOther = other?.indexOfFirstOrNull { it.isNotWhitespace() }
    if (isAnyNull(firstNonWhitespaceInThis, firstNonWhitespaceInOther)) {
        return firstNonWhitespaceInThis == firstNonWhitespaceInOther
    }
    val lastNonWhitespaceInThis = this.indexOfLastOrNull { it.isNotWhitespace() }
    val lastNonWhitespaceInOther = other.indexOfLastOrNull { it.isNotWhitespace() }
    if (isAnyNull(lastNonWhitespaceInThis, lastNonWhitespaceInOther)) {
        return false //should not happen...
    }

    val nonWhitespaceLengthOfThis = lastNonWhitespaceInThis - firstNonWhitespaceInThis
    val nonWhitespaceLengthOfOther = lastNonWhitespaceInOther - firstNonWhitespaceInOther
    if (nonWhitespaceLengthOfThis != nonWhitespaceLengthOfOther) {
        return false
    }
    // at this point we have 2 ranges, 1 in this and one in other, that are the same length, so just compare them
    return comparison.compareTo(
        startingIndexInThisString = firstNonWhitespaceInThis,
        other = other,
        startIndexInOtherString = firstNonWhitespaceInOther,
        length = nonWhitespaceLengthOfThis,
        ignoreCase = ignoreCase
    )
}


