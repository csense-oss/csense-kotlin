package csense.kotlin.extensions.collections.array

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class BooleanArrayTest {

    @Test
    fun booleanArrayForEachDiscard() {
        //empty
        booleanArrayOf().forEachDiscard { shouldNotBeCalled() }
        //single
        booleanArrayOf(true).forEachDiscard { it.assert(true); return@forEachDiscard 1 }
        //multiple
        booleanArrayOf(false, false).forEachDiscard { it.assert(false); return@forEachDiscard "" }
    }
}