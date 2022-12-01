package csense.kotlin.classes.iterator

import csense.kotlin.general.*

public object EmptyListIterator : ListIterator<Nothing> {
    override fun hasNext(): Boolean = false

    override fun hasPrevious(): Boolean = false

    override fun next(): Nothing = unexpected()

    override fun nextIndex(): Int = badIndex

    override fun previous(): Nothing = unexpected()

    override fun previousIndex(): Int = badIndex

    public const val badIndex: Int = -1
}