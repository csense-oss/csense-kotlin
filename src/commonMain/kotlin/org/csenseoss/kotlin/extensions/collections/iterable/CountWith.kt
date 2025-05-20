package org.csenseoss.kotlin.extensions.collections.iterable

import org.csenseoss.kotlin.classes.general.counter.*
import org.csenseoss.kotlin.extensions.collections.*


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