package csense.kotlin.extensions.collections.generic.collection.operations

import csense.kotlin.extensions.collections.generic.collection.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*


class GenericArrayForeachDiscardResult {
    @Test
    fun empty() {
        var counter = 0
        var innerCounter = 0
        GenericCollections.foreachDiscardResult(
            0,
            { counter += 1; "$it" },
            { t -> innerCounter += 1;t })
        counter.assert(0, "should not call when empty")
        innerCounter.assert(0, "should not call receiver when empty")
    }

    @Test
    fun single() {
        var counter = 0
        var innerCounter = 0
        GenericCollections.foreachDiscardResult(
            1,
            { counter += 1; "$it" },
            { t -> innerCounter += 1;t })
        counter.assert(1, "should called size times")
        innerCounter.assert(1, "should call receiver")
    }

    @Test
    fun multiple() {
        var counter = 0
        var innerCounter = 0
        GenericCollections.foreachDiscardResult(
            50,
            { counter += 1; "$it" },
            { t -> innerCounter += 1;t })
        counter.assert(50, "should called size times")
        innerCounter.assert(50, "should call receiver size times")
    }
}