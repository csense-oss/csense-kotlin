@file:Suppress("unused")

package csense.kotlin.classes.map

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class MapEntryTest {
    class MutableMapKeyValuePlusAssign {
        @Test
        fun empty() {
            val map = mutableMapOf<String, String>()
            map += MapEntry("key", "value")
            map.assertSingle {
                it.key.assert("key")
                it.value.assert("value")
            }
        }

        @Test
        fun newKey() {
            val map = mutableMapOf("otherKey" to "someValue")
            map += MapEntry("key", "value")
            map.assertSize(2)
            map.assertContainsKeyAnd("key") {
                it.assert("value")
            }
            map.assertContainsKeyAnd("otherKey") {
                it.assert("someValue")
            }
        }

        @Test
        fun keyExists() {
            val map = mutableMapOf("key" to "firstValue")
            map += MapEntry("key", "newValue")
            map.assertSingle {
                it.key.assert("key")
                it.value.assert("newValue")
            }
        }
    }

    class MutableMapKeyValueMinusAssign {
        @Test
        fun empty() {
            val map = mutableMapOf<String, String>()
            map -= MapEntry("key", "value")
            map.assertEmpty()
        }

        @Test
        fun newKey() {
            val map = mutableMapOf("otherKey" to "someValue")
            map -= MapEntry("key", "value")
            map.assertSingle {
                it.key.assert("otherKey")
                it.value.assert("someValue")
            }
        }

        @Test
        fun keyExists() {
            val map = mutableMapOf("key" to "firstValue")
            map += MapEntry("key", "newValue")
            map.assertSingle {
                it.key.assert("key")
                it.value.assert("newValue")
            }
        }
    }
}