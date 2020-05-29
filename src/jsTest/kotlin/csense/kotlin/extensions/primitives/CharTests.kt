package csense.kotlin.extensions.primitives

import csense.kotlin.tests.assertions.*
import kotlin.test.*


class CharTests{
    
    @Test
    fun charIsNumber() {
        ' '.isNumber().assertFalse()
        'a'.isNumber().assertFalse()
        '1'.isNumber().assertTrue()
        '?'.isNumber().assertFalse()
        '\n'.isNumber().assertFalse()
    }
}