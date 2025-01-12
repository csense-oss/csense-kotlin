package org.csenseoss.kotlin.extensions.collections.iterable

import org.csenseoss.kotlin.tests.assertions.*
import org.csenseoss.kotlin.tests.assertions.general.*
import org.csenseoss.kotlin.tests.assertions.primitives.boolean.*
import kotlin.test.*

class IsNotNullOrEmptyTest {
    @Test
    fun collectionIsNotNullOrEmpty() {
        val nullLst: List<String>? = null
        nullLst.isNotNullOrEmpty().assertFalse()
        listOf<String>().nullable().isNotNullOrEmpty().assertFalse()
        listOf("").nullable().isNotNullOrEmpty().assertTrue()
        listOf("", "test2").nullable().isNotNullOrEmpty().assertTrue()
    }
}