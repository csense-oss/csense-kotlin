@file:Suppress("unused")

package csense.kotlin.extensions.collections.collection

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class IndexOfTest {

    class CollectionItemIndexOfOrNull {
        @Test
        fun empty() {
            val lst = listOf<String>()
            lst.indexOfOrNull("a").assertNull()
            lst.indexOfOrNull("").assertNull()
            lst.indexOfOrNull("b").assertNull()
        }

        @Test
        fun single() {
            val lst = listOf("a")
            lst.indexOfOrNull("a").assert(0)
            lst.indexOfOrNull("").assertNull()
            lst.indexOfOrNull("b").assertNull()
        }

        @Test
        fun multiple() {
            val lst = listOf("d", "c")
            lst.indexOfOrNull("a").assertNull()
            lst.indexOfOrNull("").assertNull()
            lst.indexOfOrNull("b").assertNull()
            lst.indexOfOrNull("c").assert(1)
            lst.indexOfOrNull("d").assert(0)
            lst.indexOfOrNull("dc").assertNull()
        }

        @Test
        fun multipleOccurrences() {
            val lst = listOf("d", "c", "d", "c")
            lst.indexOfOrNull("a").assertNull()
            lst.indexOfOrNull("").assertNull()
            lst.indexOfOrNull("c").assert(1)
            lst.indexOfOrNull("d").assert(0)
        }
    }

    class CollectionItemLastIndexOfOrNull {
        @Test
        fun empty() {
            val lst = listOf<String>()
            lst.lastIndexOfOrNull("a").assertNull()
            lst.lastIndexOfOrNull("").assertNull()
            lst.lastIndexOfOrNull("b").assertNull()
        }

        @Test
        fun single() {
            val lst = listOf("a")
            lst.lastIndexOfOrNull("a").assert(0)
            lst.lastIndexOfOrNull("").assertNull()
            lst.lastIndexOfOrNull("b").assertNull()
        }

        @Test
        fun multiple() {
            val lst = listOf("d", "c")
            lst.lastIndexOfOrNull("a").assertNull()
            lst.lastIndexOfOrNull("").assertNull()
            lst.lastIndexOfOrNull("b").assertNull()
            lst.lastIndexOfOrNull("c").assert(1)
            lst.lastIndexOfOrNull("d").assert(0)
            lst.lastIndexOfOrNull("dc").assertNull()
        }

        @Test
        fun multipleOccurrences() {
            val lst = listOf("d", "c", "d", "c")
            lst.lastIndexOfOrNull("a").assertNull()
            lst.lastIndexOfOrNull("").assertNull()
            lst.lastIndexOfOrNull("c").assert(3)
            lst.lastIndexOfOrNull("d").assert(2)
        }
    }


    class CollectionItemIndexOfFirstOrNull {
        @Test
        fun empty() {
            listOf<String>().indexOfFirstOrNull { shouldNotBeCalled() }.assertNull()
        }

        @Test
        fun single() {
            listOf("test").indexOfFirstOrNull { false }.assertNull()
            listOf("test").indexOfFirstOrNull { true }.assert(0)
            listOf("test").indexOfFirstOrNull { it == "test" }.assert(0)
        }

        @Test
        fun multiple() {
            listOf("1", "2", "3", "1").indexOfFirstOrNull { it == "1" }
                .assert(0, "should search from the start")
            listOf("1", "2", "3", "1").indexOfFirstOrNull { it == "3" }.assert(2)
            listOf("1", "2", "3", "1").indexOfFirstOrNull { it == "4" }.assertNull()
        }
    }

    class CollectionItemIndexOfLastOrNull {
        @Test
        fun empty() {
            listOf<String>().indexOfLastOrNull { shouldNotBeCalled() }.assertNull()
        }

        @Test
        fun single() {
            listOf("test").indexOfLastOrNull { false }.assertNull()
            listOf("test").indexOfLastOrNull { true }.assert(0)
            listOf("test").indexOfLastOrNull { it == "test" }.assert(0)
        }

        @Test
        fun multiple() {
            listOf("1", "2", "3", "1").indexOfLastOrNull { it == "1" }
                .assert(3, "should search from the end towards the start")
            listOf("1", "2", "3", "1").indexOfLastOrNull { it == "3" }.assert(2)
            listOf("1", "2", "3", "1").indexOfLastOrNull { it == "4" }.assertNull()
        }
    }
}