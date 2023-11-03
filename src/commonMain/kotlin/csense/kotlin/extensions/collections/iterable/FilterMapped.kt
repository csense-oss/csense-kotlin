package csense.kotlin.extensions.collections.iterable

import csense.kotlin.extensions.collections.collection.mutable.*

public inline fun <T, R> Iterable<T>.filterMapped(
    predicate: (R) -> Boolean,
    transform: (T) -> R
): List<R> = buildList {
    forEach { it: T ->
        val transformed: R = transform(it)
        addIf(
            condition = predicate(transformed),
            item = transformed
        )
    }
}
