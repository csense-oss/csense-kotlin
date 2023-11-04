@file:Suppress("unused")

package csense.kotlin.extensions.collections.collection

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class IsSingleTest {
    class IsSingle {
        @Test
        fun empty() {
            listOf<String>().isSingle().assertFalse()
        }

        @Test
        fun single() {
            listOf("test").isSingle().assertTrue()
        }

        @Test
        fun multiple() {
            listOf("test", "1234").isSingle().assertFalse()
        }
    }

    class IsSingleOrEmpty {

        @Test
        fun empty() {
            listOf<String>().isSingleOrEmpty().assertTrue()
        }

        @Test
        fun single() {
            listOf("test").isSingleOrEmpty().assertTrue()
        }

        @Test
        fun multiple() {
            listOf("test", "1234").isSingleOrEmpty().assertFalse()
        }
    }

}