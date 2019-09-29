@file:Suppress("MissingTestFunction")

package csense.kotlin.test.assertions

import kotlin.math.*
import kotlin.test.*


fun Double.assert(value: Double, delta: Double = 0.1, message: String = "expected $value within $delta margins, but got $this") {
    val safeDelta = abs(delta)
    assertTrue(this >= value - safeDelta && this <= value + safeDelta, message)
}

fun Float.assert(value: Float, delta: Float = 0.1f, message: String = "expected $value within $delta margins, but got $this") {
    val safeDelta = abs(delta)
    assertTrue(this >= value - safeDelta && this <= value + safeDelta, message)
}

fun <T : Number> T.assert(value: T, message: String = "") {
    assertEquals(value, this, message)
}

fun Char.assert(value: Char, message: String = "") {
    assertEquals(value, this, message)
}

fun Byte.assert(value: Byte, message: String = "") {
    assertEquals(value, this, message)
}

fun IntRange.assert(otherRange: IntRange, message: String = "") {
    assertEquals(otherRange, this, message)
}