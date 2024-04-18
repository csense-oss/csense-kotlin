@file:Suppress("NOTHING_TO_INLINE")

package org.csenseoss.kotlin.extensions.collections.list.mutable

import csense.kotlin.annotations.numbers.*
import org.csenseoss.kotlin.extensions.collections.generic.collectionBounds.operations.*
import org.csenseoss.kotlin.extensions.general.*

public inline fun <Element> MutableList<Element>.replaceOrAdd(
    item: Element,
    @IntLimit(from = 0) index: Int
): Unit = when (isIndex.inBoundsEndNotInBounds(index)) {
    true -> replace(item, index)
    false -> add(item).toUnit()
}