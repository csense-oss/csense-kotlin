package csense.kotlin.extensions.primitives

import csense.kotlin.*
import csense.kotlin.extensions.*
import csense.kotlin.patterns.Expected
import csense.kotlin.patterns.ExpectedFailed
import csense.kotlin.patterns.expectedSucceded


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
fun String.indexOfSafe(subString: String, index: Int, ignoreCase: Boolean): Expected<Int> {
    val result = indexOf(subString, index, ignoreCase)
    return if (result == -1) {
        failedIndexOfExpected
    } else {
        expectedSucceded(result)
    }
}

private val failedIndexOfExpected by lazy { ExpectedFailed<Int>(IndexOfMissingException) }

private object IndexOfMissingException : RuntimeException("Unable to find substring")
//</editor-fold>

fun String.Companion.createFromChars(charArray: CharArray): String {
    return StringBuilder().appendContentOf(charArray).toString()
}


@Suppress("NOTHING_TO_INLINE")
inline fun String.findAllOf(subString: String,
                            searchByWord: Boolean,
                            ignoreCase: Boolean = false): Set<Int> {
    return forEachMatching(subString, searchByWord, ignoreCase) { start -> start }.toSet()
}

/**
 *
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
 */
fun String.replaceIf(condition: Boolean,
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
 */
fun String.replaceIfOr(condition: Boolean,
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
 */
fun String.replaceIfOr(condition: Boolean,
                       toReplace: String,
                       ifTrueValue: EmptyFunctionResult<String>,
                       ifFalseValue: EmptyFunctionResult<String>,
                       ignoreCase: Boolean = false): String {
    val replacement = condition.mapInvoke(ifTrueValue, ifFalseValue)
    return this.replace(toReplace, replacement, ignoreCase)
}


