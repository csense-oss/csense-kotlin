package org.csenseoss.kotlin.extensions.collections.generic.collection.operations

import org.csenseoss.kotlin.extensions.collections.generic.collection.*
import org.csenseoss.kotlin.tests.assertions.*
import org.csenseoss.kotlin.tests.assertions.general.*
import kotlin.test.*

class IndexOfLastOrNullTest {

    @Test
    fun badRange() {
        GenericCollections.indexOfLastOrNull(
            startFromEndIndex = 10,
            length = 0,
            getElement = { failTest("") },
            predicate = { failTest("") }
        ).assertNull()
    }

    @Test
    fun emptyRange() {
        GenericCollections.indexOfLastOrNull(
            startFromEndIndex = 0,
            length = 0,
            getElement = { failTest("") },
            predicate = { failTest("") }
        ).assertNull()
    }
}