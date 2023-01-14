@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package csense.kotlin.extensions.collections.collection.mutable

import csense.kotlin.annotations.sideEffect.*
import csense.kotlin.extensions.mapping.*
import kotlin.contracts.*




/**
 * Missing from Standard library, a vararg edition of add all
 * @receiver [MutableCollection]<E>
 * @param items [Array]<out E>
 */
@DiscardableResult
public inline fun <@kotlin.internal.OnlyInputTypes E> MutableCollection<E>.addAll(
    vararg items: E
): Boolean =
    addAll(items)


@DiscardableResult
public inline fun <@kotlin.internal.OnlyInputTypes E> MutableCollection<E>.addIf(
    condition: Boolean,
    item: E
): Boolean = condition.mapLazy(
    ifTrue = { add(item) },
    ifFalse = { false }
)

@DiscardableResult
public inline fun <@kotlin.internal.OnlyInputTypes E> MutableCollection<E>.addIf(
    condition: Boolean,
    itemAction: () -> E
): Boolean = condition.mapLazy(
    ifTrue = { add(itemAction()) },
    ifFalse = { false }
)



@DiscardableResult
public inline fun <@kotlin.internal.OnlyInputTypes E> MutableCollection<E>.addIfNotNull(
    item: E?
): Boolean {
    contract {
        returns(true) implies (item != null)
    }
    return item?.let(::add) ?: false
}


/**
 * Adds all the given [items] (if not null) to this [MutableCollection]
 * @receiver [MutableCollection]<E> the collection to append the given [items] to
 * @param items [Iterable]<E>? the items to add. if null then nothing happens
 * @return [Boolean] true if all got added. false otherwise
 */
@DiscardableResult
public inline fun <@kotlin.internal.OnlyInputTypes E> MutableCollection<E>.addAll(
    items: Iterable<E>?
): Boolean {
    var result = true
    items?.forEach {
        result = result && add(it)
    }
    return result
}