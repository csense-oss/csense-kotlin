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
            ByteBitOperations(0B0111_1111).zeroBit(4).assert(0B0111_0111)
        }

        @Test
        fun zeroFirstBit() {
            ByteBitOperations(0B0111_1111).zeroBit(0).assert(0B0111_1110)
        }
        @Test
        fun zeroBitOutOfRange() {
            ByteBitOperations(0B0111_1111).zeroBit(9).assert(0B0111_1111)
        }

    }

    class ByteBitOperationsSetBit {

        @Test
        fun ByteBitOperationsSetBit() {
            ByteBitOperations(0).setBit(0)
        }


        @Test
        fun BitIndex() {
            ByteBitOperations(0).setBit((-1))
            ByteBitOperations(0).setBit(0)
            ByteBitOperations(0).setBit(1)
            ByteBitOperations(0).setBit((-50))
            ByteBitOperations(0).setBit(42)
        }

    }
}