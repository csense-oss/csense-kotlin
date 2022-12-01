package csense.kotlin.extensions.primitives.char

import csense.kotlin.tests.assertions.*
import org.junit.*

class CharJvmTest {
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