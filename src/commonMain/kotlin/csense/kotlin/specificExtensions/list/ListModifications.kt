package csense.kotlin.specificExtensions.list

import csense.kotlin.extensions.collections.*
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
    predicate: Predicate<T>,
    replaceWith: (item: T) -> T
): List<T> {

    val replaced: MutableMap<Int, T> by lazy(mode = LazyThreadSafetyMode.NONE) {
        mutableMapOf()
    }

    list.forEachIndexed { index: Int, item: T ->
        if (predicate(item)) {
            replaced[index] = replaceWith(item)
        }
    }

    return list.mapIndexed { index: Int, item: T ->
        replaced[index] ?: item
    }
}
