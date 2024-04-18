@file:Suppress("NOTHING_TO_INLINE")

package org.csenseoss.kotlin.extensions.collections.collection

public inline fun <T> Collection<T?>.forEachNotNull(
    action: (T) -> Unit
) {
    forEach { it: T? ->
        if (it != null) {
            action(it)
        }
    }
}