package csense.kotlin.crypto

import csense.kotlin.extensions.collections.array.typed.byte.*
import csense.kotlin.extensions.primitives.operations.*
import kotlin.jvm.*
import kotlin.random.*

@JvmInline
public value class UUID4(
    public val uuid: String
) {
    public companion object
}

/**
 *
 * Creates a random UUID version 4 (random).
 *
 * see https://www.ietf.org/rfc/rfc4122.txt
 * @param random [Random]
 * @param shouldBeUppercase [Boolean]
 * @return [String]
 */
public fun UUID4.Companion.create(
    random: Random = Random,
    shouldBeUppercase: Boolean = true,
    shouldHaveDashes: Boolean = true
): String {
    val randomData: ByteArray = random.nextBytes(16)
    randomData.setClockAndReservedBits()
    randomData.setVersion4UuidBits()
    val hexString: String = randomData.toHexString(appendHexPrefix = false, shouldBeUppercase = shouldBeUppercase)

    return if (shouldHaveDashes) {
        hexString.insertDashes()
    } else {
        hexString
    }
}

private fun ByteArray.setClockAndReservedBits() {
    val clockAndReservedIndex = 8
    /*
     Set the two most significant bits (bits 6 and 7) of the
     clock_seq_hi_and_reserved to zero and one, respectively.
     clk_seq_hi_res is at index 7 (see table)
     */
    this[clockAndReservedIndex] =
        this[clockAndReservedIndex]
            .bits.zeroBit(bitIndex = 6)
            .bits.setBit(bitIndex = 7)
}

private fun ByteArray.setVersion4UuidBits() {
    val timeHighAndVersionIndex = 6
    this[timeHighAndVersionIndex] = this[timeHighAndVersionIndex]
        .bits
        .updateUpperNibble(Version4UpperNibble)
}

private fun String.insertDashes(): String {
    val hexStringBuilder: StringBuilder = StringBuilder(this)
        .insert(index = 8, value = '-')
        .insert(index = 12, value = '-')
        .insert(index = 16, value = '-')
        .insert(index = 20, value = '-')
    return hexStringBuilder.toString()
}

private const val Version4UpperNibble: Byte = 0b0100

/* 4.4.  Algorithms for Creating a UUID from Truly Random or
Pseudo-Random Numbers

        The version 4 UUID is meant for generating UUIDs from truly-random or
        pseudo-random numbers.

The algorithm is as follows:

o  Set the two most significant bits (bits 6 and 7) of the
clock_seq_hi_and_reserved to zero and one, respectively.

o  Set the four most significant bits (bits 12 through 15) of the
time_hi_and_version field to the 4-bit version number from
        Section 4.1.3.

o  Set all the other bits to randomly (or pseudo-randomly) chosen
        values.*/
/*
UUID FORMAT:  https://tools.ietf.org/html/rfc4122#section-4.1.2 (see their sizes there as well)

start  byte     bit        0                   1                   2                   3
                            0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
       0        0          +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
                           |                          time_low                             |
       4        32         +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
                           |       time_mid                |         time_hi_and_version   |
       8        64         +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
                           |clk_seq_hi_res |  clk_seq_low  |         node (0-1)            |
      12        92         +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
                           |                         node (2-5)                            |
                           +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+  128 bit  16 byte


*/