package org.csenseoss.kotlin.extensions.collections.collection

import org.csenseoss.kotlin.tests.assertions.*
import org.csenseoss.kotlin.tests.assertions.general.*
import org.csenseoss.kotlin.tests.assertions.primitives.charSequence.*
import kotlin.test.*

class SecondLastOrNullTest {
    @Test
    fun collectionItemSecondLastOrNull() {
        val emptyData: Collection<String> = listOf()
        emptyData.secondLastOrNull().assertNull()
        val singleData: Collection<String> = listOf("a")
        singleData.secondLastOrNull().assertNull()
        val two: Collection<String> = listOf("a", "b")
        two.secondLastOrNull().assert("a")
        val aLot = listOf("a", "1", "2", "b")
        aLot.secondLastOrNull().assert("2")
    }
}