package csense.kotlin.extensions.collections.iterable

import csense.kotlin.classes.general.*
import csense.kotlin.extensions.collections.*
import csense.kotlin.extensions.collections.generic.*


public inline fun <Item> Iterable<Item>.countWith(
    predicateWithCount: PredicateCount<Item>
): Int {
    val counter = IncrementalCounter()
    forEach { item ->
        val shouldCount = predicateWithCount(
            /*count=*/ counter.value,
            /*item=*/ item
        )
        if (shouldCount) {
            counter.increment()
        }
    }
    return counter.value
}
