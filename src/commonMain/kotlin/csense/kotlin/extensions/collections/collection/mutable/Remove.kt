@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")


package csense.kotlin.extensions.collections.collection.mutable

import csense.kotlin.annotations.sideEffect.*
import csense.kotlin.extensions.mapping.*
import kotlin.contracts.*


@DiscardableResult
public inline fun <@kotlin.internal.OnlyInputTypes E> MutableCollection<E>.removeIf(
    condition: Boolean,
    item: E
): Boolean = condition.mapLazy(
    ifTrue = { remove(item) },
    ifFalse = { false }
)


@DiscardableResult
public inline fun <@kotlin.internal.OnlyInputTypes E> MutableCollection<E>.removeIfNotNull(
    item: E?
): Boolean {
    contract {
        returns(true) implies (item != null)
    }
    return item?.let(::remove) ?: false
}
