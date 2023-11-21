@file:Suppress("unused", "NOTHING_TO_INLINE")


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
public inline fun String.equals(
    other: String?,
    ignoreCase: Boolean = false,
    ignoreWhitespace: Boolean
): Boolean {
    if (this === other) {
        return true
    }
    if (!ignoreWhitespace) {
        return equals(other = other, ignoreCase = ignoreCase)
    }

    //will be null if empty or all is whitespace
    val firstNonWhitespaceInThis: Int? = this.indexOfFirstOrNull { it.isNotWhitespace() }
    val firstNonWhitespaceInOther: Int? = other?.indexOfFirstOrNull { it.isNotWhitespace() }
    if (isAnyNull(firstNonWhitespaceInThis, firstNonWhitespaceInOther)) {
        return firstNonWhitespaceInThis == firstNonWhitespaceInOther
    }
    val lastNonWhitespaceInThis: Int? = this.indexOfLastOrNull { it.isNotWhitespace() }
    val lastNonWhitespaceInOther: Int? = other.indexOfLastOrNull { it.isNotWhitespace() }
    if (isAnyNull(lastNonWhitespaceInThis, lastNonWhitespaceInOther)) {
        return false //should not happen...
    }

    val nonWhitespaceLengthOfThis: Int = lastNonWhitespaceInThis - firstNonWhitespaceInThis
    val nonWhitespaceLengthOfOther: Int = lastNonWhitespaceInOther - firstNonWhitespaceInOther
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


