package csense.kotlin.extensions.collections

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class IteratorKtTest {

    @Test
    fun findFirst() {
        val empty = listOf<String>()
        empty.iterator()
            .findFirst { it == "test" }
            .assertNull()

        val single = listOf("test2")

        single.iterator()
            .findFirst { it == "test" }
            .assertNull("nothing is test in the single collection")

        single.iterator()
            .findFirst { it == "test2" }
            .assertNotNullAndEquals("test2", "should find value searching for when there")


        val multiple = listOf("test1", "test2")

        multiple.iterator()
            .findFirst { it == "test3" }
            .assertNull()

        multiple.iterator()
            .findFirst { it == "test2" }
            .assertNotNullAndEquals("test2")

        multiple.iterator()
            .findFirst { it == "test1" }
            .assertNotNullAndEquals("test1")


        multiple.iterator()
            .findFirst { true }
            .assertNotNullAndEquals("test1")

        var counterA = 0

        multiple.iterator()
            .findFirst {
                counterA += 1
                counterA == 2
            }
            .assertNotNullAndEquals("test2")


        var counterB = 0
        multiple.iterator()
            .findFirst {
                counterB += 1
                counterB == 3
            }
            .assertNull()


    }


    class IteratorTToMutableList {
        @Test
        fun empty() {
            val list = listOf<String>()
            val itt = list.iterator()
            val result = itt.toMutableList()
            result.assertEmpty()
        }

        @Test
        fun single() {
            val list = listOf("1")
            val itt = list.iterator()
            val result = itt.toMutableList()
            result.assertSingle("1")
        }

        @Test
        fun multipleStart() {
            val list = listOf("1", "2", "3")
            val itt = list.iterator()
            val result = itt.toMutableList()
            result.assertSize(3)
            result.assertContainsInOrder("1", "2", "3")
        }

        @Test
        fun multipleMiddle() {
            val list = listOf("1", "2", "3")
            val itt = list.iterator()
            itt.next()
            val result = itt.toMutableList()
            result.assertSize(2, message = "should use the iterator and start at the current location")
            result.assertContainsInOrder("2", "3")
        }


    }


    class IteratorTToList {
        @Test
        fun empty() {
            val list = listOf<String>()
            val itt = list.iterator()
            val result = itt.toList()
            result.assertEmpty()
        }

        @Test
        fun single() {
            val list = listOf("1")
            val itt = list.iterator()
            val result = itt.toList()
            result.assertSingle("1")
        }

        @Test
        fun multipleStart() {
            val list = listOf("1", "2", "3")
            val itt = list.iterator()
            val result = itt.toList()
            result.assertSize(3)
            result.assertContainsInOrder("1", "2", "3")
        }

        @Test
        fun multipleMiddle() {
            val list = listOf("1", "2", "3")
            val itt = list.iterator()
            itt.next()
            val result = itt.toList()
            result.assertSize(2, message = "should use the iterator and start at the current location")
            result.assertContainsInOrder("2", "3")
        }


    }


    class IteratorTMap {
        @Test
        fun empty() {
            val list = listOf<String>()
            val itt = list.iterator()
            val result = itt.map { shouldNotBeCalled() }
            result.assertEmpty()
        }

        @Test
        fun single() {
            val list = listOf("1")
            val itt = list.iterator()
            val result = itt.map { it.toInt() }
            result.assertSingle(1)
        }

        @Test
        fun multipleStart() {
            val list = listOf("1", "2", "3")
            val itt = list.iterator()
            val result = itt.map { it.toInt() }
            result.assertSize(3)
            result.assertContainsInOrder(1, 2, 3)
        }

        @Test
        fun multipleMiddle() {
            val list = listOf("1", "2", "3")
            val itt = list.iterator()
            itt.next()
            val result = itt.map { it.toInt() }
            result.assertSize(2, message = "should use the iterator and start at the current location")
            result.assertContainsInOrder(2, 3)
        }
    }

    class IteratorTAtEnd {
        @Test
        fun empty() {
            listOf<String>().iterator().atEnd().assertTrue()
        }

        @Test
        fun single() {
            val itt = listOf("a").iterator()
            itt.atEnd().assertFalse()
            itt.next()
            itt.atEnd().assertTrue()
        }

        @Test
        fun multiple() {
            val itt = listOf("a", "1").iterator()
            itt.atEnd().assertFalse()
            itt.next()
            itt.atEnd().assertFalse()
            itt.next()
            itt.atEnd().assertTrue()
        }
    }
}

