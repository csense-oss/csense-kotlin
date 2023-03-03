package csense.kotlin.extensions.primitives.char

import csense.kotlin.tests.assertions.*
import kotlin.test.*

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
        '0'.asDigit().assert(0)
        '1'.asDigit().assert(1)
        '2'.asDigit().assert(2)
        '3'.asDigit().assert(3)
        '4'.asDigit().assert(4)
        '5'.asDigit().assert(5)
        '6'.asDigit().assert(6)
        '7'.asDigit().assert(7)
        '8'.asDigit().assert(8)
        '9'.asDigit().assert(9)
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
        '0'.asHexDigit().assert(0)
        '1'.asHexDigit().assert(1)
        '2'.asHexDigit().assert(2)
        '3'.asHexDigit().assert(3)
        '4'.asHexDigit().assert(4)
        '5'.asHexDigit().assert(5)
        '6'.asHexDigit().assert(6)
        '7'.asHexDigit().assert(7)
        '8'.asHexDigit().assert(8)
        '9'.asHexDigit().assert(9)
        'a'.asHexDigit().assert(0x0a)
        'c'.asHexDigit().assert(0x0C)
        '.'.asHexDigit().assertNull()
        '_'.asHexDigit().assertNull()
        ' '.asHexDigit().assertNull()
        'a'.asHexDigit().assert(0x0A)
        'A'.asHexDigit().assert(0x0a)
        'b'.asHexDigit().assert(0x0b)
        'f'.asHexDigit().assert(0x0f)
        'F'.asHexDigit().assert(0x0F)
        'g'.asHexDigit().assertNull()
    }

    @Test
    fun charIsDigit() {
        ' '.isDigit().assertFalse()
        'a'.isDigit().assertFalse()
        '?'.isDigit().assertFalse()
        '\n'.isDigit().assertFalse()

        '0'.isDigit().assertTrue()
        '1'.isDigit().assertTrue()
        '2'.isDigit().assertTrue()
        '3'.isDigit().assertTrue()
        '4'.isDigit().assertTrue()
        '5'.isDigit().assertTrue()
        '6'.isDigit().assertTrue()
        '7'.isDigit().assertTrue()
        '8'.isDigit().assertTrue()
        '9'.isDigit().assertTrue()
    }


    @Test
    fun charIsNotDigit() {
        ' '.isNotDigit().assertTrue()
        'a'.isNotDigit().assertTrue()
        'Q'.isNotDigit().assertTrue()
        '1'.isNotDigit().assertFalse()
        '?'.isNotDigit().assertTrue()
        '\n'.isNotDigit().assertTrue()
    }

    @Test
    fun testHexCharsToValue() {
        hexCharsToValue('A', 'A').assert(0xAA)
        hexCharsToValue('F', 'F').assert(0xFF)
        hexCharsToValue('Q', 'Q').assertNull()
        hexCharsToValue('0', '1').assert(0x01)
        hexCharsToValue('5', '1').assert(0x51)
    }

    @Test
    fun isNotWhitespace() {
        ' '.isNotWhitespace().assertFalse()
        'a'.isNotWhitespace().assertTrue()
        'Q'.isNotWhitespace().assertTrue()
        '1'.isNotWhitespace().assertTrue()
        '?'.isNotWhitespace().assertTrue()
        '\n'.isNotWhitespace().assertFalse()
    }

    class CharIsNotEqual {

        @Test
        fun test() {
            ' '.isNotEqual('a', ignoreCase = false).assertTrue()
            'a'.isNotEqual('a', ignoreCase = false).assertFalse()
            'Q'.isNotEqual('q', ignoreCase = false).assertTrue()
        }

        @Test
        fun ignoreCase() {
            'a'.isNotEqual('a', ignoreCase = true).assertFalse()
            'a'.isNotEqual('A', ignoreCase = true).assertFalse()
            'a'.isNotEqual('B', ignoreCase = true).assertTrue()
        }

    }

    @Test
    fun charIsSymbol() {
        ' '.isSymbol().assertTrue()
        'a'.isSymbol().assertFalse()
        'Q'.isSymbol().assertFalse()
        '1'.isSymbol().assertFalse()
        '?'.isSymbol().assertTrue()
        '\n'.isSymbol().assertTrue()
    }

    @Test
    fun charIsLetter() {
        ' '.isLetter().assertFalse()
        'a'.isLetter().assertTrue()
        'Q'.isLetter().assertTrue()
        '1'.isLetter().assertFalse()
        '?'.isLetter().assertFalse()
        '\n'.isLetter().assertFalse()
    }

    @Test
    fun charIsNotLetter() {
        ' '.isNotLetter().assertTrue()
        'a'.isNotLetter().assertFalse()
        'Q'.isNotLetter().assertFalse()
        '1'.isNotLetter().assertTrue()
        '?'.isNotLetter().assertTrue()
        '\n'.isNotLetter().assertTrue()
    }

    @Test
    fun charIsUpperCaseLetter() {
        ' '.isUpperCaseLetter().assertFalse()
        'a'.isUpperCaseLetter().assertFalse()
        'Q'.isUpperCaseLetter().assertTrue()
        '1'.isUpperCaseLetter().assertFalse()
        '?'.isUpperCaseLetter().assertFalse()
        '\n'.isUpperCaseLetter().assertFalse()
    }

    @Test
    fun charIsLowerCaseLetter() {
        ' '.isLowerCaseLetter().assertFalse()
        'a'.isLowerCaseLetter().assertTrue()
        'Q'.isLowerCaseLetter().assertFalse()
        '1'.isLowerCaseLetter().assertFalse()
        '?'.isLowerCaseLetter().assertFalse()
        '\n'.isLowerCaseLetter().assertFalse()
    }
}