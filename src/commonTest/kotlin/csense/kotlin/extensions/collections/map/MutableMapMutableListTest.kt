package csense.kotlin.extensions.collections.map

import csense.kotlin.extensions.collections.map.mutable.mutableList.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class MutableMapMutableListTest {
    class MutableMapKeyMutableListValueAppendValues {

        @Test
        fun emptyAndEmpty() {
            val result = mutableMapOf<String, MutableList<String>>()
            result.appendValues(mapOf())
            result.assertEmpty("empty + empty is still empty")
        }


        @Test
        fun emptySingle() {
            val result = mutableMapOf<String, MutableList<String>>()
            result.appendValues(mapOf("1" to mutableListOf("1")))
            result.assertSingle {
                it.key.assert("1")
                it.value.assertSingle("1")
            }
        }


        @Test
        fun emptyMultiple() {
            val result = mutableMapOf<String, MutableList<String>>()
            result.appendValues(mapOf("1" to mutableListOf("11", "12"), "2" to mutableListOf("21", "22")))
            result.assertSize(2)

            result.assertContainsKeyAnd("1") {
                it.assertContainsInOrder("11", "12")
            }

            result.assertContainsKeyAnd("2") {
                it.assertContainsInOrder("21", "22")
            }
        }

    }
}