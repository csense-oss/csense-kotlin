@file:Suppress("unused")

package csense.kotlin.units

import csense.kotlin.annotations.numbers.*

//TODO use inline classes when able
/**
 * Base 2 implementation of the math between bit  / byte and various sizes
 * A list of measurements of binary sizes.
 * 8 bits are 1 byte,
 * 1024 bytes are a kilobyte
 * and so on
 * see for example
 * https://en.wikipedia.org/wiki/Binary_prefix
 *
 * this does not handle overflow; so all kinds of "downwards" walk can result in overflow.
 * eg if you go from MegaBytes to kilobytes.
 *
 * or if you go from byte to bit, there is a potential overflow as well
 *
 *
 * @property value [Long]
 * @constructor
 */
public sealed class BinaryBitSizes(@LongLimit(from = 0) public val value: Long) {
    public class Bit(@LongLimit(from = 0) bits: Long) : BinaryBitSizes(bits)
    public class Byte(@LongLimit(from = 0) bytes: Long) : BinaryBitSizes(bytes)

    //and then each listed below in both forms
    /*bytes*/
    public class KiloByte(@LongLimit(from = 0) kiloBytes: Long) : BinaryBitSizes(kiloBytes)

    public class MegaByte(@LongLimit(from = 0) megaBytes: Long) : BinaryBitSizes(megaBytes)
    public class GigaByte(@LongLimit(from = 0) gigaBytes: Long) : BinaryBitSizes(gigaBytes)
    public class TeraByte(@LongLimit(from = 0) teraBytes: Long) : BinaryBitSizes(teraBytes)
    public class PetaByte(@LongLimit(from = 0) petaBytes: Long) : BinaryBitSizes(petaBytes)


    /*bits*/
    public class KiloBit(@LongLimit(from = 0) kiloBits: Long) : BinaryBitSizes(kiloBits)

    public class MegaBit(@LongLimit(from = 0) megaBits: Long) : BinaryBitSizes(megaBits)
    public class GigaBit(@LongLimit(from = 0) gigaBits: Long) : BinaryBitSizes(gigaBits)
    public class TeraBit(@LongLimit(from = 0) teraBits: Long) : BinaryBitSizes(teraBits)
    public class PetaBit(@LongLimit(from = 0) petaBits: Long) : BinaryBitSizes(petaBits)
}

/**
 * The factor between bits and bytes ( 8 bits = 1 byte)
 */
private const val bitByteFactor = 8

/**
 * The prefix multiplicity factor
 */
private const val prefixFactor = 1024


//bits
public fun BinaryBitSizes.Bit.toBytes(): BinaryBitSizes.Byte = BinaryBitSizes.Byte(value / bitByteFactor)

public fun BinaryBitSizes.Bit.toKiloBits(): BinaryBitSizes.KiloBit = BinaryBitSizes.KiloBit(value / prefixFactor)

//bytes
public fun BinaryBitSizes.Byte.toBits(): BinaryBitSizes.Bit = BinaryBitSizes.Bit(value * bitByteFactor)

public fun BinaryBitSizes.Byte.toKiloBytes(): BinaryBitSizes.KiloByte = BinaryBitSizes.KiloByte(value / prefixFactor)


//kilobytes
public fun BinaryBitSizes.KiloByte.toKiloBits(): BinaryBitSizes.KiloBit = BinaryBitSizes.KiloBit(value * bitByteFactor)

public fun BinaryBitSizes.KiloByte.toBytes(): BinaryBitSizes.Byte = BinaryBitSizes.Byte(value * prefixFactor)
public fun BinaryBitSizes.KiloByte.toMegaBytes(): BinaryBitSizes.MegaByte =
    BinaryBitSizes.MegaByte(value / prefixFactor)


//kilobits
public fun BinaryBitSizes.KiloBit.toKiloBytes(): BinaryBitSizes.KiloByte = BinaryBitSizes.KiloByte(value / bitByteFactor)

public fun BinaryBitSizes.KiloBit.toBits(): BinaryBitSizes.Bit = BinaryBitSizes.Bit(value * prefixFactor)
public fun BinaryBitSizes.KiloBit.toMegaBits(): BinaryBitSizes.MegaBit = BinaryBitSizes.MegaBit(value / prefixFactor)

