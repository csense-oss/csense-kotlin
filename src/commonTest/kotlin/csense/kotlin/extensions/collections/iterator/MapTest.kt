@file:Suppress("unused")

package csense.kotlin.extensions.collections.iterator

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class MapTest {

    class IteratorTMap {
        @Test
        fun empty() {
            val list: List<String> = listOf()
            val itt: Iterator<String> = list.iterator()
            val result: List<Nothing> = itt.map { shouldNotBeCalled() }
            result.assertEmpty()
        }

        @Test
        fun single() {
            val list: List<String> = listOf("1")
            val itt: Iterator<String> = list.iterator()
            val result: List<Int> = itt.map { it.toInt() }
            result.assertSingle(1)
        }

        @Test
        fun multipleStart() {
            val list: List<String> = listOf("1", "2", "3")
            val itt: Iterator<String> = list.iterator()
            val result: List<Int> = itt.map { it.toInt() }
            result.assertSize(3)
            result.assertContainsInOrder(1, 2, 3)
        }

        @Test
        fun multipleMiddle() {
            val list: List<String> = listOf("1", "2", "3")
            val itt: Iterator<String> = list.iterator()
            itt.next()
            val result: List<Int> = itt.map { it.toInt() }
            result.assertSize(size = 2, message = "should use the iterator and start at the current location")
            result.assertContainsInOrder(2, 3)
        }
    }
}