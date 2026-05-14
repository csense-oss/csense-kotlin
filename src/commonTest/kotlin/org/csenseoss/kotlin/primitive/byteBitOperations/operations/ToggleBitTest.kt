package org.csenseoss.kotlin.primitive.byteBitOperations.operations

import org.csenseoss.kotlin.primitive.byteBitOperations.ByteBitOperations
import org.csenseoss.kotlin.tests.assertions.primitives.byte.*
import kotlin.test.*

class ToggleBitTest {
    @Test
    fun zeroToOne() {
        ByteBitOperations(0b0000).toggleBit(bitIndex = 0, setBit = true).assert(0b0001)
        ByteBitOperations(0b0000).toggleBit(bitIndex = 1, setBit = true).assert(0b0010)
        ByteBitOperations(0b0000).toggleBit(bitIndex = 2, setBit = true).assert(0b0100)
        ByteBitOperations(0b0000).toggleBit(bitIndex = 3, setBit = true).assert(0b1000)
    }

    @Test
    fun oneToZero() {
        ByteBitOperations(0b1111).toggleBit(bitIndex = 0, setBit = false).assert(0b1110)
        ByteBitOperations(0b1111).toggleBit(bitIndex = 1, setBit = false).assert(0b1101)
        ByteBitOperations(0b1111).toggleBit(bitIndex = 2, setBit = false).assert(0b1011)
        ByteBitOperations(0b1111).toggleBit(bitIndex = 3, setBit = false).assert(0b0111)
    }
}