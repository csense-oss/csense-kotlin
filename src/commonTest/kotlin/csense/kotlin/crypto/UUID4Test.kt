package csense.kotlin.crypto

import csense.kotlin.extensions.primitives.*
import csense.kotlin.tests.assertions.*
import kotlin.experimental.*
import kotlin.random.*
import kotlin.test.*

class UUID4Test {

    /**
     * By observing
     * https://www.baeldung.com/java-uuid  -> 2.structure
     *
    123e4567-e89b-42d3-a456-556642440000
    xxxxxxxx-xxxx-Bxxx-Axxx-xxxxxxxxxxxx

    A represents the variant which determines the layout of the UUID.
    B represents the version. The version in the mentioned UUID (value of B) is 4.
     */
    @Test
    fun create() {
        val random = Random(500)
        val firstUUId = UUID4.create(random)
        val secondUUID = UUID4.create(random)

        firstUUId.assertIsValidUUIDWithVersion('4')

        firstUUId.assertNot(secondUUID)

        secondUUID.assertIsValidUUIDWithVersion('4')

        "fa09b05b-cc0c-4b35-92b1-0971a035b712".assertIsValidUUIDWithVersion('4')
        "271f8672-f65d-11e8-8eb2-f2801f1b9fd1".assertIsValidUUIDWithVersion('1')

    }


    private fun String.assertIsValidUUIDWithVersion(version: Char) {
        length.assert(36)
        count { it == '-' }.assert(4)
        //index 14 should be the "version" element //group 3
        elementAt(14).assert(version)
        //index 19 should be the variant which should always be 10xx binary
        val hexByte = elementAt(19).asHexDigit()
        val byte = hexByte ?: throw Exception("not a valid hex digit")
        val anded = byte.and(0b1100)
        anded.assert(0b1000)

    }
}