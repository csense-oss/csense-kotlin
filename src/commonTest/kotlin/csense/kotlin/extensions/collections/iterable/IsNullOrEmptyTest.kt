package csense.kotlin.extensions.collections.iterable

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class IsNullOrEmptyTest {

    @Test
    fun collectionIsNullOrEmpty() {
        val nullLst: List<String>? = null
        nullLst.isNullOrEmpty().assertTrue()
        listOf<String>().nullable().isNullOrEmpty().assertTrue()
        listOf("").nullable().isNullOrEmpty().assertFalse()
        listOf("", "test2").nullable().isNullOrEmpty().assertFalse()
    }


}