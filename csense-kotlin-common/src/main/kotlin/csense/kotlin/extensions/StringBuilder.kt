package csense.kotlin.extensions

import csense.kotlin.extensions.collections.array.*


/**
 * Clears this stringbuilder.
 * @receiver StringBuilder
 * @return StringBuilder
 */
@Suppress("NOTHING_TO_INLINE")
inline fun StringBuilder.clear(): StringBuilder = apply {
    this.removeRange(0, length - 1)
}

/**
 * Clears the content and sets the content to the given string
 * @receiver StringBuilder
 * @param content String
 * @return StringBuilder
 */
@Suppress("NOTHING_TO_INLINE")
inline fun StringBuilder.set(content: String): StringBuilder = apply {
    clear()
    append(content)
}

/**
 * Clears the content and sets the content to the given char array
 * @receiver StringBuilder
 * @param content CharArray
 * @return StringBuilder
 */
@Suppress("NOTHING_TO_INLINE")
inline fun StringBuilder.set(content: CharArray): StringBuilder = apply {
    clear()
    appendContentOf(content)
}


/**
 *appends the content of the given char array to this string builder
 * @receiver StringBuilder
 * @param charArray CharArray
 * @return StringBuilder
 */
@Suppress("NOTHING_TO_INLINE")
inline fun StringBuilder.appendContentOf(charArray: CharArray): StringBuilder = apply {
    charArray.forEachDiscard(this::append)
}
