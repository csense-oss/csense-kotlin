@file:Suppress("unused")

package csense.kotlin.extensions.collections.iterator

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class ToListTest {


    class IteratorTToMutableList {
        @Test
        fun empty() {
            val list: List<String> = listOf()
            val itt: Iterator<String> = list.iterator()
            val result: MutableList<String> = itt.toMutableList()
            result.assertEmpty()
        }

        @Test
        fun single() {
            val list: List<String> = listOf("1")
            val itt: Iterator<String> = list.iterator()
            val result: MutableList<String> = itt.toMutableList()
            result.assertSingle("1")
        }

        @Test
        fun multipleStart() {
            val list: List<String> = listOf("1", "2", "3")
            val itt: Iterator<String> = list.iterator()
            val result: MutableList<String> = itt.toMutableList()
            result.assertSize(3)
            result.assertContainsInOrder("1", "2", "3")
        }

        @Test
        fun multipleMiddle() {
            val list: List<String> = listOf("1", "2", "3")
            val itt: Iterator<String> = list.iterator()
            itt.next()
            val result: MutableList<String> = itt.toMutableList()
            result.assertSize(size = 2, message = "should use the iterator and start at the current location")
            result.assertContainsInOrder("2", "3")
        }


    }


    class IteratorTToList {
        @Test
        fun empty() {
            val list: List<String> = listOf()
            val itt: Iterator<String> = list.iterator()
            val result: List<String> = itt.toList()
            result.assertEmpty()
        }

        @Test
        fun single() {
            val list: List<String> = listOf("1")
            val itt: Iterator<String> = list.iterator()
            val result: List<String> = itt.toList()
            result.assertSingle("1")
        }

        @Test
        fun multipleStart() {
            val list: List<String> = listOf("1", "2", "3")
            val itt: Iterator<String> = list.iterator()
            val result: List<String> = itt.toList()
            result.assertSize(3)
            result.assertContainsInOrder("1", "2", "3")
        }

        @Test
        fun multipleMiddle() {
            val list: List<String> = listOf("1", "2", "3")
            val itt: Iterator<String> = list.iterator()
            itt.next()
            val result: List<String> = itt.toList()
            result.assertSize(size = 2, message = "should use the iterator and start at the current location")
            result.assertContainsInOrder("2", "3")
        }


    }
}