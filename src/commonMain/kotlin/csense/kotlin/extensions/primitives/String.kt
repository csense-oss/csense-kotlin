@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.primitives

import csense.kotlin.*
import csense.kotlin.annotations.numbers.*
import csense.kotlin.extensions.*
import csense.kotlin.extensions.collections.generic.*

//region Searching / find
/**
 * Finds all indices of the given substring
 * @receiver [String] The string we are searching in
 * @param subString [String] the substring we are searching for
 * @param searchByWord [Boolean]
 * @param ignoreCase [Boolean] if we should ignore casing
 * @return [Set]<[Int]> a set of indices
 */
public inline fun String.findAllOf(
    subString: String,
    searchByWord: Boolean,
    ignoreCase: Boolean = false
): Set<@IntLimit(from = 0) Int> {
    return mapEachMatching(subString, searchByWord, ignoreCase) { start -> start }.toSet()
}
//TODO consider moving..
/**
 * Maps each matching occurrence of a substring
 * @receiver [String] the string to search in
 * @param subString [String] the string we are searching for
 * @param searchByWord [Boolean]
 * @param ignoreCase [Boolean] whenever we should ignore casing
 * @param mapper [Function1]<[Int], U> Maps the given index to a given value
 * @return [List]<U> the resulting list by mapping all the found occurrences of [subString]
 */
