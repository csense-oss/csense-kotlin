package org.csenseoss.kotlin.classes.iterator.empty

import org.csenseoss.kotlin.general.*

public object EmptyIterator : Iterator<Nothing> {
    override fun hasNext(): Boolean = false

    @Throws(UnexpectedException::class)
    override fun next(): Nothing = unexpected()
}