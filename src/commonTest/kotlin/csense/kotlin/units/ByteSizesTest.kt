@file:Suppress("LocalVariableName", "NOTHING_TO_INLINE")

package csense.kotlin.units

import csense.kotlin.tests.assertions.assert
import kotlin.test.Test

class ByteSizesTest {
    
    //bit to byte
    @Test
    fun toBytes() {
        val zero = BinaryBitSizes.Bit(0)
        assertBitToByte(zero, zero.toBytes())
        
        val _8bits = BinaryBitSizes.Bit(8)
        assertBitToByte(_8bits, _8bits.toBytes())
        
        val _16bits = BinaryBitSizes.Bit(16)
        assertBitToByte(_16bits, _16bits.toBytes())
        
        val _128bits = BinaryBitSizes.Bit(128)
        assertBitToByte(_128bits, _128bits.toBytes())
    }
    
    //bytes to kilo bytes
    @Test
    fun toKiloBits() {
        BinaryBitSizes.Bit(0).toKiloBits().assert(0)
        BinaryBitSizes.Bit(1024).toKiloBits().assert(1)
        BinaryBitSizes.Bit(2048).toKiloBits().assert(2)
        BinaryBitSizes.Bit(4096).toKiloBits().assert(4)
    }
    
    // bytes to bits
    @Test
    fun toBits() {
        val zero = BinaryBitSizes.Byte(0)
        val one = BinaryBitSizes.Byte(1)
        val _16 = BinaryBitSizes.Byte(16)
        assertByteToBit(zero, zero.toBits())
        assertByteToBit(one, one.toBits())
        assertByteToBit(_16, _16.toBits())
    }
    
    //bytes to kilo bytes
    @Test
    fun toKiloBytes() {
        BinaryBitSizes.Byte(0).toKiloBytes().assert(0)
        BinaryBitSizes.Byte(1024).toKiloBytes().assert(1)
        BinaryBitSizes.Byte(2048).toKiloBytes().assert(2)
        BinaryBitSizes.Byte(4096).toKiloBytes().assert(4)
    }
    
    // kilobyte to kilobits
    @Test
    fun toKiloBits1() {
        
        val zero = BinaryBitSizes.KiloByte(0)
        val one = BinaryBitSizes.KiloByte(1)
        val _32 = BinaryBitSizes.KiloByte(32)
        assertByteToBit(zero, zero.toKiloBits())
        assertByteToBit(one, one.toKiloBits())
        assertByteToBit(_32, _32.toKiloBits())
    }
    
    //kilobytes to bytes
    @Test
    fun toBytes1() {
        BinaryBitSizes.KiloByte(0).toBytes().assert(0)
        BinaryBitSizes.KiloByte(1).toBytes().assert(1024)
        BinaryBitSizes.KiloByte(2).toBytes().assert(2048)
        BinaryBitSizes.KiloByte(4).toBytes().assert(4096)
    }
    
    // kilobyte to megabytes
    @Test
    fun toMegaBytes() {
        BinaryBitSizes.KiloByte(0).toMegaBytes().assert(0)
        BinaryBitSizes.KiloByte(900).toMegaBytes().assert(0) //rounds down
        BinaryBitSizes.KiloByte(2048).toMegaBytes().assert(2)
        BinaryBitSizes.KiloByte(4096).toMegaBytes().assert(4)
    }
    
    //kilobits to kilobytes
    @Test
    fun toKiloBytes1() {
        val zero = BinaryBitSizes.KiloBit(0)
        val _16 = BinaryBitSizes.KiloBit(0)
        val _1024 = BinaryBitSizes.KiloBit(0)
        assertBitToByte(zero, zero.toKiloBytes())
        assertBitToByte(_16, _16.toKiloBytes())
        assertBitToByte(_1024, _1024.toKiloBytes())
    }
    
    // kilobits to bits
    @Test
    fun toBits1() {
        BinaryBitSizes.KiloBit(0).toBits().assert(0)
        BinaryBitSizes.KiloBit(1).toBits().assert(1024)
        BinaryBitSizes.KiloBit(10).toBits().assert(10240)
    }
    
    
    private fun assertBitToByte(inBytes: BinaryBitSizes, inBits: BinaryBitSizes) {
        inBytes.value.assert(inBits.value * 8)
    }
    
    private fun assertByteToBit(inBits: BinaryBitSizes, inBytes: BinaryBitSizes) {
        inBits.value.assert(inBytes.value / 8)
    }
    
    
    @Test
    fun binaryBitSizesPetaBitToTeraBits() {
        //TODO make me.
        
    }
    
