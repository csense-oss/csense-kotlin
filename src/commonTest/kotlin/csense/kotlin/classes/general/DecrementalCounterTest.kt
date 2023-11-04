@file:Suppress("unused")

package csense.kotlin.classes.general

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class DecrementalCounterTest {
    @Test
    fun constructorStart() {
        val zero = DecrementalCounter(from = 0)
        val one = DecrementalCounter(from = 1)
        (zero == one).assertFalse()
    }

    @Test
    fun value() {
        val zero = DecrementalCounter(from = 0)
        zero.value.assert(0)

        val two = DecrementalCounter(from = 2)
        two.value.assert(2)
    }

    @Test
    fun valueAndIncrement() {
        val counter = DecrementalCounter(from = 0)
        counter.valueAndDecrement.assert(0)
        counter.valueAndDecrement.assert(-1)
        counter.value.assert(-2)
    }

    @Test
    fun increment() {
        val counter = DecrementalCounter(from = 0)
        counter.value.assert(0)
        counter.decrement()
        counter.value.assert(-1)
    }

    @Test
    fun incrementIf() {
        val no = DecrementalCounter(from = 0)
        no.decrementIf(shouldDecrement = false)
        no.value.assert(0)

        val shouldIncrement = DecrementalCounter(from = 0)
        shouldIncrement.decrementIf(shouldDecrement = true)
        shouldIncrement.value.assert(-1)
    }
}