package csense.kotlin.extensions.primitives

import csense.kotlin.tests.assertions.failTest
import kotlin.test.Test


class BoolTest {

    @Test
    fun onFalse() {
        true.onFalse { failTest("should not be called") }
        var counter = 0
        false.onFalse { counter += 1 }
    }

    @Test
    fun onTrue() {
        false.onTrue { failTest("should not be called") }
        var counter = 0
        true.onTrue { counter += 1 }
    }


    @Test
    fun ifTrue() {
        false.ifTrue { failTest("should not be called") }
        var counter = 0
        true.ifTrue { counter += 1 }
    }

    @Test
    fun ifFalse() {
        true.ifFalse { failTest("should not be called") }
        var counter = 0
        false.ifFalse { counter += 1 }
    }
}