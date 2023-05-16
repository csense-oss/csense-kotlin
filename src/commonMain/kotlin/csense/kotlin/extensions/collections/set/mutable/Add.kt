@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")


package csense.kotlin.extensions.collections.set.mutable

import csense.kotlin.*
import csense.kotlin.extensions.collections.set.*
import csense.kotlin.extensions.primitives.boolean.*
import kotlin.contracts.*


/**
 * Adds the given [item] to this set if this set does not contain it already.
 * After adding (successfully) invokes the given [action]
 * @receiver [MutableSet]<T>
 * @param item T the item to add (if not contained already)
 * @param action [Function0]<[Unit]> the action to invoke if [item] was not present and could be added
 * @return [Boolean] true iff added, false if not added (either contained already or could not add)
 * @timecomplexity O(1)
 */
public inline fun <@kotlin.internal.OnlyInputTypes T> MutableSet<T>.addIfMissingAnd(
    item: T,
    action: Function0<T>
): Boolean {
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }
    return doesNotContain(item) && add(item).also { it.ifTrue { action(item) } }
}
