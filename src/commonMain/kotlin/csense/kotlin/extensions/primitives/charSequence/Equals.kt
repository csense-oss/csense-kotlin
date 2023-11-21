@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.primitives.charSequence

public inline fun CharSequence.equals(
    other: CharSequence,
    ignoreCase: Boolean = false
): Boolean = contentEquals(other, ignoreCase = ignoreCase)

public inline fun CharSequence.isNotEqual(
    other: CharSequence,
    ignoreCase: Boolean = false
): Boolean = !equals(
    other = other,
    ignoreCase = ignoreCase
)
