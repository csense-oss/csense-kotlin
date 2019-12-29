package csense.kotlin.extensions.collections.array

import csense.kotlin.tests.assertions.assert
import csense.kotlin.tests.assertions.failTest
import csense.kotlin.tests.assertions.shouldNotBeCalled
import kotlin.test.Test

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