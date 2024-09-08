package org.csenseoss.kotlin.classes.iterator

import org.csenseoss.kotlin.general.*

public object EmptyListIterator : ListIterator<Nothing> {
    override fun hasNext(): Boolean = false

    override fun hasPrevious(): Boolean = false

    @Throws(UnexpectedException::class)
    override fun next(): Nothing = unexpected()

    override fun nextIndex(): Int = badIndex

    @Throws(UnexpectedException::class)
    override fun previous(): Nothing = unexpected()

    override fun previousIndex(): Int = badIndex

    public const val badIndex: Int = -1
}