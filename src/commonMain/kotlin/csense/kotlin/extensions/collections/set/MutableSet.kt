@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package csense.kotlin.extensions.collections.set

import csense.kotlin.*
import csense.kotlin.extensions.primitives.*
import kotlin.contracts.*

/**
 * Toggles whenever a set contains the given item;
 * if the set contains the item it will be removed.
 * if it does not contain the item, the item will be inserted.
 * @receiver [MutableSet]<T>
 * @param item T
 * @timecomplexity O(1)
 */

public inline fun <@kotlin.internal.OnlyInputTypes T> MutableSet<T>.toggleExistence(item: T) {
    setExistence(item, !contains(item))
}

/**
 * like [toggleExistence], except you control the action by the "[shouldExists]";
 * if that is true, then the element is added, if false the element is removed.
 * @receiver [MutableSet]<T>
 * @param item T
 * @param shouldExists [Boolean]
 * @timecomplexity O(1)
 */
public inline fun <@kotlin.internal.OnlyInputTypes T> MutableSet<T>.setExistence(item: T, shouldExists: Boolean) {
    if (shouldExists) {
        add(item)
    } else {
        remove(item)
    }
}

/**
 * Adds the given [item] to this set if this set does not contain it already.
 * After adding (successfully) invokes the given [action]
 * @receiver [MutableSet]<T>
 * @param item T the item to add (if not contained already)
 * @param action [Function0]<[Unit]> the action to invoke if [item] was not present and could be added
 * @return [Boolean] true iff added, false if not added (either contained already or could not add)
 * @timecomplexity O(1)
 */
@OptIn(ExperimentalContracts::class)
public inline fun <@kotlin.internal.OnlyInputTypes T> MutableSet<T>.addIfMissingAnd(
    item: T,
    action: Function0<T>
): Boolean {
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }
    return doesNotContain(item) && add(item).ifTrue { action(item) }
}
