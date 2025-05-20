@file:Suppress("unused")

package org.csenseoss.kotlin.classes.iterator.empty

import org.csenseoss.kotlin.tests.assertions.exceptions.*
import org.csenseoss.kotlin.tests.assertions.primitives.boolean.*
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