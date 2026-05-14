package org.csenseoss.kotlin.primitive.byteBitOperations.operations

import org.csenseoss.kotlin.primitive.byteBitOperations.*
import org.csenseoss.kotlin.tests.assertions.general.*
import org.csenseoss.kotlin.tests.assertions.primitives.boolean.*
import org.csenseoss.kotlin.tests.assertions.primitives.byte.*
import kotlin.experimental.*
import kotlin.test.*

class ByteBitOperationsTest {
    class ByteBitOperationsZeroBit {

        @Test
        fun allZero() {
            ByteBitOperations(0B0000_0000).zeroBit(0).assert(0B0000_0000)
        }

        @Test
        fun zerosBit() {
            ByteBitOperations(0B0111_1111).zeroBit(4).assert(0B0110_1111)
        }

        @Test
        fun zeroFirstBit() {
            ByteBitOperations(0B0111_1111).zeroBit(0).assert(0B0111_1110)
        }

        @Test
        fun zeroLastBit() {
            ByteBitOperations(0B1111_1111.toByte()).zeroBit(7).assert(0B0111_1111)
        }

        @Test
        fun zeroBitOutOfRange() {
            ByteBitOperations(0B0111_1111).zeroBit(9).assert(0B0111_1111)
            ByteBitOperations(0B1111_1111.toByte()).zeroBit(8).assert(0B1111_1111.toByte())
        }

    }

    class ByteBitOperationsSetBit {

        @Test
        fun allOnes() {
            ByteBitOperations(0B1111_1111.toByte()).setBit(0).assert(0B1111_1111.toByte())
        }


        @Test
        fun setFirstBit() {
            ByteBitOperations(0B1111_1110.toByte()).setBit(0).assert(0B1111_1111.toByte())
        }

        @Test
        fun setsBit() {
            ByteBitOperations(0B1110_1111.toByte()).setBit(4).assert(0B1111_1111.toByte())
        }

        @Test
        fun setLastBit() {
            ByteBitOperations(0B0111_1111.toByte()).setBit(7).assert(0B1111_1111.toByte())
        }

        @Test
        fun setBitOutOfRange() {
            ByteBitOperations(0B0111_1111).setBit(9).assert(0B0111_1111)
            ByteBitOperations(0B1111_1111.toByte()).setBit(8).assert(0B1111_1111.toByte())
        }
    }

    @Test
    fun byteBits() {
        0.toByte().bits.assertByEquals(0.toByte().bits)
        (0.toByte().bits != 1.toByte().bits).assertTrue()
    }

    @Test
    fun byteBitOperationsShl() {
        0.toByte().bits.shl(2).assert(0)
        1.toByte().bits.shl(1).assert((2 * 1).toByte())
        2.toByte().bits.shl(1).assert((2 * 2).toByte())
        8.toByte().bits.shl(2).assert((8 * 2 * 2).toByte())
    }

    @Test
    fun byteBitOperationsShr() {
        0.toByte().bits.shr(2).assert(0)
        1.toByte().bits.shr(1).assert(0)
        2.toByte().bits.shr(1).assert(1)
        8.toByte().bits.shr(2).assert(((8 / 2) / 2).toByte())
    }

    class ByteBitOperationsUpdateLowerNibble {
        @Test
        fun empty() {
            val byte: Byte = 0b0000_0000
            val res = byte.bits.updateLowerNibble(0b1111)
            res.assert(0b0000_1111)
        }

        @Test
        fun withBitsSet() {
            val byte: Byte = 0b0100_1010
            val res = byte.bits.updateLowerNibble(0b1111)
            res.and(0b0000_1111)
            res.assert(0b0100_1111)
        }
    }

    class ByteBitOperationsUpdateUpperNibble {
        @Test
        fun empty() {
            val byte: Byte = 0b0000_0000
            val res = byte.bits.updateUpperNibble(0b1111)
            res.assert(0b1111_0000.toByte())
        }

        @Test
        fun withBitsSet() {
            val byte: Byte = 0b0100_1010
            val res = byte.bits.updateUpperNibble(0b1111)
            res.and(0b1111_0000.toByte())
            res.assert(0b1111_1010.toByte())
        }
    }

    @Test
    fun byteBitOperationsShiftUpperNibbleToLower() {
        val byte: Byte = 0b1111_0000.toByte()
        val res = byte.bits.shiftUpperNibbleToLower()
        res.assert(0b0000_1111.toByte())
    }

    @Test
    fun byteBitOperationsShiftLowerNibbleToUpper() {
        val byte: Byte = 0b0000_1111
        val res = byte.bits.shiftLowerNibbleToUpper()
        res.assert(0b1111_0000.toByte())
    }

    @Test
    fun byteBitOperationsClearLowerNibble() {
        val byte: Byte = 0b1111_1111.toByte()
        val res = byte.bits.clearLowerNibble()
        res.assert(0b1111_0000.toByte())
    }

    @Test
    fun byteBitOperationsClearUpperNibble() {
        val byte: Byte = 0b1111_1111.toByte()
        val res = byte.bits.clearUpperNibble()
        res.assert(0b0000_1111.toByte())
    }


    @Test
    fun byteBitOperationsUshr() {
        val byte: Byte = 0b1010_1010.toByte()
        byte.bits.ushr(8)
            .assert(0, message = "shifting all bits away should leave 0")

        byte.bits.ushr(1)
            .assert(0b0101_0101.toByte())

        byte.bits.ushr(4)
            .assert(0b0000_1010.toByte())

    }

    @Test
    fun byteBitOperationsSplitIntoNibbles() {
        val byte: Byte = 0b1001_1110.toByte()
        val res = byte.bits.splitIntoNibbles()
        res.lowerNibble.assert(0b1110)
        res.upperNibble.assert(0b1001)
    }

    @Test
    fun byteBitOperations() {
        val first = 0.toByte().bits
        val second = 1.toByte().bits
        (first == second).assertFalse(message = "should be different instances")
    }
}