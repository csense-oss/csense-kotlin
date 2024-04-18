package org.csenseoss.kotlin.classes.iterator

import org.csenseoss.kotlin.general.*

public object EmptyIterator : Iterator<Nothing> {
    override fun hasNext(): Boolean = false
    override fun next(): Nothing = unexpected()
}