package csense.kotlin.units

//TODO use inline classes when stable
/**
 * A list of measurements of binary sizes.
 * 8 bits are 1 byte,
 * 1024 bytes are a kilobyte
 * and so on
 * see for example
 * https://en.wikipedia.org/wiki/Binary_prefix
 *
 * this does not handle overflow; so all kinds of "downards" walk can result in overflow.
 * eg if you go from MegaBytes to kilobytes.
 *
 * or if you go from byte to bit, there is a potential overflow as well
 *
 *
 * @property value Long
 * @constructor
 */
sealed class BinaryBitSizes(val value: Long) {
    class Bit(bits: Long) : BinaryBitSizes(bits)
    class Byte(bytes: Long) : BinaryBitSizes(bytes)
    //and then each listed below in both forms
    /*bytes*/
    class KiloByte(kiloBytes: Long) : BinaryBitSizes(kiloBytes)

    class MegaByte(megaBytes: Long) : BinaryBitSizes(megaBytes)
    class GigaByte(gigaBytes: Long) : BinaryBitSizes(gigaBytes)
    class TeraByte(teraBytes: Long) : BinaryBitSizes(teraBytes)
    class PetaByte(petaBytes: Long) : BinaryBitSizes(petaBytes)


    /*bits*/
    class KiloBit(kiloBits: Long) : BinaryBitSizes(kiloBits)

    class MegaBit(megaBits: Long) : BinaryBitSizes(megaBits)
    class GigaBit(gigaBits: Long) : BinaryBitSizes(gigaBits)
    class TeraBit(teraBits: Long) : BinaryBitSizes(teraBits)
    class PetaBit(petaBits: Long) : BinaryBitSizes(petaBits)
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
fun BinaryBitSizes.Bit.toBytes(): BinaryBitSizes.Byte = BinaryBitSizes.Byte(value / bitByteFactor)

fun BinaryBitSizes.Bit.toKiloBits(): BinaryBitSizes.KiloBit = BinaryBitSizes.KiloBit(value / prefixFactor)

//bytes
fun BinaryBitSizes.Byte.toBits(): BinaryBitSizes.Bit = BinaryBitSizes.Bit(value * bitByteFactor)

fun BinaryBitSizes.Byte.toKiloBytes(): BinaryBitSizes.KiloByte = BinaryBitSizes.KiloByte(value / prefixFactor)


//kilobytes
fun BinaryBitSizes.KiloByte.toKiloBits(): BinaryBitSizes.KiloBit = BinaryBitSizes.KiloBit(value * bitByteFactor)

fun BinaryBitSizes.KiloByte.toBytes(): BinaryBitSizes.Byte = BinaryBitSizes.Byte(value * prefixFactor)
fun BinaryBitSizes.KiloByte.toMegaBytes(): BinaryBitSizes.MegaByte = BinaryBitSizes.MegaByte(value / prefixFactor)


//kilobits
fun BinaryBitSizes.KiloBit.toKiloBytes(): BinaryBitSizes.KiloByte = BinaryBitSizes.KiloByte(value / prefixFactor)

fun BinaryBitSizes.KiloBit.toBits(): BinaryBitSizes.Bit = BinaryBitSizes.Bit(value * prefixFactor)
fun BinaryBitSizes.KiloBit.toMegaBits(): BinaryBitSizes.MegaBit = BinaryBitSizes.MegaBit(value / prefixFactor)

// megabytes
fun BinaryBitSizes.MegaByte.toKiloBytes(): BinaryBitSizes.KiloByte = BinaryBitSizes.KiloByte(value * prefixFactor)

fun BinaryBitSizes.MegaByte.toGigaBytes(): BinaryBitSizes.GigaByte = BinaryBitSizes.GigaByte(value / prefixFactor)
fun BinaryBitSizes.MegaByte.toMegaBit(): BinaryBitSizes.MegaBit = BinaryBitSizes.MegaBit(value * bitByteFactor)

// megabits
fun BinaryBitSizes.MegaBit.toMegaByte(): BinaryBitSizes.MegaByte = BinaryBitSizes.MegaByte(value / bitByteFactor)

fun BinaryBitSizes.MegaBit.toKiloBits(): BinaryBitSizes.KiloBit = BinaryBitSizes.KiloBit(value * prefixFactor)
fun BinaryBitSizes.MegaBit.toGigaBits(): BinaryBitSizes.GigaBit = BinaryBitSizes.GigaBit(value / prefixFactor)


// gigaBytes
fun BinaryBitSizes.GigaByte.toMegaBytes(): BinaryBitSizes.MegaByte = BinaryBitSizes.MegaByte(value * prefixFactor)

fun BinaryBitSizes.GigaByte.toGigaBits(): BinaryBitSizes.GigaBit = BinaryBitSizes.GigaBit(value * bitByteFactor)
fun BinaryBitSizes.GigaByte.toTeraBytes(): BinaryBitSizes.TeraByte = BinaryBitSizes.TeraByte(value / prefixFactor)

// gigabits
fun BinaryBitSizes.GigaBit.toMegaBits(): BinaryBitSizes.MegaBit = BinaryBitSizes.MegaBit(value * prefixFactor)

fun BinaryBitSizes.GigaBit.toGigaBytes(): BinaryBitSizes.GigaByte = BinaryBitSizes.GigaByte(value / bitByteFactor)
fun BinaryBitSizes.GigaBit.toTeraBits(): BinaryBitSizes.TeraBit = BinaryBitSizes.TeraBit(value / prefixFactor)


// terabytes
fun BinaryBitSizes.TeraByte.toPetaBytes(): BinaryBitSizes.PetaByte = BinaryBitSizes.PetaByte(value / prefixFactor)

fun BinaryBitSizes.TeraByte.toTeraBits(): BinaryBitSizes.TeraBit = BinaryBitSizes.TeraBit(value * bitByteFactor)
fun BinaryBitSizes.TeraByte.toGigaBytes(): BinaryBitSizes.GigaByte = BinaryBitSizes.GigaByte(value * prefixFactor)

// terabits
fun BinaryBitSizes.TeraBit.toPetaBits(): BinaryBitSizes.PetaBit = BinaryBitSizes.PetaBit(value / prefixFactor)

fun BinaryBitSizes.TeraBit.toTeraBytes(): BinaryBitSizes.TeraByte = BinaryBitSizes.TeraByte(value / bitByteFactor)
fun BinaryBitSizes.TeraBit.toGigaBits(): BinaryBitSizes.GigaBit = BinaryBitSizes.GigaBit(value * prefixFactor)

// petabytes
fun BinaryBitSizes.PetaByte.toPetaBits(): BinaryBitSizes.PetaBit = BinaryBitSizes.PetaBit(value * bitByteFactor)

fun BinaryBitSizes.PetaByte.toTeraBytes(): BinaryBitSizes.TeraByte = BinaryBitSizes.TeraByte(value * prefixFactor)

// petabits
fun BinaryBitSizes.PetaBit.toPetaBytes(): BinaryBitSizes.PetaByte = BinaryBitSizes.PetaByte(value / bitByteFactor)

fun BinaryBitSizes.PetaBit.toTeraBits(): BinaryBitSizes.TeraBit = BinaryBitSizes.TeraBit(value * prefixFactor)



