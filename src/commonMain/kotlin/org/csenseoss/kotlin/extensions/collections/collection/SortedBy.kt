@file:Suppress("NOTHING_TO_INLINE")

package org.csenseoss.kotlin.extensions.collections.collection

public inline fun Collection<Boolean>.sortedByFalseFirst(): Collection<Boolean> {
    return this.sorted()
}

public inline fun Collection<Boolean>.sortedByTrueFirst(): Collection<Boolean> {
    return this.sortedDescending()
}