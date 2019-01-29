@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.primitives

import csense.kotlin.*
import csense.kotlin.extensions.*
import csense.kotlin.patterns.*


/**
 *
 * @receiver String
 * @return String?
 */
@Suppress("NOTHING_TO_INLINE")
inline fun String.fileExtension(): String? {
    return lastIndexOf('.').let {
        if (it > 0 && it + 1 < length) {
            return substring(it + 1)
        } else {
            null
        }
    }
}

//<editor-fold desc="Index of safe">
/**
 *
 * @receiver String
 * @param subString String
 * @param index Int
 * @param ignoreCase Boolean
 * @return Expected<Int>
 */
fun String.indexOfSafe(subString: String, index: Int, ignoreCase: Boolean): Expected<Int> {
    val result = indexOf(subString, index, ignoreCase)
    return if (result == -1) {
        failedIndexOfExpected
    } else {
        expectedSucceded(result)
    }
}

private val failedIndexOfExpected by lazy { ExpectedFailed<Int>(IndexOfMissingException) }

/**
 *
 */
object IndexOfMissingException : RuntimeException("Unable to find substring")
//</editor-fold>

/**
 *
 * @receiver String.Companion
 * @param charArray CharArray
 * @return String
 */
inline fun String.Companion.createFromChars(charArray: CharArray): String {
    return StringBuilder().appendContentOf(charArray).toString()
}


/**
 *
 * @receiver String
 * @param subString String
 * @param searchByWord Boolean
 * @param ignoreCase Boolean
 * @return Set<Int>
 */
@Suppress("NOTHING_TO_INLINE")
inline fun String.findAllOf(subString: String,
                            searchByWord: Boolean,
                            ignoreCase: Boolean = false): Set<Int> {
    return forEachMatching(subString, searchByWord, ignoreCase) { start -> start }.toSet()
}

/**
 *
 * @receiver String
 * @param subString String
 * @param searchByWord Boolean
 * @param ignoreCase Boolean
 * @param mapper Function1<Int, U>
 * @return List<U>
 */
inline fun <U> String.forEachMatching(subString: String,
                                      searchByWord: Boolean,
                                      ignoreCase: Boolean = false,
                                      crossinline mapper: Function1<Int, U>): List<U> {
    if (subString.isEmpty() || this.isEmpty()) {
        return listOf()
    }
    var currentIndex = 0
    val appendLenght = searchByWord.map(subString.length, 1)
    var next = this.indexOfSafe(subString, currentIndex, ignoreCase)
    val result = mutableListOf<U>()
    while (next.isValid) {
        result.add(mapper(next.value))
        currentIndex = next.value + appendLenght
        next = this.indexOfSafe(subString, currentIndex, ignoreCase)
    }
    return result
}


/**
 * Replaces a value given a criteria. if the condition is true, the replace is called with the value
 * otherwise this string is returned as is.
 * @receiver String
 * @param condition Boolean
 * @param toReplace String
 * @param newValue String
 * @param ignoreCase Boolean
 * @return String
 */
inline fun String.replaceIf(condition: Boolean,
                            toReplace: String,
                            newValue: String,
                            ignoreCase: Boolean = false): String {
    return if (condition) {
        this.replace(toReplace, newValue, ignoreCase)
    } else {
        this
    }
}

/**
 * Replaces a value given a criteria. if the condition is true, the ifTrueValue is used for the replacement
 * if the condition is false, the ifFalseValue is used.
 * @receiver String
 * @param condition Boolean
 * @param toReplace String
 * @param ifTrueValue String
 * @param ifFalseValue String
 * @param ignoreCase Boolean
 * @return String
 */
inline fun String.replaceIfOr(condition: Boolean,
                              toReplace: String,
                              ifTrueValue: String,
                              ifFalseValue: String,
                              ignoreCase: Boolean = false): String {
    val replacement = condition.map(ifTrueValue, ifFalseValue)
    return this.replace(toReplace, replacement, ignoreCase)
}

/**
 * Replaces a value given a criteria. if the condition is true, the ifTrueValue is used for the replacement
 * if the condition is false, the ifFalseValue is used. lazily evaluates the values.
 * @receiver String
 * @param condition Boolean
 * @param toReplace String
 * @param ifTrueValue EmptyFunctionResult<String>
 * @param ifFalseValue EmptyFunctionResult<String>
 * @param ignoreCase Boolean
 * @return String
 */
inline fun String.replaceIfOr(condition: Boolean,
                              toReplace: String,
                              crossinline ifTrueValue: EmptyFunctionResult<String>,
                              crossinline ifFalseValue: EmptyFunctionResult<String>,
                              ignoreCase: Boolean = false): String {
    val replacement = condition.mapInvoke(ifTrueValue, ifFalseValue)
    return this.replace(toReplace, replacement, ignoreCase)
}


/**
 *
 * @receiver String
 * @param toInsert Array<out StringInserts>
 * @return String? null if any index is outside of bounds
 */
fun String.insertInto(vararg toInsert: StringInserts): String? {
    val size = count()

    val sb = StringBuilder()
    toInsert.sortBy { it.atIndex } //make sure its sorted, such that we are never run into any issues.
    val lastInsertIndex = toInsert.lastOrNull()?.atIndex
    if (lastInsertIndex != null && lastInsertIndex > size) {
        return null
    }
    //all indexes are guaranteed to be in this string. or either the test or sort does not work.
    var currentToIndex = 0
    var currentLastIndex: Int
    toInsert.forEach {
        currentLastIndex = currentToIndex
        currentToIndex = it.atIndex

        sb.append(this.subSequence(currentLastIndex, currentToIndex))
        sb.append(it.toInsert)
    }
    if (currentToIndex < size) {
        sb.append(this.substring(currentToIndex))
    }
    return sb.toString()
}

/**
 * A class representing an insert to be made into a string
 * @property toInsert String the string to insert
 * @property atIndex Int at what raw index in the string
 */
data class StringInserts(val toInsert: String, val atIndex: Int)


/**
 *  Limits this string to the given number of characters
 * @receiver String the string to potentially limit
 * @param maxLength Int the max length
 * @return String a string at max the given max length;
 * if maxLength is zero or negative, and empty string is returned
 */
inline fun String.limitTo(maxLength: Int): String {
    if (maxLength.isNegative || maxLength.isZero) {
        return ""
    }
    return substring(0, minOf(maxLength, length))
}