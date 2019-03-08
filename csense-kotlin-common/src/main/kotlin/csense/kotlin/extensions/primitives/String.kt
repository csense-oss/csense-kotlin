@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.primitives

import csense.kotlin.*
import csense.kotlin.extensions.*
import csense.kotlin.extensions.collections.generic.*
import csense.kotlin.patterns.*

//TODO unit tests.

//region File related string apis
/**
 *
 * @receiver String
 * @return String?
 */

inline fun String.fileExtension(): String? {
    val fileExtension = substringAfterLast(".", "")
    return fileExtension.isEmpty().map(null, fileExtension) //map empty to null
}


/**
 * Tries to remove any kind of file extensions.
 * @receiver String
 */
inline fun String.removeFileExtension(): String = substringBeforeLast(".")
//endregion

//region IndexOf safe
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

//TODO this seems rather bad, as we are to allocate a random stacktrace..
private val failedIndexOfExpected = ExpectedFailed<Int>(IndexOfMissingException)

/**
 *
 */
object IndexOfMissingException : Exception("Unable to find substring")
//endregion

//region Creation and insertion
/**
 *
 * @receiver String.Companion
 * @param charArray CharArray
 * @return String
 */
inline fun String.Companion.createFromChars(charArray: CharArray): String =
        StringBuilder().appendContentOf(charArray).toString()


/**
 * A class representing an insert to be made into a string
 * @property toInsert String the string to insert
 * @property atIndex Int at what raw index in the string
 */
data class StringInserts(val toInsert: String, val atIndex: Int)

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
        sb.append(this.subSequence(currentLastIndex, currentToIndex),
                it.toInsert)
    }
    if (currentToIndex < size) {
        sb.append(this.substring(currentToIndex))
    }
    return sb.toString()
}
//endregion

//region Searching / find
/**
 *
 * @receiver String
 * @param subString String
 * @param searchByWord Boolean
 * @param ignoreCase Boolean
 * @return Set<Int>
 */

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
//endregion

//region Replacing
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
//endregion

//region contains / startwith queries
/**
 * Returns whenever this string ends with at least one of the given collection
 * @receiver C
 * @param items Collection<C>
 * @param ignoreCase Boolean
 * @return Boolean
 */
inline fun String.endsWithAny(
        items: Collection<String>,
        ignoreCase: Boolean = false): Boolean =
        items.any { this.endsWith(it, ignoreCase) }

/**
 * Returns whenever this string ends with at least one of the given collection
 * @receiver C
 * @param items Array<out C>
 * @param ignoreCase Boolean
 * @return Boolean
 */
inline fun String.endsWithAny(
        vararg items: String,
        ignoreCase: Boolean = false): Boolean =
        items.any { this.endsWith(it, ignoreCase) }

/**
 * Returns whenever this string starts with at least one of the given collection
 * @receiver C
 * @param items Array<out C>
 * @param ignoreCase Boolean
 * @return Boolean
 */
inline fun String.startsWithAny(
        vararg items: String,
        ignoreCase: Boolean = false): Boolean =
        items.any { this.startsWith(it, ignoreCase) }

/**
 * Returns whenever this string starts with at least one of the given collection
 * @receiver C
 * @param items Collection<C>
 * @param ignoreCase Boolean
 * @return Boolean
 */
inline fun String.startsWithAny(
        items: Collection<String>,
        ignoreCase: Boolean = false): Boolean =
        items.any { this.startsWith(it, ignoreCase) }
//endregion

//region Modification / computing
/**
 * Limits this string to the given number of characters
 * @receiver String the string to potentially limit
 * @param maxLength Int the max length
 * @return String a string at max the given max length;
 * if maxLength is zero or negative, and empty string is returned
 */
inline fun String.limitTo(maxLength: Int): String {
    if (maxLength.isNegativeOrZero) {
        return ""
    }
    return substring(0, minOf(length, maxLength))
}


/**
 * Wraps this string with the given prefix and postfix strings.
 * @receiver String
 * @param prefix String
 * @param postFix String
 * @return String the combined result (prefix + this + postfix)
 */
inline fun String.wrapIn(prefix: String, postFix: String): String =
        prefix + this + postFix

/**
 * As the name implies wraps the given receiver into a pair of quoutes at each end
 * @receiver String
 * @return String the wrapped string.
 */
inline fun String.wrapInQuotes(): String = wrapIn("\"", "\"")

//endregion

//region Action on state
/**
 * Opposite of "ifEmpty"
 * if this string is not empty , executes the method and returns that
 * if it is empty, then it returns that.
 * @receiver C
 * @param action Function1<C, C>
 * @return C
 */
inline fun String.ifNotEmpty(action: Function1<String, String>): String = if (isEmpty()) {
    this
} else {
    action(this)
}

/**
 * Opposite of "ifBlank"
 * if this string is not blank, executes the method and returns that
 * if it is blank, then it returns that.
 * @receiver C
 * @param action Function1<C, C>
 * @return C
 */
inline fun String.ifNotBlank(action: Function1<String, String>): String = if (isBlank()) {
    this
} else {
    action(this)
}
//endregion

//region Skipping functions

/**
 * Skips the given part if it starts with it.
 * @receiver String
 * @param prefix String the prefix we are looking for (and the part that will be skipped iff there.
 * @param ignoreCase Boolean how we should compare prefix with this string
 * @return String the resulting string, either the original or substring by the prefix length
 */
inline fun String.skipStartsWith(prefix: String, ignoreCase: Boolean = false): String {
    val startsWith = startsWith(prefix, ignoreCase)
    return startsWith.mapLazy(
            ifTrue = { substring(prefix.length) },
            ifFalse = { this })
}
//endregion

//region foreach generic(s)
/**
 * Iterates over 2 characters at ones; only get executed iff the length is a factor of 2
 * @receiver String
 * @param action (first: Char, second: Char) -> Unit
 */
inline fun String.foreach2(action: Function2Unit<Char, Char>) =
        GenericCollectionExtensions.forEach2(length, this::get, action)

/**
 * Iterates over 2 characters at ones with the index(first) as well; only get executed iff the length is a factor of 2
 * @receiver String
 * @param action (first: Char, second: Char) -> Unit
 */
inline fun String.foreach2Indexed(action: Function2IndexedUnit<Char, Char>) =
        GenericCollectionExtensions.forEach2Indexed(length, this::get, action)
//endregion

//region Hex converting
/**
 * The opposite of ByteArray.toHexString , so takes a hex string (eg "0x20") and converts it to a byte array of that
 * if any error is found during the "deserialization, null will be returned.
 * @receiver String
 * @return ShortArray? since UBytes are still experimental, shorts are used to make sure the size "is ok"
 */
inline fun String.fromHexStringToByteArray(): ShortArray? {
    //strip prefix iff asked to
    if (length.isOdd || isEmpty()) {
        return null
    }
    //we have the hex prefix iff it starts with "0x". strip that iff necessary
    val string = skipStartsWith("0x", true)
    val result = ShortArray(string.length / 2)
    string.foreach2Indexed { index: Int, first: Char, second: Char ->
        val shortValue = hexCharsToValue(first, second) ?: return@fromHexStringToByteArray null
        result[index / 2] = shortValue
    }
    return result
}
//endregion