@file:Suppress("unused")

package csense.kotlin.classes.iterator

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class EmptyListIteratorTest {


    @Test
    fun hasNext() {
        EmptyListIterator.hasNext().assertFalse(message = "empty does not have a next")
    }

    @Test
    fun hasPrevious() {
        EmptyListIterator.hasPrevious().assertFalse(message = "empty does not have a previous")
    }

    @Test
    fun nextIndex() {
        EmptyListIterator.nextIndex().assert(EmptyListIterator.badIndex, message = "empty does not have a next index")
    }

    @Test
    fun previousIndex() {
        EmptyListIterator.previousIndex()
            .assert(EmptyListIterator.badIndex, message = "empty does not have a previous index")
    }

    @Test
    fun next() = assertThrows<Throwable> {
        EmptyListIterator.next()
    }

    @Test
    fun previous() = assertThrows<Throwable> {
        EmptyListIterator.previous()
    }
}