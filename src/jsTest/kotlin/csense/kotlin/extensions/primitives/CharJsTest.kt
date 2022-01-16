package csense.kotlin.extensions.primitives

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class CharJsTest {
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