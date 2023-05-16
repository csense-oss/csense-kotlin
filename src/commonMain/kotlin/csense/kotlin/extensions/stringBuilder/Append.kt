@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.extensions.stringBuilder

import csense.kotlin.extensions.collections.array.typed.char.*


/**
 * Appends the content of the given char array to this string builder
 * @receiver [StringBuilder]
 * @param charArray [CharArray]
 * @return [StringBuilder]
 */
public inline fun StringBuilder.appendContentOf(charArray: CharArray): StringBuilder = apply {
    charArray.forEachDiscard(this::append)
}


public inline fun StringBuilder.appendLineIfNotEmpty(value: String): StringBuilder = apply {
    if (value.isNotEmpty()) {
        appendLine(value)
    }
}

public inline fun StringBuilder.appendLineIfNotBlank(value: String): StringBuilder = apply {
    if (value.isNotBlank()) {
        appendLine(value)
    }
}