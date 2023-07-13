package csense.kotlin.extensions.collections.iterable

import csense.kotlin.extensions.collections.collection.mutable.*
import csense.kotlin.general.createBy.*

public inline fun <T, R> Iterable<T>.filterMapped(
    predicate: (R) -> Boolean,
    transform: (T) -> R
): List<R> = createListBy { result: MutableList<R> ->
    forEach { it: T ->
        val transformed: R = transform(it)
        result.addIf(
            condition = predicate(transformed),
            item = transformed
        )
    }
}
