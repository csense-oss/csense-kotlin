@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")


package csense.kotlin.extensions.primitives.string

import csense.kotlin.*
import csense.kotlin.annotations.numbers.*
import csense.kotlin.extensions.collections.generic.collection.*
import csense.kotlin.extensions.collections.generic.collection.operations.*
import csense.kotlin.extensions.general.*
import csense.kotlin.extensions.mapping.*
import csense.kotlin.extensions.primitives.char.*
import csense.kotlin.extensions.primitives.charSequence.*
import csense.kotlin.specificExtensions.string.*
import kotlin.contracts.*
import kotlin.text.isDigit

//region Searching / find
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
    return modifications.mapEachMatching(subString, searchByWord, ignoreCase) { start -> start }.toSet()
}


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
 * @timecomplexity o(n*m) where n is this strings length and m is the size of all any strings
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
 * @param ifNotEmpty [Function1]<C, C>
 * @return C
 */
public inline fun String.ifNotEmpty(ifNotEmpty: Function1<String, String>): String {
    contract {
        callsInPlace(ifNotEmpty, InvocationKind.AT_MOST_ONCE)
    }
    return if (isEmpty()) {
        this
    } else {
        ifNotEmpty(this)
    }
}

/**
 * Opposite of "[ifBlank]"
 * if this string is not blank, executes the method and returns that
 * if it is blank, then it returns this.
 * @receiver C
 * @param ifNotBlank [Function1]<C, C>
 * @return C
 */
public inline fun String.ifNotBlank(ifNotBlank: Function1<String, String>): String {
    contract {
        callsInPlace(ifNotBlank, InvocationKind.AT_MOST_ONCE)
    }
    return if (isBlank()) {
        this
    } else {
        ifNotBlank(this)
    }
}
//endregion

//region Skipping functions

/**
 * Skips the given part if it starts with it.
 * @receiver [String]
 * @param prefix [String] the prefix we are looking for (and the part that will be skipped iff there.)
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
 * Tells if this string starts with something different from the given or not
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
 * Tells if this string starts with something different from the given or not
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
 * Tells if this string ends with something different from the given [sequence] or not
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
    ignoreNoneLetters: Boolean = false
): Boolean = when {
    isEmpty() -> false
    ignoreNoneLetters -> none(Char::isLowerCaseLetter)
    else -> all(Char::isUpperCaseLetter)
}


/**
 * Tells if this [String] solely consists of lowercase characters
 * @receiver [String]
 * @return [Boolean] true if either the string is empty or all chars are lowercase
 * @timecomplexity O(n)
 */
public inline fun String.isOnlyLowerCaseLetters(
    ignoreNoneLetters: Boolean = false
): Boolean = when {
    isEmpty() -> false
    ignoreNoneLetters -> none(Char::isUpperCaseLetter)
    else -> all(Char::isLowerCaseLetter)
}

/**
 *
 * @receiver String
 * @return Boolean
 */
public inline fun String.isOnlyDigits(): Boolean = when {
    isEmpty() -> false
    else -> all(Char::isDigit)
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
    GenericCollections.forEachBackwards(count(), this::elementAt, action)

/**
 *
 * @receiver [String]
 * @param action [Function2]<[Int], [Char]>
 * @timecomplexity O(n)
 */
public inline fun String.forEachBackwardsIndexed(
    action: Function2Unit<@IntLimit(from = 0) Int, Char>
): Unit =
    GenericCollections.forEachBackwardsIndexed(count(), this::elementAt, action)

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
 * Returns null if this string [isEmpty]
 * @return [String] null if this string [isEmpty]. Otherwise, the string
 */
public inline fun String.nullOnEmpty(): String? = ifEmpty {
    null
}

/**
 * Returns null if this string [isBlank]
 * @return [String] null if this string [isBlank]. Otherwise, the string
 */
public inline fun String.nullOnBlank(): String? = ifBlank {
    null
}

/**
 * Title-cases this string's first word if it is not already title cased.
 * @return this string if empty or if the first char is title-cased.
 * otherwise, title-cases the first char
 */
public inline fun String.titleCaseFirstWord(): String = caseFirstWord(shouldBeTitleCase = true)

/**
 * lower-cases this string's first word if it is not already lower cased.
 * @return this string if empty or if the first char is lowercase.
 * otherwise, lowercase the first char
 */
public inline fun String.lowerCaseFirstWord(): String = caseFirstWord(shouldBeTitleCase = false)

/**
 * apply either a Title case or a lowercase to this string's first word
 * @param shouldBeTitleCase [Boolean] true means to title case, false means to lowercase
 * @return this string if empty or if already the given casing;  otherwise applies the given casing.
 */
public inline fun String.caseFirstWord(shouldBeTitleCase: Boolean): String {
    val firstCharIndex = indexOfFirstOrNull { it.isNotWhitespace() } ?: return this
    val firstChar = this[firstCharIndex]
    if (firstChar.isTitleCase()) {
        return this
    }
    val withChar = shouldBeTitleCase.mapLazy(
        ifTrue = firstChar::titlecaseChar,
        ifFalse = firstChar::lowercaseChar
    )
    return modifications.replaceCharAt(index = firstCharIndex, withChar = withChar)
}


@kotlin.internal.LowPriorityInOverloadResolution
public inline fun String.startsWith(
    prefix: String,
    ignoreCase: Boolean = false,
    ignoreWhitespace: Boolean = false
): Boolean {
    if (this === prefix) {
        return true
    }

    return if (!ignoreWhitespace) {
        startsWith(prefix = prefix, ignoreCase = ignoreCase)
    } else {
        val firstNonWhitespaceIndex = indexOfFirstOrNull { it.isNotWhitespace() } ?: return false
        return comparison.containsStringAt(
            startIndex = firstNonWhitespaceIndex,
            other = prefix,
            ignoreCase = ignoreCase
        )
    }
}

@kotlin.internal.LowPriorityInOverloadResolution
public inline fun String.endsWith(
    suffix: String,
    ignoreCase: Boolean = false,
    ignoreWhitespace: Boolean = false
): Boolean {
    if (this === suffix) {
        return true
    }
    return if (!ignoreWhitespace) {
        endsWith(suffix = suffix, ignoreCase = ignoreCase)
    } else {
        val lastNonWhitespaceIndexInclusive = indexOfLastOrNull { it.isNotWhitespace() } ?: return false
        return comparison.containsStringEndingAt(
            endIndex = lastNonWhitespaceIndexInclusive,
            other = suffix,
            ignoreCase = ignoreCase
        )
    }
}

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


