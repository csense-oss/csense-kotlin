@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.extensions.collections.list.mutable

import csense.kotlin.annotations.numbers.*
import csense.kotlin.extensions.collections.generic.collectionBounds.operations.*
import csense.kotlin.extensions.general.*

public inline fun <Element> MutableList<Element>.replaceOrAdd(
    item: Element,
    @IntLimit(from = 0) index: Int
): Unit = when (isIndex.inBoundsEndNotInBounds(index)) {
    true -> replace(item, index)
    false -> add(item).toUnit()
}
