package csense.kotlin.algorithms

import csense.kotlin.test.assertions.*
import kotlin.test.*

class BinarySearchKtTest {

    @Test
    fun testBinarySearch() {
        val strArray = listOf("a", "b", "c", "d")
        val indexOfA = strArray.binarySearch { item: String, _: Int ->
            return@binarySearch item.compareTo("a").toComparing()
        }
        indexOfA.assertNotNullAndEquals(0)
    }

}