@file:Suppress("MissingTestFunction")

package csense.kotlin.test.assertions

import kotlin.test.*

fun Boolean.assert(value: Boolean, message: String = "expected $value got $this") {
    assertEquals(value, this, message)
}

fun Boolean.assertFalse(message: String = "expected false got true") {
    assert(false, message)
}

fun Boolean.assertTrue(message: String = "expected true, got false") {
    assert(true, message)
}