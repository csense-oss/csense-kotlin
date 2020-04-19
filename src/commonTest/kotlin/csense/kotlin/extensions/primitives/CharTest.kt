package csense.kotlin.extensions.primitives

import csense.kotlin.tests.assertions.assert
import csense.kotlin.tests.assertions.assertNotNullAndEquals
import csense.kotlin.tests.assertions.assertNull
import kotlin.test.Test

class CharTest {

    @Test
    fun toCase() {
        'a'.toCase(true).assert('A')
        'a'.toCase(false).assert('a')

        'A'.toCase(true).assert('A')
        'A'.toCase(false).assert('a')

        '0'.toCase(true).assert('0')
        '0'.toCase(false).assert('0')
    }

    @Test
    fun asDigit() {
        '0'.asDigit().assertNotNullAndEquals(0)
        '1'.asDigit().assertNotNullAndEquals(1)
        '2'.asDigit().assertNotNullAndEquals(2)
        '3'.asDigit().assertNotNullAndEquals(3)
        '4'.asDigit().assertNotNullAndEquals(4)
        '5'.asDigit().assertNotNullAndEquals(5)
        '6'.asDigit().assertNotNullAndEquals(6)
        '7'.asDigit().assertNotNullAndEquals(7)
        '8'.asDigit().assertNotNullAndEquals(8)
        '9'.asDigit().assertNotNullAndEquals(9)
        'a'.asDigit().assertNull()
        'A'.asDigit().assertNull()
        '.'.asDigit().assertNull()
        '_'.asDigit().assertNull()
        ' '.asDigit().assertNull()
        'b'.asDigit().assertNull()
        'f'.asDigit().assertNull()
        'F'.asDigit().assertNull()
    }

    @Test
    fun asHexDigit() {
        '0'.asHexDigit().assertNotNullAndEquals(0)
        '1'.asHexDigit().assertNotNullAndEquals(1)
        '2'.asHexDigit().assertNotNullAndEquals(2)
        '3'.asHexDigit().assertNotNullAndEquals(3)
        '4'.asHexDigit().assertNotNullAndEquals(4)
        '5'.asHexDigit().assertNotNullAndEquals(5)
        '6'.asHexDigit().assertNotNullAndEquals(6)
        '7'.asHexDigit().assertNotNullAndEquals(7)
        '8'.asHexDigit().assertNotNullAndEquals(8)
        '9'.asHexDigit().assertNotNullAndEquals(9)
        'a'.asHexDigit().assertNotNullAndEquals(0x0a)
        'c'.asHexDigit().assertNotNullAndEquals(0x0C)
        '.'.asHexDigit().assertNull()
        '_'.asHexDigit().assertNull()
        ' '.asHexDigit().assertNull()
        'a'.asHexDigit().assertNotNullAndEquals(0x0A)
        'A'.asHexDigit().assertNotNullAndEquals(0x0a)
        'b'.asHexDigit().assertNotNullAndEquals(0x0b)
        'f'.asHexDigit().assertNotNullAndEquals(0x0f)
        'F'.asHexDigit().assertNotNullAndEquals(0x0F)
        'g'.asHexDigit().assertNull()
    }
}