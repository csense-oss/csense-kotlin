@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.extensions

import csense.kotlin.extensions.collections.array.*

/**
 * Clears the content and sets the content to the given string
 * @receiver [StringBuilder]
 * @param content [String]
 * @return [StringBuilder]
 */
public inline fun StringBuilder.set(content: String): StringBuilder = apply {
    clear()
    append(content)
}

/**
 * Clears the content and sets the content to the given char array
 * @receiver [StringBuilder]
 * @param charArray [CharArray]
 * @return [StringBuilder]
 */
public inline fun StringBuilder.set(charArray: CharArray): StringBuilder = apply {
    clear()
    appendContentOf(charArray)
}


/**
 * Appends the content of the given char array to this string builder
 * @receiver [StringBuilder]
 * @param charArray [CharArray]
 * @return [StringBuilder]
 */
public inline fun StringBuilder.appendContentOf(charArray: CharArray): StringBuilder = apply {
    charArray.forEachDiscard(this::append)
}
