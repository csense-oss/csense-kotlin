package csense.kotlin.extensions.primitives

import csense.kotlin.tests.assertions.*
import org.junit.jupiter.api.*

class IsUpperCaseTest {
    @Test
    fun charIsUpperCase() {
        ' '.isUpperCaseLetter().assertFalse()
        'a'.isUpperCaseLetter().assertFalse()
        'A'.isUpperCaseLetter().assertTrue()
        '1'.isUpperCaseLetter().assertFalse()
        '?'.isUpperCaseLetter().assertFalse()
        'Q'.isUpperCaseLetter().assertTrue()
        '\n'.isUpperCaseLetter().assertFalse()
    }
    
    @Test
    fun charIsLowerCase() {
        ' '.isLowerCaseLetter().assertFalse()
        'a'.isLowerCaseLetter().assertTrue()
        'B'.isLowerCaseLetter().assertFalse()
        '1'.isLowerCaseLetter().assertFalse()
        '?'.isLowerCaseLetter().assertFalse()
        '\n'.isLowerCaseLetter().assertFalse()
        'Q'.isLowerCaseLetter().assertFalse()
        'q'.isLowerCaseLetter().assertTrue()
    }
}