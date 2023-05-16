package csense.kotlin.extensions.collections.array

import csense.kotlin.extensions.collections.array.typed.boolean.*
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


    class BooleanArrayForEachBackwards {
        @Test
        fun empty() {
            val empty: BooleanArray = booleanArrayOf()
            empty.forEachBackwards {
                shouldNotBeCalled()
            }
        }

        @Test
        fun single() = assertCalled(times = 1) { shouldBeCalled ->
            val single: BooleanArray = booleanArrayOf(true)
            single.forEachBackwards {
                it.assertTrue()
                shouldBeCalled()
            }
        }

        @Test
        fun multiple() = assertCalled(times = 2) { shouldBeCalled ->
            var haveSeenLast = false
            val multiple: BooleanArray = booleanArrayOf(true, false)
            multiple.forEachBackwards {
                if (haveSeenLast) {
                    it.assertTrue()
                } else {
                    it.assertFalse()
                }
                haveSeenLast = true
                shouldBeCalled()
            }
        }
    }
}