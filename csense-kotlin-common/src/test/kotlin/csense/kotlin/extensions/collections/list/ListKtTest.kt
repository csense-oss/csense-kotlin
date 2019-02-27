package csense.kotlin.extensions.collections.list

import csense.kotlin.test.assertions.*
import kotlin.test.*


class ListKtTest {

    @Ignore
    @Test
    fun limitToSize() {
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