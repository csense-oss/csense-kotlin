package csense.kotlin.extensions.primitives

import csense.kotlin.extensions.primitives.boolean.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*


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

    @Test
    fun booleanIsNullOrFalse() {
        (null as Boolean?).isNullOrFalse().assertTrue()
        false.nullable().isNullOrFalse().assertTrue()
        true.nullable().isNullOrFalse().assertFalse()
    }

    @Test
    fun booleanIsNullOrTrue() {
        (null as Boolean?).isNullOrTrue().assertTrue()
        false.nullable().isNullOrTrue().assertFalse()
        true.nullable().isNullOrTrue().assertTrue()
    }

    @Test
    fun booleanIsNotNullOrTrue() {
        (null as Boolean?).isNotNullOrTrue().assertFalse()
        false.nullable().isNotNullOrTrue().assertTrue()
        true.nullable().isNotNullOrTrue().assertFalse()
    }

    @Test
    fun booleanIsNotNullOrFalse() {
        (null as Boolean?).isNotNullOrFalse().assertFalse()
        false.nullable().isNotNullOrFalse().assertFalse()
        true.nullable().isNotNullOrFalse().assertTrue()
    }
}