@file:Suppress("unused")

package csense.kotlin.extensions.collections.array.generic

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class IsSingleTest {
    class IsSingle {
        @Test
        fun empty() {
            arrayOf<String>().isSingle().assertFalse()
        }

        @Test
        fun single() {
            arrayOf("test").isSingle().assertTrue()
        }

        @Test
        fun multiple() {
            arrayOf("test", "1234").isSingle().assertFalse()
        }
    }


    class IsSingleOrEmpty {

        @Test
        fun empty() {
            arrayOf<String>().isSingleOrEmpty().assertTrue()
        }

        @Test
        fun single() {
            arrayOf("test").isSingleOrEmpty().assertTrue()
        }

        @Test
        fun multiple() {
            arrayOf("test", "1234").isSingleOrEmpty().assertFalse()
        }

    }
}