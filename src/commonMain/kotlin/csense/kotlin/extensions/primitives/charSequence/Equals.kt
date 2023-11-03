@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package csense.kotlin.extensions.primitives.charSequence

import csense.kotlin.extensions.primitives.char.*


@kotlin.internal.LowPriorityInOverloadResolution
public inline fun CharSequence.equals(other: CharSequence, ignoreCase: Boolean = false): Boolean {
    if (length != other.length) {
        return false
    }
    forEachIndexed { index: Int, char: Char ->
        if (other[index].isNotEqual(other = char, ignoreCase = ignoreCase)) {
            return@equals false
        }
    }
    return true

}

@kotlin.internal.LowPriorityInOverloadResolution
public inline fun CharSequence.isNotEqual(
    other: CharSequence,
    ignoreCase: Boolean = false
): Boolean = !equals(other = other, ignoreCase = ignoreCase)