    @Test
    fun binaryBitSizesPetaBitToPetaBytes() {
        //TODO make me.
        
    }
    
    @Test
    fun binaryBitSizesPetaByteToTeraBytes() {
        //TODO make me.
        
    }
    
    @Test
    fun binaryBitSizesPetaByteToPetaBits() {
        //TODO make me.
        
    }
    
    @Test
    fun binaryBitSizesTeraBitToGigaBits() {
        //TODO make me.
        
    }
    
    @Test
    fun binaryBitSizesTeraBitToTeraBytes() {
        //TODO make me.
        
    }
    
    @Test
    fun binaryBitSizesTeraBitToPetaBits() {
        //TODO make me.
        
    }
    
    @Test
    fun binaryBitSizesTeraByteToGigaBytes() {
        //TODO make me.
        
    }
    
    @Test
    fun binaryBitSizesTeraByteToTeraBits() {
        //TODO make me.
        
    }
    
    @Test
    fun binaryBitSizesTeraByteToPetaBytes() {
        //TODO make me.
        
    }
    
    @Test
    fun binaryBitSizesGigaBitToTeraBits() {
        //TODO make me.
        
    }
    
    @Test
    fun binaryBitSizesGigaBitToGigaBytes() {
        //TODO make me.
        
    }
    
    @Test
    fun binaryBitSizesGigaBitToMegaBits() {
        //TODO make me.
        
    }
    
    @Test
    fun binaryBitSizesGigaByteToTeraBytes() {
        //TODO make me.
        
    }
    
    @Test
    fun binaryBitSizesGigaByteToGigaBits() {
        //TODO make me.
        
    }
    
    @Test
    fun binaryBitSizesGigaByteToMegaBytes() {
        //TODO make me.
        
    }
    
    @Test
    fun binaryBitSizesMegaBitToGigaBits() {
        //TODO make me.
        
    }
    
    @Test
    fun binaryBitSizesMegaBitToKiloBits() {
        //TODO make me.
        
    }
    
    @Test
    fun binaryBitSizesMegaBitToMegaByte() {
        //TODO make me.
        
    }
    
    @Test
    fun binaryBitSizesMegaByteToMegaBit() {
        //TODO make me.
        
    }
    
    @Test
    fun binaryBitSizesMegaByteToGigaBytes() {
        //TODO make me.
        
    }
    
    @Test
    fun binaryBitSizesMegaByteToKiloBytes() {
        //TODO make me.
        
    }
    
    @Test
    fun binaryBitSizesKiloBitToMegaBits() {
        //TODO make me.
        
    }
    
    @Test
    fun binaryBitSizesKiloBitToBits() {
        //TODO make me.
        
    }
    
    @Test
    fun binaryBitSizesKiloBitToKiloBytes() {
        //TODO make me.
        
    }
    
    @Test
    fun binaryBitSizesKiloByteToMegaBytes() {
        //TODO make me.
        
    }
    
    @Test
    fun binaryBitSizesKiloByteToBytes() {
        //TODO make me.
        
    }
    
    @Test
    fun binaryBitSizesKiloByteToKiloBits() {
        //TODO make me.
        
    }
    
    @Test
    fun binaryBitSizesByteToKiloBytes() {
        //TODO make me.
        
    }
    
    @Test
    fun binaryBitSizesByteToBits() {
        //TODO make me.
        
    }
    
    @Test
    fun binaryBitSizesBitToKiloBits() {
        BinaryBitSizes.Bit(0).toKiloBits().assert(0)
        BinaryBitSizes.Bit(7).toKiloBits().assert(0)
        BinaryBitSizes.Bit(1024).toKiloBits().assert(1)
        BinaryBitSizes.Bit(1025).toKiloBits().assert(1)
        BinaryBitSizes.Bit(2048).toKiloBits().assert(2)
        
    }
    
    @Test
    fun binaryBitSizesBitToBytes() {
        BinaryBitSizes.Bit(0).toBytes().assert(0)
        BinaryBitSizes.Bit(7).toBytes().assert(0)
        BinaryBitSizes.Bit(8).toBytes().assert(1)
        BinaryBitSizes.Bit(9).toBytes().assert(1)
        BinaryBitSizes.Bit(16).toBytes().assert(2)
        
    }
}

inline fun BinaryBitSizes.assert(expected: Long) = value.assert(expected)