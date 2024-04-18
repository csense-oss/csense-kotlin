@file:Suppress("unused")

package org.csenseoss.kotlin.classes.iterator

import csense.kotlin.tests.assertions.*
import org.csenseoss.kotlin.classes.iterator.EmptyIterator
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