public inline fun <U> String.mapEachMatching(
    subString: String,
    searchByWord: Boolean,
    ignoreCase: Boolean = false,
    mapper: (index: Int) -> U
): List<U> {
    if (subString.isEmpty() || this.isEmpty()) {
        return emptyList()
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
    return result
}
//endregion

//region Replacing
/**
 * Replaces a value given a criteria. if the condition is true, the replace is called with the value
 * otherwise this string is returned as is.
 * @receiver [String]
 * @param condition [Boolean]
 * @param toReplace [String]
 * @param newValue [String]
 * @param ignoreCase [Boolean]
 * @return [String]
 */
public inline fun String.replaceIf(
    condition: Boolean,
    toReplace: String,
    newValue: String,
    ignoreCase: Boolean = false
): String {
    return if (condition) {
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
public inline fun String.replaceIfOr(
    condition: Boolean,
    toReplace: String,
    ifTrueValue: String,
    ifFalseValue: String,
    ignoreCase: Boolean = false
): String {
    val replacement = condition.map(ifTrueValue, ifFalseValue)
    return this.replace(toReplace, replacement, ignoreCase)
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
public inline fun String.replaceIfOr(
    condition: Boolean,
    toReplace: String,
    crossinline ifTrueValue: EmptyFunctionResult<String>,
    crossinline ifFalseValue: EmptyFunctionResult<String>,
    ignoreCase: Boolean = false
): String {
    val replacement = condition.mapLazy(ifTrueValue, ifFalseValue)
    return this.replace(toReplace, replacement, ignoreCase)
}
//endregion

//region contains / startWith queries
/**
 * Returns whenever this string ends with at least one of the given collection
 * @receiver C
 * @param collection [Collection]<C>
 * @param ignoreCase [Boolean]
 * @return [Boolean]
 */
public inline fun String.endsWithAny(
    collection: Collection<String>,
    ignoreCase: Boolean = false
): Boolean =
    collection.any { this.endsWith(it, ignoreCase) }

/**
 * Returns whenever this string ends with at least one of the given Array
 * @receiver [String] the string to ask if it ends with any of [strings]
 * @param strings [Array]<out C> the strings we want to tell if this ends with
 * @param ignoreCase [Boolean]
 * @return [Boolean]
 * @timecomplexity o(n*m) where n is this strings length and m is the size of all the any strings
 */
public inline fun String.endsWithAny(
    vararg strings: String,
    ignoreCase: Boolean = false
): Boolean =
    strings.any { this.endsWith(it, ignoreCase) }

/**
 * Returns whenever this string starts with at least one of the given collection
 * @receiver C
 * @param strings [Array]<out C>
 * @param ignoreCase [Boolean]
 * @return [Boolean]
 */
public inline fun String.startsWithAny(
    vararg strings: String,
    ignoreCase: Boolean = false
): Boolean =
    strings.any { this.startsWith(it, ignoreCase) }

/**
 * Returns whenever this string starts with at least one of the given collection
 * @receiver C
 * @param collection [Collection]<C>
 * @param ignoreCase [Boolean]
 * @return [Boolean]
 */
public inline fun String.startsWithAny(
    collection: Collection<String>,
    ignoreCase: Boolean = false
): Boolean =
    collection.any { this.startsWith(it, ignoreCase) }
//endregion


//region Action on state
/**
 * Opposite of "[ifEmpty]"
 * if this [String] is not empty , executes the method and returns that
 * if it is empty, then it returns "this".
 * @receiver C
 * @param action [Function1]<C, C>
 * @return C
 */
public inline fun String.ifNotEmpty(action: Function1<String, String>): String = if (isEmpty()) {
    this
} else {
    action(this)
}

/**
 * Opposite of "[ifBlank]"
 * if this string is not blank, executes the method and returns that
 * if it is blank, then it returns this.
 * @receiver C
 * @param action [Function1]<C, C>
 * @return C
 */
public inline fun String.ifNotBlank(action: Function1<String, String>): String = if (isBlank()) {
    this
} else {
    action(this)
}
//endregion

//region Skipping functions

/**
 * Skips the given part if it starts with it.
 * @receiver [String]
 * @param prefix [String] the prefix we are looking for (and the part that will be skipped iff there.
 * @param ignoreCase [Boolean] how we should compare prefix with this string
 * @return [String] the resulting string, either the original or substring by the prefix length
 */
public inline fun String.skipStartsWith(
    prefix: String,
    ignoreCase: Boolean = false
): String {
    val startsWith = startsWith(prefix, ignoreCase)
    return startsWith.mapLazy(
        ifTrue = { substring(prefix.length) },
        ifFalse = { this })
}
//endregion

//region foreach generic(s)
/**
 * Iterates over 2 characters at ones; only get executed iff the length is a factor of 2
 * @receiver [String]
 * @param action (first: [Char], second: [Char]) -> Unit
 */
@Deprecated("will be removed")
public inline fun String.foreach2(action: Function2Unit<Char, Char>): Unit =
    GenericCollectionExtensions.forEach2(length, this::get, action)

/**
 * Iterates over 2 characters at ones with the index(first) as well; only get executed iff the length is a factor of 2
 * @receiver [String]
 * @param action (first: [Char], second: [Char]) -> Unit
 */
@Deprecated("will be removed")
public inline fun String.foreach2Indexed(action: Function2IndexedUnit<Char, Char>): Unit =
    GenericCollectionExtensions.forEach2Indexed(length, this::get, action)
//endregion

//region Hex converting
/**
 * The opposite of [csense.kotlin.extensions.collections.array.toHexString] , so takes a hex string (eg "0x20") and converts it to a byte array of that
 * if any error is found during the "deserialization, null will be returned.
 * @receiver [String]
 * @return [ShortArray]? since [UByte]s are still experimental, [Short]s are used to make sure the size "is ok"
 */
public inline fun String.fromHexStringToByteArray(): ShortArray? {
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


//region Does not starts with


/**
 * Tells if this string does not start with the given [prefix]
 * @receiver [String] the string to test
 * @param prefix [CharSequence] the value we want to know if the receiver starts with or not
 * @param ignoreCase [Boolean] whenever we should ignore casing
 * @return [Boolean] true if this has a different start from [prefix] false if not
 */
public inline fun String.doesNotStartsWith(
    prefix: CharSequence,
    ignoreCase: Boolean = false
): Boolean =
    !startsWith(prefix, ignoreCase)

/**
 * Tells if this string does not start with the given [prefix]
 * @receiver [String] the string to test
 * @param prefix [Char] the [Char] we want to know if the receiver starts with or not
 * @param ignoreCase [Boolean] whenever we should ignore casing
 * @return [Boolean] true if this has a different start from [prefix] false if not
 */
public inline fun String.doesNotStartsWith(
    prefix: Char,
    ignoreCase: Boolean = false
): Boolean =
    !startsWith(prefix, ignoreCase)


/**
 * Tells if this string starts with something different than the given or not
 * @receiver [String] the string to test
 * @param subStrings [Array]<out [String]> the items to test if the receiver starts with or not
 * @param ignoreCase [Boolean] whenever we should ignore casing
 * @return [Boolean] true if this string starts with something different from any of the given [subStrings]
 */
public inline fun String.doesNotStartsWithAny(
    vararg subStrings: String,
    ignoreCase: Boolean = false
): Boolean =
    !startsWithAny(*subStrings, ignoreCase = ignoreCase)

/**
 * Tells if this string starts with something different than the given or not
 * @receiver [String] the string to test
 * @param items [Collection]<[String]> the items to test if the receiver starts with or not
 * @param ignoreCase [Boolean] whenever we should ignore casing
 * @return [Boolean] true if this string starts with something different from any of the given [items]
 */
public inline fun String.doesNotStartsWithAny(
    items: Collection<String>,
    ignoreCase: Boolean = false
): Boolean =
    !startsWithAny(items, ignoreCase)


//endregion


//region does not ends with
/**
 * Tells if this string ends with something different than the given [sequence] or not
 * @receiver [String]
 * @param sequence [String]
 * @param ignoreCase [Boolean]
 * @return [Boolean]
 */
public inline fun String.doesNotEndsWith(
    sequence: CharSequence,
    ignoreCase: Boolean = false
): Boolean =
    !endsWith(sequence, ignoreCase)

public inline fun String.doesNotEndsWith(
    char: Char,
    ignoreCase: Boolean = false
): Boolean =
    !endsWith(char, ignoreCase)

public inline fun String.doesNotEndsWithAny(
    vararg subStrings: String,
    ignoreCase: Boolean = false
): Boolean =
    !endsWithAny(*subStrings, ignoreCase = ignoreCase)

public inline fun String.doesNotEndsWithAny(
    items: Collection<String>,
    ignoreCase: Boolean = false
): Boolean =
    !endsWithAny(items, ignoreCase)
//endregion

//region Contains any editions
/**
 *
 * @receiver [String]
 * @param strings [Array]<out [String]>
 * @param ignoreCase [Boolean]  `true` to ignore character case when comparing strings. By default `false`.
 * @return [Boolean]
 * @timecomplexity O(n*m) where n is the length of this string, and m is the size of strings (times the average length)
 * This will be improved in later versions to use a better algorithm for larger data sets
 */
public inline fun String.containsAny(vararg strings: String, ignoreCase: Boolean = false): Boolean = strings.any {
    this.contains(it, ignoreCase)
}

/**
 *
 * @receiver [String]
 * @param collection [Iterable]<[String]>
 * @param ignoreCase [Boolean] `true` to ignore character case when comparing strings. By default `false`.
 * @return [Boolean]
 * @timecomplexity O(n*m) where n is the length of this string, and m is the size of strings (times the average length)
 * This will be improved in later versions to use a better algorithm for larger data sets
 */
public inline fun String.containsAny(
    collection: Iterable<String>,
    ignoreCase: Boolean = false
): Boolean = collection.any {
    this.contains(it, ignoreCase)
}
//endregion

/**
 * Tells if this [String] solely consists of uppercase characters
 * @receiver [String]
 * @return [Boolean] true if all chars are upper case (if empty, returns false)
 * @timecomplexity O(n)
 */
public inline fun String.isOnlyUpperCaseLetters(
    IgnoreNoneLetters: Boolean = false
): Boolean = when {
    isEmpty() -> false
    IgnoreNoneLetters -> none(Char::isLowerCaseLetter)
    else -> all(Char::isUpperCaseLetter)
}


/**
 * Tells if this [String] solely consists of lowercase characters
 * @receiver [String]
 * @return [Boolean] true if either the string is empty or all chars are lowercase
 * @timecomplexity O(n)
 */
public inline fun String.isOnlyLowerCaseLetters(
    IgnoreNoneLetters: Boolean = false
): Boolean = when {
    isEmpty() -> false
    IgnoreNoneLetters -> none(Char::isUpperCaseLetter)
    else -> all(Char::isLowerCaseLetter)
}

/**
 *
 * @receiver [String]
 * @param action [Function1]<[Char]>
 * @timecomplexity O(n)
 */
public inline fun String.forEachBackwards(
    action: FunctionUnit<Char>
): Unit =
    GenericCollectionExtensions.forEachBackwards(count(), this::elementAt, action)

/**
 *
 * @receiver [String]
 * @param action [Function2]<[Int], [Char]>
 * @timecomplexity O(n)
 */
public inline fun String.forEachBackwardsIndexed(
    action: Function2Unit<@IntLimit(from = 0) Int, Char>
): Unit =
    GenericCollectionExtensions.forEachBackwardsIndexed(count(), this::elementAt, action)

/**
 * Tells if this is a new line (either windows or unix)
 * @receiver [String] the string to test
 * @return [Boolean] true if this string is exactly a newline, false otherwise
 * see
 * https://en.wikipedia.org/wiki/Newline#Representation
 */
public inline fun String.isNewLine(): Boolean = when (this.length) {
    1 -> this == "\n" //unix
    2 -> this == "\r\n" // windows
    else -> false
}

/**
 * Returns null if this string is empty
 * @return [String] null if this string is empty. Otherwise, the string
 */
public inline fun String.nullOnEmpty(): String? = ifEmpty {
    null
}

/**
 * Title-cases this string's first word if it is not already title cased.
 * @return this string if empty, or the first char is title-cased.
 * otherwise, title-cases the first char and returns that string
 */
public inline fun String.titleCaseFirstWord(): String {
    ifEmpty { return@titleCaseFirstWord this }
    if (first().isTitleCase()) {
        return this
    }
    return replaceFirstChar(Char::titlecaseChar)
}