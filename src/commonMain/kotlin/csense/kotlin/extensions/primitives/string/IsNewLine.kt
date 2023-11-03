@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.extensions.primitives.string


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