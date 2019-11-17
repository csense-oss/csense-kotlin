@file:Suppress("unused")

package csense.kotlin.extensions.collections.list

import csense.kotlin.tests.assertions.assert
import csense.kotlin.tests.assertions.assertEmpty
import csense.kotlin.tests.assertions.assertSize
import kotlin.test.Test


class ListKtTest {

    @Test
    fun limitToSize() {
        listOf<String>().limitToSize(0).assertSize(0)
        listOf<String>().limitToSize(-1).assertSize(0)
        listOf<String>().limitToSize(1).assertSize(0)

        listOf("a").limitToSize(0).assertSize(0)
        listOf("a").limitToSize(1).assertSize(1)
        listOf("a").limitToSize(-1).assertSize(0)
        listOf("a").limitToSize(2).assertSize(1)

        listOf("a", "b").limitToSize(1).apply {
            assertSize(1)
            first().assert("a")
        }

        listOf("a", "b").limitToSize(2).apply {
            assertSize(2)
            first().assert("a")
            last().assert("b")
        }
        listOf("a", "b").limitToSize(30).assertSize(2)
    }


    @Test
    fun repeatToSize() {
        val collection: MutableList<Int> = mutableListOf()
        collection.repeatToSize(50).apply {
            size.assert(0, "repeating nothing is wrong and gives nothing")
        }

        collection.add(42)
        collection.repeatToSize(50).apply {
            size.assert(50)
        }

        collection.add(42)
        collection.repeatToSize(50).apply {
            size.assert(50)
        }
        //todo more cases here
    }

    class ListTSubList {
        @Test
        fun empty() {
            listOf<String>().subList(0..2).assertEmpty()
            listOf<String>().subList(0..1).assertEmpty()
            listOf<String>().subList(0..10).assertEmpty()
        }

        @Test
        fun single() {
            listOf("a").subList(0..2).assertEmpty()
            listOf("a").subList(0..1).apply {
                assertSize(1)
                first().assert("a")
            }
            listOf("a").subList(0..10).assertEmpty()
        }

        @Test
        fun multiple() {
            listOf("a", "b").subList(0..2).apply {
                assertSize(2)
                first().assert("a")
                last().assert("b")
            }
            listOf("a", "b").subList(0..1).apply {
                assertSize(1)
                first().assert("a")
            }
            listOf("a", "b").subList(1..2).apply {
                assertSize(1)
                first().assert("b")
            }
            listOf("a", "b").subList(0..10).assertEmpty()
        }
    }

    class ListTRepeat {
        @Test
        fun empty() {
            listOf<String>().repeat(0).assertEmpty()
            listOf<String>().repeat(1).assertEmpty()
            listOf<String>().repeat(100).assertEmpty()
        }

        @Test
        fun single() {
            listOf("a").repeat(0).assertEmpty()
            listOf("a").repeat(1).apply {
                assertSize(1)
                first().assert("a")
            }
            listOf("a").repeat(10).assertSize(10)
        }

        @Test
        fun multiple() {
            listOf("a", "1").repeat(0).assertEmpty()
            listOf("a", "1").repeat(1).apply {
                assertSize(2)
                first().assert("a")
                last().assert("1")
            }
            listOf("a", "1").repeat(10).assertSize(20)
        }
    }

    class ListTSubListSafe {
        @Test
        fun empty() {
            listOf<String>().subListSafe(0, 2).assertEmpty()
            listOf<String>().subListSafe(0, 1).assertEmpty()
            listOf<String>().subListSafe(0, 10).assertEmpty()
        }

        @Test
        fun single() {
            listOf("a").subListSafe(0, 2).assertEmpty()
            listOf("a").subListSafe(0, 1).apply {
                assertSize(1)
                first().assert("a")
            }
            listOf("a").subListSafe(0, 10).assertEmpty()
        }

        @Test
        fun multiple() {
            listOf("a", "b").subListSafe(0, 2).apply {
                assertSize(2)
                first().assert("a")
                last().assert("b")
            }
            listOf("a", "b").subListSafe(0, 1).apply {
                assertSize(1)
                first().assert("a")
            }
            listOf("a", "b").subListSafe(1, 2).apply {
                assertSize(1)
                first().assert("b")
            }
            listOf("a", "b").subListSafe(0, 10).assertEmpty()
        }
    }
}