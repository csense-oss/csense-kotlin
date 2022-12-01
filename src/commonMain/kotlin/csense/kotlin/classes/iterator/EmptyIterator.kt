package csense.kotlin.classes.iterator

import csense.kotlin.general.*

public object EmptyIterator : Iterator<Nothing> {
    override fun hasNext(): Boolean = false
    override fun next(): Nothing = unexpected()
}