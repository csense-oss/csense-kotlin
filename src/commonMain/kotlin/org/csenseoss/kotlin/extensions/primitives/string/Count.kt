@file:Suppress("NOTHING_TO_INLINE")

package org.csenseoss.kotlin.extensions.primitives.string


public inline fun String.count(
    char: Char,
    ignoreCase: Boolean = false
): Int {
    return count { it: Char -> it.equals(char, ignoreCase = ignoreCase) }
}