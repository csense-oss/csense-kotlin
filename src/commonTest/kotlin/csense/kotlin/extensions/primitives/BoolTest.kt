package csense.kotlin.extensions.primitives

import csense.kotlin.tests.assertions.failTest
import csense.kotlin.tests.assertions.shouldNotBeCalled
import kotlin.test.Test


class BoolTest {

    @Test
    fun onFalse() {
        true.onFalse { shouldNotBeCalled() }
        var counter = 0
        false.onFalse { counter += 1 }
    }

    @Test
    fun onTrue() {
        false.onTrue { shouldNotBeCalled() }
        var counter = 0
        true.onTrue { counter += 1 }
    }


    @Test
    fun ifTrue() {
        false.ifTrue { shouldNotBeCalled() }
        var counter = 0
        true.ifTrue { counter += 1 }
    }

    @Test
    fun ifFalse() {
        true.ifFalse { shouldNotBeCalled() }
        var counter = 0
        false.ifFalse { counter += 1 }
    }
}