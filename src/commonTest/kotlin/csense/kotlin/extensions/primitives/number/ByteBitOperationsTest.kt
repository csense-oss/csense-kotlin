package csense.kotlin.extensions.primitives.number

import csense.kotlin.tests.assertions.*
import kotlin.experimental.*
import kotlin.test.*

class ByteBitOperationsTest {

    @Test
    fun byteBitOperationsShl() {
        0.toByte().bitOperations.shl(2).assert(0)
        1.toByte().bitOperations.shl(1).assert((2 * 1).toByte())
        2.toByte().bitOperations.shl(1).assert((2 * 2).toByte())
        8.toByte().bitOperations.shl(2).assert((8 * 2 * 2).toByte())
    }

    @Test
    fun byteBitOperationsShr() {
        0.toByte().bitOperations.shr(2).assert(0)
        1.toByte().bitOperations.shr(1).assert(0)
        2.toByte().bitOperations.shr(1).assert(1)
        8.toByte().bitOperations.shr(2).assert(((8 / 2) / 2).toByte())
    }

    class ByteBitOperationsUpdateLowerNibble {
        @Test
        fun empty() {
            val byte: Byte = 0b0000_0000
            val res = byte.bitOperations.updateLowerNibble(0b1111)
            res.assert(0b0000_1111)
        }

        @Test
        fun withBitsSet() {
            val byte: Byte = 0b0100_1010
            val res = byte.bitOperations.updateLowerNibble(0b1111)
            res.and(0b0000_1111)
            res.assert(0b0100_1111)
        }
    }

    class ByteBitOperationsUpdateUpperNibble {
        @Test
        fun empty() {
            val byte: Byte = 0b0000_0000
            val res = byte.bitOperations.updateUpperNibble(0b1111)
            res.assert(0b1111_0000.toByte())
        }

        @Test
        fun withBitsSet() {
            val byte: Byte = 0b0100_1010
            val res = byte.bitOperations.updateUpperNibble(0b1111)
            res.and(0b1111_0000.toByte())
            res.assert(0b1111_1010.toByte())
        }
    }

    @Test
    fun byteBitOperationsShiftUpperNibbleToLower() {
        val byte: Byte = 0b1111_0000.toByte()
        val res = byte.bitOperations.shiftUpperNibbleToLower()
        res.assert(0b0000_1111.toByte())
    }

    @Test
    fun byteBitOperationsShiftLowerNibbleToUpper() {
        val byte: Byte = 0b0000_1111
        val res = byte.bitOperations.shiftLowerNibbleToUpper()
        res.assert(0b1111_0000.toByte())
    }

    @Test
    fun byteBitOperationsClearLowerNibble() {
        val byte: Byte = 0b1111_1111.toByte()
        val res = byte.bitOperations.clearLowerNibble()
        res.assert(0b1111_0000.toByte())
    }

    @Test
    fun byteBitOperationsClearUpperNibble() {
        val byte: Byte = 0b1111_1111.toByte()
        val res = byte.bitOperations.clearUpperNibble()
        res.assert(0b0000_1111.toByte())
    }


    @Test
    fun byteBitOperationsUshr() {
        val byte: Byte = 0b1010_1010.toByte()
        byte.bitOperations.ushr(8)
            .assert(0, message = "shifting all bits away should leave 0")

        byte.bitOperations.ushr(1)
            .assert(0b0101_0101.toByte())

        byte.bitOperations.ushr(4)
            .assert(0b0000_1010.toByte())

    }

    @Test
    fun byteBitOperationsSplitIntoNibbles() {
        val byte: Byte = 0b1001_1110.toByte()
        val res = byte.bitOperations.splitIntoNibbles()
        res.lowerNibble.assert(0b1110)
        res.upperNibble.assert(0b1001)
    }

    @Test
    fun byteBitOperations() {
        val first = 0.toByte().bitOperations
        val second = 1.toByte().bitOperations
        (first == second).assertFalse(message = "should be different instances")
    }
}