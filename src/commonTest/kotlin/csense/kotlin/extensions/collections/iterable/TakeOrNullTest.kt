@file:Suppress("unused")

package csense.kotlin.extensions.collections.iterable

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class TakeOrNullTest {

    class IterableTTakeOrNull {
        @Test
        fun empty() {
            val lst: Iterable<String> = listOf()
            lst.takeOrNull(0).assertNotNullApply { assertEmpty() }
            lst.takeOrNull(1).assertNotNullApply { assertEmpty() }
            lst.takeOrNull(-1).assertNull()
        }

        @Test
        fun single() {
            val lst: Iterable<String> = listOf("abc")
            lst.takeOrNull(0).assertNotNullApply { assertEmpty() }
            lst.takeOrNull(1).assertNotNullApply {
                assertSingle("abc")
            }
            lst.takeOrNull(-1).assertNull()
        }

        @Test
        fun multiple() {
            val lst: Iterable<String> = listOf("abc", "123")
            lst.takeOrNull(-1).assertNull()
            lst.takeOrNull(0).assertNotNullApply { assertEmpty() }
            lst.takeOrNull(1).assertNotNullApply {
                assertSingle("abc")
            }
            lst.takeOrNull(2).assertNotNullApply {
                assertSize(2)
                this[0].assert("abc")
                this[1].assert("123")
            }
            lst.takeOrNull(3).assertNotNullApply {
                assertSize(2)
                this[0].assert("abc")
                this[1].assert("123")
            }
        }
    }
}