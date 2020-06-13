package csense.kotlin.extensions.primitives

import csense.kotlin.tests.assertions.*
import org.junit.jupiter.api.*

class IsUpperCaseTest {
    @Test
    fun charIsUpperCase() {
        ' '.isUpperCase.assertFalse()
        'a'.isUpperCase.assertFalse()
        'A'.isUpperCase.assertTrue()
        '1'.isUpperCase.assertFalse()
        '?'.isUpperCase.assertFalse()
        'Q'.isUpperCase.assertTrue()
        '\n'.isUpperCase.assertFalse()
    }
    
    @Test
    fun charIsLowerCase() {
        ' '.isLowerCase.assertFalse()
        'a'.isLowerCase.assertTrue()
        'B'.isLowerCase.assertFalse()
        '1'.isLowerCase.assertFalse()
        '?'.isLowerCase.assertFalse()
        '\n'.isLowerCase.assertFalse()
        'Q'.isLowerCase.assertFalse()
        'q'.isLowerCase.assertTrue()
    }
}