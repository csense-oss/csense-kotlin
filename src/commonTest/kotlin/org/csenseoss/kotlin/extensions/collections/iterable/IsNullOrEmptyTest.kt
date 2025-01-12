package org.csenseoss.kotlin.extensions.collections.iterable

import org.csenseoss.kotlin.extensions.collections.iterable.*
import org.csenseoss.kotlin.tests.assertions.*
import org.csenseoss.kotlin.tests.assertions.general.*
import org.csenseoss.kotlin.tests.assertions.primitives.boolean.*
import kotlin.collections.isNullOrEmpty
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