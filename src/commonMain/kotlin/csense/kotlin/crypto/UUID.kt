package csense.kotlin.crypto

import csense.kotlin.extensions.collections.array.*
import csense.kotlin.specificExtensions.string.*
import kotlin.experimental.*
import kotlin.random.*

/**
 * An implementation of a UUID version 4
 */
public object UUID4 {

    private const val clk_seq_hi_resIndex = 8
    private const val time_hi_and_versionIndex = 6

    /**
     *
     * Creates a random UUID version 4 (random).
     *
     * see https://www.ietf.org/rfc/rfc4122.txt
     * @param random [Random]
     * @param shouldBeUppercase [Boolean]
     * @return [String]
     */
    public fun create(
        random: Random = Random,
        shouldBeUppercase: Boolean = true,
        shouldHaveDashes: Boolean = true
    ): String {
        //create 16 bit random
        val randomData = random.nextBytes(16)

        //then manipulate it as follows in RFC 4122 section 4.4
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

        /**
         * Make sure we only work on it iff the returned size matches our expectation.w
         */
        if (randomData.size >= 16) {

            /*
             Set the two most significant bits (bits 6 and 7) of the
             clock_seq_hi_and_reserved to zero and one, respectively.

             clk_seq_hi_res is at index 7 (see table)
             */

            randomData[clk_seq_hi_resIndex] =
                randomData[clk_seq_hi_resIndex]
                    .and(0b0111_1111)
                    .or(0b1000_0000.toByte())
            //AND => removes all the unwanted 1's (akk at the second last location
            //OR => adds the 1 to the last bit. => 0b10xxxxxx
            //akk set upper part to 0b10xx


            /*
            version 4 bit pattern
                0     1     0     0   The randomly or pseudo-
                                     randomly generated version
                                     specified in this document.
             */

            randomData[time_hi_and_versionIndex] =
                randomData[time_hi_and_versionIndex]
                    .and(0b0100_1111)
                    .or(0b0100_0000)
            //AND => removes all the unwanted 1's (akk all the x*xx in the upper part)
            //OR => adds the second last bit. => 0b010xxxxxx
            //ak set upper part to 0b0100
        }

        val hexString = randomData.toHexString(false, shouldBeUppercase)
        return if (shouldHaveDashes) {
            return hexString.mpp.insertInto(
                StringMpp.StringInserts("-", 8),
                StringMpp.StringInserts("-", 12),
                StringMpp.StringInserts("-", 16),
                StringMpp.StringInserts("-", 20)
            ) ?: hexString
        } else {
            hexString
        }
    }
}

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