@file:Suppress("unused")

package csense.kotlin.classes.iterator

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class EmptyIteratorTest {
    @Test
    fun hasNext() {
        EmptyIterator.hasNext().assertFalse("empty is empty")
    }

    @Test
    fun next() = assertThrows<Throwable> {
        EmptyIterator.next()
    }
}