package csense.kotlin.extensions.primitives

import csense.kotlin.extensions.primitives.toCase
import csense.kotlin.test.assertions.assert
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
}