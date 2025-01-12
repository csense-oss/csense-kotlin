@file:Suppress("unused")

package org.csenseoss.kotlin.extensions.collections.iterator

import org.csenseoss.kotlin.tests.assertions.collections.iterable.*
import org.csenseoss.kotlin.tests.assertions.general.*
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
            result.assert(1)
        }

        @Test
        fun multipleStart() {
            val list: List<String> = listOf("1", "2", "3")
            val itt: Iterator<String> = list.iterator()
            val result: List<Int> = itt.map { it.toInt() }
            result.assert(1, 2, 3)
        }

        @Test
        fun multipleMiddle() {
            val list: List<String> = listOf("1", "2", "3")
            val itt: Iterator<String> = list.iterator()
            itt.next()
            val result: List<Int> = itt.map { it.toInt() }
            result.assert(2, 3, message = "should use the iterator and start at the current location")
        }
    }
}