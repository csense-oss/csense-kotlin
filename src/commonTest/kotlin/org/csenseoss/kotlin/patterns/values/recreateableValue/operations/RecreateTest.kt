package org.csenseoss.kotlin.patterns.values.recreateableValue.operations

import org.csenseoss.kotlin.patterns.values.recreateableValue.*
import org.csenseoss.kotlin.tests.assertions.comparable.*
import kotlin.test.*

class RecreateTest {
    @Test
    fun recreate() {
        var counter = 0
        val container: RecreateableValue<Int> = RecreateableValue {
            counter += 1
            counter
        }
        container.value.assert(1)
        counter.assert(1)

        container.value.assert(1)
        counter.assert(1, "should not update until recreate is called")

        container.recreate().assert(2)
        counter.assert(2)
    }
}