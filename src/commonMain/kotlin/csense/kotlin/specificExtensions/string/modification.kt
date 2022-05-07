@file:Suppress("NOTHING_TO_INLINE")
@file:OptIn(ExperimentalContracts::class)

package csense.kotlin.specificExtensions.string

import csense.kotlin.*
import csense.kotlin.annotations.numbers.*
import csense.kotlin.extensions.*
import csense.kotlin.extensions.collections.generic.*
import csense.kotlin.extensions.primitives.*
import kotlin.contracts.*
import kotlin.jvm.*

@JvmInline
public value class StringModification(public val string: String) {
    public data class StringSplitAt(
        public val beforeIndex: String,
        public val afterIndex: String
    )
}

public inline val String.modifications: StringModification
    inline get() = StringModification(this)


/**
 * Limits this [String] to the given number of characters
 * @receiver [String] the [String] to limit
 * @param maxLength [Int] the max length
 * @return [String] a [String] at max the given length
 *
 * if [maxLength] [Int.isNegativeOrZero], an empty string is returned
 */
public inline fun StringModification.limitTo(@IntLimit(from = 0) maxLength: Int): String {
    if (maxLength.isNegativeOrZero) {
        return ""
    }
    return string.substring(0, minOf(string.length, maxLength))
}


/**
 * Wraps this string with the given prefix and postfix strings.
 * @receiver [String]
 * @param prefix [String]
 * @param postFix [String]
 * @return [String] the combined result (prefix + this + postfix)
 */
public inline fun StringModification.wrapIn(prefix: String, postFix: String): String =
    prefix + string + postFix

/**
 * As the name implies wraps the given receiver into a pair of quotes at each end
 * @receiver [String]
 * @return [String] the wrapped string.
 */
public inline fun StringModification.wrapInQuotes(): String = wrapIn("\"", "\"")

@Throws(IndexOutOfBoundsException::class)
public inline fun StringModification.replaceCharAt(index: Int, withChar: Char): String {
    return replaceCharAtOrNull(index = index, withChar = withChar)
        ?: throw IndexOutOfBoundsException("Index out of bounds. Index: $index, length: ${string.length}")
}


/**
 *
 * @receiver String
 * @param index Int
 * @param withChar Char
 * @return String?
 */
public inline fun StringModification.replaceCharAtOrNull(index: Int, withChar: Char): String? = with(string) {
    if (isIndex.outOfBounds(index, isEndOutOfBonds = true)) {
        return@with null
    }
    val split = splitAtOrNull(index) ?: return@with null
    return@with split.beforeIndex + withChar + split.afterIndex
}

public inline fun StringModification.splitAtOrNull(index: Int): StringModification.StringSplitAt? = with(string) {
    if (isIndex.outOfBounds(index, isEndOutOfBonds = true)) {
        return@with null
    }
    val before = substringOrNull(0, index) ?: ""
    val after = substringOrNull(index + 1, length) ?: ""
    return@with StringModification.StringSplitAt(before, after)
}


/**
 * Maps each matching occurrence of a substring
 * @receiver [String] the string to search in
 * @param subString [String] the string we are searching for
 * @param searchByWord [Boolean]
 * @param ignoreCase [Boolean] whenever we should ignore casing
 * @param mapper [Function1]<[Int], U> Maps the given index to a given value
 * @return [List]<U> the resulting list by mapping all the found occurrences of [subString]
 */
public fun <U> StringModification.mapEachMatching(
    subString: String,
    searchByWord: Boolean,
    ignoreCase: Boolean = false,
    mapper: (index: Int) -> U
): List<U> = with(string) {
    if (subString.isEmpty() || this.isEmpty()) {
        return@with emptyList()
    }
    var currentIndex = 0
    val appendLength = searchByWord.map(subString.length, 1)
    var next = this.indexOfOrNull(subString, currentIndex, ignoreCase)
    val result = mutableListOf<U>()
    while (next != null) {
        result.add(mapper(next))
        currentIndex = next + appendLength
        next = this.indexOfOrNull(subString, currentIndex, ignoreCase)
    }
    return@with result
}

