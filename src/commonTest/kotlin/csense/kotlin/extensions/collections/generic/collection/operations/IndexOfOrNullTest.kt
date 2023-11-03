package csense.kotlin.extensions.collections.generic.collection.operations

import csense.kotlin.classes.general.*
import csense.kotlin.extensions.collections.generic.collection.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class IndexOfOrNullTest {

    @Test
    fun badRange() {
        GenericCollections.indexOfOrNull(
            byRange = IntRange(start = 10, endInclusive = -1),
            getElement = { failTest("") },
            predicate = { failTest("") }
        ).assertNull()
    }

    @Test
    fun emptyRange() {
        GenericCollections.indexOfOrNull(
            byRange = IntRange.EMPTY,
            getElement = { failTest("") },
            predicate = { failTest("") }
        ).assertNull()
    }

    @Test
    fun respectsDirectionForward() {
        val end = 2
        val range: IntProgression = 0.rangeUntil(end)
        val counter = IncrementalCounter()
        GenericCollections.indexOfOrNull(
            byRange = range,
            getElement = { index: Int ->
                index.assert(counter.value)
                counter.valueAndIncrement
            },
            predicate = { _: Int ->
                false
            }
        ).assertNull()
        counter.value.assert(end)
    }

    @Test
    fun respectsDirectionBackwards() {
        val length = 2
        val range: IntProgression = length.downTo(0)
        val counter = DecrementalCounter(from = length)
        GenericCollections.indexOfOrNull(
            byRange = range,
            getElement = { index: Int ->
                index.assert(counter.value)
                counter.valueAndDecrement
            },
            predicate = { _: Int ->
                false
            }
        ).assertNull()
        counter.value.assert(-1)
    }
}

