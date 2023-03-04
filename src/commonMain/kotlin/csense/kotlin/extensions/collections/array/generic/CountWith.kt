@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package csense.kotlin.extensions.collections.array.generic

import csense.kotlin.classes.general.*
import csense.kotlin.extensions.collections.*


/**
 * Counts based on the given predicate (which also receives the current count)
 * @receiver [Array]<Item>
 * @param predicateWithCount [PredicateCount]<Item>
 * @return [Int] number of items matching the predicate
 */
public inline fun <Item> Array<Item>.countWith(
    predicateWithCount: PredicateCount<Item>
): Int {
    val counter = IncrementalCounter()
    forEach { it: Item ->
        val shouldCount: Boolean = predicateWithCount(
            /* count = */ counter.value,
            /* item = */ it
        )
        counter.incrementIf(shouldCount)
    }
    return counter.value
}
