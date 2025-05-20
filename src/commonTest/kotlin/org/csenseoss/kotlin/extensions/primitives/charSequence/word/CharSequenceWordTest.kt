package org.csenseoss.kotlin.extensions.primitives.charSequence.word

import org.csenseoss.kotlin.tests.assertions.general.*
import kotlin.test.*

class CharSequenceWordTest {

    @Test
    fun word(){
        val empty: CharSequenceWord = "".word
        val single: CharSequenceWord = "1".word

        empty.assertNotByEquals(single)
    }
}