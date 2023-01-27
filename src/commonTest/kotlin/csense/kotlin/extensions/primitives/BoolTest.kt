package csense.kotlin.extensions.primitives

import csense.kotlin.extensions.primitives.boolean.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*


class BoolTest {

    @Test
    fun onFalse()  = assertCalled { shouldBeCalled ->
        true.onFalse<Nothing> { shouldNotBeCalled() }
        false.onFalse { shouldBeCalled()  }
    }

    @Test
    fun onTrue()  = assertCalled { shouldBeCalled ->
        false.onTrue<Nothing> { shouldNotBeCalled() }
        true.onTrue { shouldBeCalled()}
    }


    @Test
    fun ifTrue()  = assertCalled { shouldBeCalled ->
        false.ifTrue<Nothing> { shouldNotBeCalled() }
        true.ifTrue { shouldBeCalled() }
    }

    @Test
    fun ifFalse() = assertCalled { shouldBeCalled ->
        true.ifFalse<Nothing> { shouldNotBeCalled() }
        false.ifFalse { shouldBeCalled() }
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