// megabytes
public fun BinaryBitSizes.MegaByte.toKiloBytes(): BinaryBitSizes.KiloByte =
    BinaryBitSizes.KiloByte(value * prefixFactor)

public fun BinaryBitSizes.MegaByte.toGigaBytes(): BinaryBitSizes.GigaByte =
    BinaryBitSizes.GigaByte(value / prefixFactor)

public fun BinaryBitSizes.MegaByte.toMegaBit(): BinaryBitSizes.MegaBit = BinaryBitSizes.MegaBit(value * bitByteFactor)

// megabits
public fun BinaryBitSizes.MegaBit.toMegaByte(): BinaryBitSizes.MegaByte = BinaryBitSizes.MegaByte(value / bitByteFactor)

public fun BinaryBitSizes.MegaBit.toKiloBits(): BinaryBitSizes.KiloBit = BinaryBitSizes.KiloBit(value * prefixFactor)
public fun BinaryBitSizes.MegaBit.toGigaBits(): BinaryBitSizes.GigaBit = BinaryBitSizes.GigaBit(value / prefixFactor)


// gigaBytes
public fun BinaryBitSizes.GigaByte.toMegaBytes(): BinaryBitSizes.MegaByte =
    BinaryBitSizes.MegaByte(value * prefixFactor)

public fun BinaryBitSizes.GigaByte.toGigaBits(): BinaryBitSizes.GigaBit = BinaryBitSizes.GigaBit(value * bitByteFactor)
public fun BinaryBitSizes.GigaByte.toTeraBytes(): BinaryBitSizes.TeraByte =
    BinaryBitSizes.TeraByte(value / prefixFactor)

// gigabits
public fun BinaryBitSizes.GigaBit.toMegaBits(): BinaryBitSizes.MegaBit = BinaryBitSizes.MegaBit(value * prefixFactor)

public fun BinaryBitSizes.GigaBit.toGigaBytes(): BinaryBitSizes.GigaByte =
    BinaryBitSizes.GigaByte(value / bitByteFactor)

public fun BinaryBitSizes.GigaBit.toTeraBits(): BinaryBitSizes.TeraBit = BinaryBitSizes.TeraBit(value / prefixFactor)


// terabytes
public fun BinaryBitSizes.TeraByte.toPetaBytes(): BinaryBitSizes.PetaByte =
    BinaryBitSizes.PetaByte(value / prefixFactor)

public fun BinaryBitSizes.TeraByte.toTeraBits(): BinaryBitSizes.TeraBit = BinaryBitSizes.TeraBit(value * bitByteFactor)
public fun BinaryBitSizes.TeraByte.toGigaBytes(): BinaryBitSizes.GigaByte =
    BinaryBitSizes.GigaByte(value * prefixFactor)

// terabits
public fun BinaryBitSizes.TeraBit.toPetaBits(): BinaryBitSizes.PetaBit = BinaryBitSizes.PetaBit(value / prefixFactor)

public fun BinaryBitSizes.TeraBit.toTeraBytes(): BinaryBitSizes.TeraByte =
    BinaryBitSizes.TeraByte(value / bitByteFactor)

public fun BinaryBitSizes.TeraBit.toGigaBits(): BinaryBitSizes.GigaBit = BinaryBitSizes.GigaBit(value * prefixFactor)

// petabytes
public fun BinaryBitSizes.PetaByte.toPetaBits(): BinaryBitSizes.PetaBit = BinaryBitSizes.PetaBit(value * bitByteFactor)

public fun BinaryBitSizes.PetaByte.toTeraBytes(): BinaryBitSizes.TeraByte =
    BinaryBitSizes.TeraByte(value * prefixFactor)

// petabits
public fun BinaryBitSizes.PetaBit.toPetaBytes(): BinaryBitSizes.PetaByte =
    BinaryBitSizes.PetaByte(value / bitByteFactor)

public fun BinaryBitSizes.PetaBit.toTeraBits(): BinaryBitSizes.TeraBit = BinaryBitSizes.TeraBit(value * prefixFactor)



