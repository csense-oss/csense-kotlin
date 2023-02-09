@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.extensions.stringBuilder


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
