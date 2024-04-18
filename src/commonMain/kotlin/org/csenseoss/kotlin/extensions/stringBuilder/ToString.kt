@file:Suppress("NOTHING_TO_INLINE")

package org.csenseoss.kotlin.extensions.stringBuilder

/**
 * gets the current content ([toString]) of this [StringBuilder] and [clear]s it
 */
public inline fun StringBuilder.toStringAndClear(): String = toString().also {
    clear()
}