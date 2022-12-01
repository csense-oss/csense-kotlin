@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")


package csense.kotlin.extensions.collections.collection

import csense.kotlin.annotations.sideEffect.*
import csense.kotlin.extensions.mapping.*
import kotlin.contracts.*


//region set & setAll

/**
 * Clears the collection and adds the given collection
 * @receiver [MutableCollection]<E>
 * @param collection [Collection]<E>
 */
@DiscardableResult
public inline fun <@kotlin.internal.OnlyInputTypes E> MutableCollection<E>.setAll(
    collection: Collection<E>
): Boolean {
    clear()
    return addAll(collection)
}

/**
 * Clears the collection and adds the given vararg of items.
 * @receiver [MutableCollection]<E>
 * @param items [Collection]<E>
 */
@DiscardableResult
public inline fun <@kotlin.internal.OnlyInputTypes E> MutableCollection<E>.setAll(
    vararg items: E
): Boolean {
    clear()
    return addAll(items)
}

/**
 * Clears the collection and adds the given element
 * @receiver [MutableCollection]<E>
 * @param item E
 */
@DiscardableResult
public inline fun <@kotlin.internal.OnlyInputTypes E> MutableCollection<E>.set(
    item: E
): Boolean {
    clear()
    return add(item)
}
//endregion


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
public inline fun <@kotlin.internal.OnlyInputTypes E> MutableCollection<E>.removeIf(
    condition: Boolean,
    item: E
): Boolean = condition.mapLazy(
    ifTrue = { remove(item) },
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


@DiscardableResult
public inline fun <@kotlin.internal.OnlyInputTypes E> MutableCollection<E>.removeIfNotNull(
    item: E?
): Boolean {
    contract {
        returns(true) implies (item != null)
    }
    return item?.let(::remove) ?: false
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