/**
 * Replaces a value given a criteria. if the condition is true, [replace] is called with the value
 * otherwise this string is returned as is.
 * @receiver [String]
 * @param condition [Boolean]
 * @param toReplace [String]
 * @param newValue [String]
 * @param ignoreCase [Boolean]
 * @return [String]
 */
public inline fun StringModification.replaceIf(
    condition: Boolean,
    toReplace: String,
    newValue: String,
    ignoreCase: Boolean = false
): String = with(string) {
    return@with if (condition) {
        this.replace(toReplace, newValue, ignoreCase)
    } else {
        this
    }
}

/**
 * Replaces a value given a criteria. if the condition is true, the ifTrueValue is used for the replacement
 * if the condition is false, the ifFalseValue is used.
 * @receiver [String]
 * @param condition [Boolean]
 * @param toReplace [String]
 * @param ifTrueValue [String]
 * @param ifFalseValue [String]
 * @param ignoreCase [Boolean]
 * @return [String]
 */
public inline fun StringModification.replaceIfOr(
    condition: Boolean,
    toReplace: String,
    ifTrueValue: String,
    ifFalseValue: String,
    ignoreCase: Boolean = false
): String = with(string) {
    val replacement = condition.map(ifTrueValue, ifFalseValue)
    return@with this.replace(toReplace, replacement, ignoreCase)
}

/**
 * Replaces a value given a criteria. if the condition is true, the ifTrueValue is used for the replacement
 * if the condition is false, the ifFalseValue is used. lazily evaluates the values.
 * @receiver [String]
 * @param condition [Boolean]
 * @param toReplace [String]
 * @param ifTrueValue [EmptyFunctionResult]<[String]>
 * @param ifFalseValue [EmptyFunctionResult]<[String]>
 * @param ignoreCase [Boolean]
 * @return [String]
 */
public inline fun StringModification.replaceIfOr(
    condition: Boolean,
    toReplace: String,
    crossinline ifTrueValue: EmptyFunctionResult<String>,
    crossinline ifFalseValue: EmptyFunctionResult<String>,
    ignoreCase: Boolean = false
): String {
    contract {
        callsInPlace(ifTrueValue, InvocationKind.AT_MOST_ONCE)
        callsInPlace(ifFalseValue, InvocationKind.AT_MOST_ONCE)
    }
    return with(string) {
        val replacement = condition.mapLazy(ifTrueValue, ifFalseValue)
        return@with this.replace(toReplace, replacement, ignoreCase)
    }
}

/**
 * Replaces [searchingFor] with the value of [replaceWith] (only iff there are any [searchingFor])
 *
 * @receiver StringModification
 * @param searchingFor String
 * @param replaceWith Function0<String> will only be called once if there are [searchingFor] occurrences in [StringModification.string]
 * @param ignoreCase Boolean
 * @return String
 */
public fun StringModification.replaceLazy(
    searchingFor: String,
    replaceWith: () -> String,
    ignoreCase: Boolean = false
): String {
    contract {
        callsInPlace(replaceWith, InvocationKind.AT_MOST_ONCE)
    }
    return with(string) {

        if (searchingFor.isEmpty()) {
            return@with this
        }
        var index: Int? = indexOfOrNull(searchingFor, startIndex = 0, ignoreCase = ignoreCase) ?: return@with this
        val builder = StringBuilder()
        val replace = replaceWith()
        var fromIndex = 0
        while (index != null) {
            builder.append(substring(fromIndex, index))
            builder.append(replace)
            fromIndex = index + searchingFor.length
            index = indexOfOrNull(searchingFor, startIndex = index + 1, ignoreCase = ignoreCase)
        }
        builder.append(substring(fromIndex))
        return@with builder.toString()

    }
}