@file:Suppress("NOTHING_TO_INLINE")
package csense.kotlin.extensions.primitives.string

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
