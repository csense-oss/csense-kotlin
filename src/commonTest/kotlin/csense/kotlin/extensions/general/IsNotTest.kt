package csense.kotlin.extensions.general

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class IsNotTest {

    @Test
    fun anyIsNot() {
        42.isNot<Int>().assertFalse()
        "".isNot<String>().assertFalse()
        "".isNot<CharSequence>().assertFalse()
        42.isNot<Char>().assertTrue()
        42.0.isNot<Char>().assertTrue()
        42.0.isNot<Number>().assertFalse()
        listOf<String>().isNot<List<Double>>().assertFalse("Type erasure...")
    }
}