@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.extensions.collections.collection

import csense.kotlin.extensions.collections.array.generic.*

/**
 * Tells whenever this [Collection] only has a [single] element
 * @receiver Collection<*>
 * @return [Boolean]
 */
public inline fun Collection<*>.isSingle(): Boolean =
    size == 1

public inline fun Collection<*>.isSingleOrEmpty(): Boolean =
    isSingle() || isEmpty()

