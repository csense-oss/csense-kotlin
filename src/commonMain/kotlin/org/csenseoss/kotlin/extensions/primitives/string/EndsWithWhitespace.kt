@file:Suppress("NOTHING_TO_INLINE")

package org.csenseoss.kotlin.extensions.primitives.string

public inline fun String.endsWithWhitespace(): Boolean {
    val last: Char = lastOrNull() ?: return false
    return last.isWhitespace()
}