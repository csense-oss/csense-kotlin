package csense.kotlin.units

import csense.kotlin.test.assertions.*
import kotlin.test.*

class BytesKtTest {

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

    @Ignore
    //kilobits to megabits
    @Test
    fun toMegaBits() {

    }

    @Ignore
    // megabyte to kilobytes
    @Test
    fun toKiloBytes2() {
    }

    @Ignore
    @Test
    fun toGigaBytes() {
    }

    @Ignore
    @Test
    fun toMegaBit() {
    }

    @Ignore
    @Test
    fun toMegaByte() {
    }

    @Ignore
    @Test
    fun toKiloBits2() {
    }

    @Ignore
    @Test
    fun toGigaBits() {
    }

    @Ignore
    @Test
    fun toMegaBytes1() {
    }

    @Ignore
    @Test
    fun toGigaBits1() {
    }

    @Ignore
    @Test
    fun toTeraBytes() {
    }

    @Ignore
    @Test
    fun toMegaBits1() {
    }

    @Ignore
    @Test
    fun toGigaBytes1() {
    }

    @Ignore
    @Test
    fun toTeraBits() {
    }

    @Ignore
    @Test
    fun toPetaBytes() {
    }

    @Ignore
    @Test
    fun toTeraBits1() {
    }

    @Ignore
    @Test
    fun toGigaBytes2() {
    }

    @Ignore
    @Test
    fun toPetaBits() {
    }

    @Ignore
    @Test
    fun toTeraBytes1() {
    }

    @Ignore
    @Test
    fun toGigaBits2() {
    }

    @Ignore
    @Test
    fun toPetaBits1() {
    }

    @Ignore
    @Test
    fun toTeraBytes2() {
    }

    @Ignore
    @Test
    fun toPetaBytes1() {
    }

    @Ignore
    @Test
    fun toTeraBits2() {
    }

    private fun assertBitToByte(inBytes: BinaryBitSizes, inBits: BinaryBitSizes) {
        inBytes.value.assert(inBits.value * 8)
    }

    private fun assertByteToBit(inBits: BinaryBitSizes, inBytes: BinaryBitSizes) {
        inBits.value.assert(inBytes.value / 8)
    }

    private fun BinaryBitSizes.assert(expected: Long) {
        value.assert(expected)
    }
}