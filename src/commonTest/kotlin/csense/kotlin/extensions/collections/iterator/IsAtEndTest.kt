@file:Suppress("unused")

package csense.kotlin.extensions.collections.iterator

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class IsAtEndTest {

    class IteratorTIsAtEnd {
        @Test
        fun empty() {
            listOf<String>().iterator().isAtEnd().assertTrue()
        }

        @Test
        fun single() {
            val itt: Iterator<String> = listOf("a").iterator()
            itt.isAtEnd().assertFalse()
            itt.next()
            itt.isAtEnd().assertTrue()
        }

        @Test
        fun multiple() {
            val itt: Iterator<String> = listOf("a", "1").iterator()
            itt.isAtEnd().assertFalse()
            itt.next()
            itt.isAtEnd().assertFalse()
            itt.next()
            itt.isAtEnd().assertTrue()
        }
    }
}

