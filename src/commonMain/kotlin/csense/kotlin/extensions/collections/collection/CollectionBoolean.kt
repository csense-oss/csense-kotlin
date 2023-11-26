@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.extensions.collections.collection

import csense.kotlin.extensions.primitives.boolean.*

/**
 * Tells if all booleans in the given collection are true.
 * @receiver [Collection]<[Boolean]>
 * @return [Boolean] true if all booleans are true, false otherwise. for empty its "true"
 */
public inline fun Collection<Boolean>.isAllTrue(): Boolean = all { it: Boolean ->
    it.isTrue()
}

/**
 * Tells if all booleans in the given collection are false.
 * @receiver [Collection]<[Boolean]>
 * @return [Boolean] true if all booleans are false, true otherwise. for empty its "true"
 */
public inline fun Collection<Boolean>.isAllFalse(): Boolean = all { it: Boolean ->
    it.isFalse()
}