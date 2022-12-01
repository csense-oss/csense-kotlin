package csense.kotlin.extensions.primitives.operations

import csense.kotlin.tests.assertions.*
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
}