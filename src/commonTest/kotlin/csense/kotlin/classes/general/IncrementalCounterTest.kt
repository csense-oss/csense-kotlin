package csense.kotlin.classes.general

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class IncrementalCounterTest {
    @Test
    fun constructorStart() {
        val zero = IncrementalCounter(start = 0)
        val one = IncrementalCounter(start = 1)
        (zero == one).assertFalse()
    }

    @Test
    fun value() {
        val zero = IncrementalCounter(start = 0)
        zero.value.assert(0)

        val two = IncrementalCounter(start = 2)
        two.value.assert(2)
    }

    @Test
    fun valueAndIncrement() {
        val counter = IncrementalCounter(start = 0)
        counter.valueAndIncrement.assert(0)
        counter.valueAndIncrement.assert(1)
        counter.value.assert(2)
    }

    @Test
    fun increment() {
        val counter = IncrementalCounter(start = 0)
        counter.value.assert(0)
        counter.increment()
        counter.value.assert(1)
    }

    @Test
    fun incrementIf() {
        val no = IncrementalCounter(start = 0)
        no.incrementIf(shouldIncrement = false)
        no.value.assert(0)

        val shouldIncrement = IncrementalCounter(start = 0)
        shouldIncrement.incrementIf(shouldIncrement = true)
        shouldIncrement.value.assert(1)
    }
}