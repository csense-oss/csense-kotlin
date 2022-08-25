package csense.kotlin.specificExtensions.list

import csense.kotlin.extensions.*
import kotlin.jvm.*


public val <T> List<T>.modification: ListModifications<T>
    get() = ListModifications(this)


@JvmInline
public value class ListModifications<T>(public val list: List<T>)


/**
 * Replaces all the found items via the [predicate] with the given [replaceWith] function
 * @param predicate Function1<T, Boolean>
 * @param replaceWith Function1<T, T>
 * @return List<T> the list with all items matching the [predicate] replaced via [replaceWith] or the original list if non-matched the [predicate]
 * optimized such that if no replacements are made, it will return the original list without copying it.
 */
public fun <T> ListModifications<T>.replaceAllWith(
    predicate: (item: T) -> Boolean,
    replaceWith: (item: T) -> T
): List<T> {

    var replaced: MutableMap<Int, T>? = null

    list.forEachIndexed { index, item ->
        if (predicate(item)) {
            replaced = (replaced ?: mutableMapOf()).apply {
                val replacedWith = replaceWith(item)
                put(index, replacedWith)
            }
        }
    }

    if (replaced.isNull()) {
        return list
    }
    return list.mapIndexed { index, item ->
        replaced?.get(index) ?: item
    }
}

