@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.extensions.collections.collection

/**
 * Tells whenever this [Collection] only has a [single] element
 * @receiver Collection<*>
 * @return [Boolean]
 */
public inline fun Collection<*>.isSingle(): Boolean =
    size == 1

public inline fun Collection<*>.isSingleOrEmpty(): Boolean =
    isSingle() || isEmpty()

