@file:Suppress("LocalVariableName", "NOTHING_TO_INLINE")

package csense.kotlin.units

import csense.kotlin.tests.assertions.*
import kotlin.test.*

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
        BinaryBitSizes.PetaBit(0).toTeraBits().assert(0)
        BinaryBitSizes.PetaBit(1).toTeraBits().assert(1024)
    }

    @Test
    fun binaryBitSizesPetaBitToPetaBytes() {
        BinaryBitSizes.PetaBit(0).toPetaBytes().assert(0)
        BinaryBitSizes.PetaBit(1).toPetaBytes().assert(0)
        BinaryBitSizes.PetaBit(8).toPetaBytes().assert(1)
    }

    @Test
    fun binaryBitSizesPetaByteToTeraBytes() {
        BinaryBitSizes.PetaByte(1).toTeraBytes().assert(1024)
        BinaryBitSizes.PetaByte(0).toTeraBytes().assert(0)
        BinaryBitSizes.PetaByte(2).toTeraBytes().assert(2048)
    }

    @Test
    fun binaryBitSizesPetaByteToPetaBits() {
        BinaryBitSizes.PetaByte(1).toPetaBits().assert(8)
        BinaryBitSizes.PetaByte(0).toPetaBits().assert(0)
        BinaryBitSizes.PetaByte(2).toPetaBits().assert(16)
    }

    @Test
    fun binaryBitSizesTeraBitToGigaBits() {
        BinaryBitSizes.TeraBit(1).toGigaBits().assert(1024)
        BinaryBitSizes.TeraBit(0).toGigaBits().assert(0)
        BinaryBitSizes.TeraBit(8).toGigaBits().assert(8192)
    }

    @Test
    fun binaryBitSizesTeraBitToTeraBytes() {
        BinaryBitSizes.TeraBit(0).toTeraBytes().assert(0)
        BinaryBitSizes.TeraBit(1).toTeraBytes().assert(0)
        BinaryBitSizes.TeraBit(8).toTeraBytes().assert(1)
    }

    @Test
    fun binaryBitSizesTeraBitToPetaBits() {
        BinaryBitSizes.TeraBit(0).toPetaBits().assert(0)
        BinaryBitSizes.TeraBit(1).toPetaBits().assert(0)
        BinaryBitSizes.TeraBit(1024).toPetaBits().assert(1)
    }

    @Test
    fun binaryBitSizesTeraByteToGigaBytes() {
        BinaryBitSizes.PetaByte(1).toTeraBytes().assert(1024)
        BinaryBitSizes.PetaByte(0).toTeraBytes().assert(0)
        BinaryBitSizes.PetaByte(2).toTeraBytes().assert(2048)

    }

    @Test
    fun binaryBitSizesTeraByteToTeraBits() {
        BinaryBitSizes.TeraByte(0).toTeraBits().assert(0)
        BinaryBitSizes.TeraByte(1).toTeraBits().assert(8)
    }

    @Test
    fun binaryBitSizesTeraByteToPetaBytes() {
        BinaryBitSizes.TeraByte(0).toPetaBytes().assert(0)
        BinaryBitSizes.TeraByte(1).toPetaBytes().assert(0)
        BinaryBitSizes.TeraByte(1024).toPetaBytes().assert(1)
    }

    @Test
    fun binaryBitSizesGigaBitToTeraBits() {
        BinaryBitSizes.GigaBit(0).toTeraBits().assert(0)
        BinaryBitSizes.GigaBit(1).toTeraBits().assert(0)
        BinaryBitSizes.GigaBit(1024).toTeraBits().assert(1)
    }

    @Test
    fun binaryBitSizesGigaBitToGigaBytes() {
        BinaryBitSizes.GigaBit(0).toGigaBytes().assert(0)
        BinaryBitSizes.GigaBit(1).toGigaBytes().assert(0)
        BinaryBitSizes.GigaBit(8).toGigaBytes().assert(1)
    }

    @Test
    fun binaryBitSizesGigaBitToMegaBits() {
        BinaryBitSizes.GigaBit(1).toMegaBits().assert(1024)
        BinaryBitSizes.GigaBit(0).toMegaBits().assert(0)
        BinaryBitSizes.GigaBit(2).toMegaBits().assert(2048)

    }

    @Test
    fun binaryBitSizesGigaByteToTeraBytes() {
        BinaryBitSizes.GigaByte(0).toTeraBytes().assert(0)
        BinaryBitSizes.GigaByte(1).toTeraBytes().assert(0)
        BinaryBitSizes.GigaByte(1024).toTeraBytes().assert(1)
    }

    @Test
    fun binaryBitSizesGigaByteToGigaBits() {
        BinaryBitSizes.GigaByte(0).toGigaBits().assert(0)
        BinaryBitSizes.GigaByte(1).toGigaBits().assert(8)
        BinaryBitSizes.GigaByte(2).toGigaBits().assert(16)
    }

    @Test
    fun binaryBitSizesGigaByteToMegaBytes() {
        BinaryBitSizes.GigaByte(1).toMegaBytes().assert(1024)
        BinaryBitSizes.GigaByte(0).toMegaBytes().assert(0)
        BinaryBitSizes.GigaByte(2).toMegaBytes().assert(2048)
    }

    @Test
    fun binaryBitSizesMegaBitToGigaBits() {
        BinaryBitSizes.MegaBit(0).toGigaBits().assert(0)
        BinaryBitSizes.MegaBit(1).toGigaBits().assert(0)
        BinaryBitSizes.MegaBit(1024).toGigaBits().assert(1)
    }

    @Test
    fun binaryBitSizesMegaBitToKiloBits() {
        BinaryBitSizes.MegaBit(0).toKiloBits().assert(0)
        BinaryBitSizes.MegaBit(1).toKiloBits().assert(1024)
        BinaryBitSizes.MegaBit(2).toKiloBits().assert(2048)
    }

    @Test
    fun binaryBitSizesMegaBitToMegaByte() {
        BinaryBitSizes.MegaBit(2).toMegaByte().assert(0)
        BinaryBitSizes.MegaBit(0).toMegaByte().assert(0)
        BinaryBitSizes.MegaBit(8).toMegaByte().assert(1)
    }

    @Test
    fun binaryBitSizesMegaByteToMegaBit() {
        BinaryBitSizes.MegaByte(0).toMegaBit().assert(0)
        BinaryBitSizes.MegaByte(1).toMegaBit().assert(8)
        BinaryBitSizes.MegaByte(2).toMegaBit().assert(16)
    }

    @Test
    fun binaryBitSizesMegaByteToGigaBytes() {
        BinaryBitSizes.MegaByte(0).toGigaBytes().assert(0)
        BinaryBitSizes.MegaByte(1).toGigaBytes().assert(0)
        BinaryBitSizes.MegaByte(1024).toGigaBytes().assert(1)
    }

    @Test
    fun binaryBitSizesMegaByteToKiloBytes() {
        BinaryBitSizes.MegaByte(0).toKiloBytes().assert(0)
        BinaryBitSizes.MegaByte(1).toKiloBytes().assert(1024)
        BinaryBitSizes.MegaByte(2).toKiloBytes().assert(2048)
    }

    @Test
    fun binaryBitSizesKiloBitToMegaBits() {
        BinaryBitSizes.KiloBit(0).toMegaBits().assert(0)
        BinaryBitSizes.KiloBit(1).toMegaBits().assert(0)
        BinaryBitSizes.KiloBit(1024).toMegaBits().assert(1)
    }

    @Test
    fun binaryBitSizesKiloBitToBits() {
        BinaryBitSizes.KiloBit(0).toMegaBits().assert(0)
        BinaryBitSizes.KiloBit(1).toMegaBits().assert(0)
        BinaryBitSizes.KiloBit(1024).toMegaBits().assert(1)

    }

    @Test
    fun binaryBitSizesKiloBitToKiloBytes() {
        BinaryBitSizes.KiloBit(0).toKiloBytes().assert(0)
        BinaryBitSizes.KiloBit(1).toKiloBytes().assert(0)
        BinaryBitSizes.KiloBit(8).toKiloBytes().assert(1)
    }

    @Test
    fun binaryBitSizesKiloByteToMegaBytes() {
        BinaryBitSizes.KiloByte(0).toMegaBytes().assert(0)
        BinaryBitSizes.KiloByte(1).toMegaBytes().assert(0)
        BinaryBitSizes.KiloByte(1024).toMegaBytes().assert(1)
    }

    @Test
    fun binaryBitSizesKiloByteToBytes() {
        BinaryBitSizes.KiloByte(0).toBytes().assert(0)
        BinaryBitSizes.KiloByte(1).toBytes().assert(1024)
        BinaryBitSizes.KiloByte(2).toBytes().assert(2048)
    }

    @Test
    fun binaryBitSizesKiloByteToKiloBits() {
        BinaryBitSizes.KiloByte(0).toKiloBits().assert(0)
        BinaryBitSizes.KiloByte(1).toKiloBits().assert(8)
        BinaryBitSizes.KiloByte(2).toKiloBits().assert(16)
    }

    @Test
    fun binaryBitSizesByteToKiloBytes() {
        BinaryBitSizes.Byte(0).toKiloBytes().assert(0)
        BinaryBitSizes.Byte(1).toKiloBytes().assert(0)
        BinaryBitSizes.Byte(1024).toKiloBytes().assert(1)
    }

    @Test
    fun binaryBitSizesByteToBits() {
        BinaryBitSizes.Byte(0).toBits().assert(0)
        BinaryBitSizes.Byte(1).toBits().assert(8)
        BinaryBitSizes.Byte(2).toBits().assert(16)
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