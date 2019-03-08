package csense.kotlin.extensions.collections.list

import csense.kotlin.test.assertions.*
import kotlin.test.*


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

    @Ignore
    @Test
    fun subList() {

    }

    @Ignore
    @Test
    fun repeat() {
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
}