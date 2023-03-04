package csense.kotlin.extensions.collections.iterable

import csense.kotlin.classes.general.*
import csense.kotlin.extensions.collections.*


public inline fun <Item> Iterable<Item>.countWith(
    predicateWithCount: PredicateCount<Item>
): Int {
    val counter = IncrementalCounter()
    forEach { item: Item ->
        val shouldCount: Boolean = predicateWithCount(
            /*count=*/ counter.value,
            /*item=*/ item
        )
        counter.incrementIf(shouldCount)
    }
    return counter.value
}
