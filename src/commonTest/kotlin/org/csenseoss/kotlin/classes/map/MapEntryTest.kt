@file:Suppress("unused")

package org.csenseoss.kotlin.classes.map

import org.csenseoss.kotlin.tests.assertions.collections.map.*
import kotlin.test.*

class MapEntryTest {
    class MutableMapKeyValuePlusAssign {
        @Test
        fun empty() {
            val map: MutableMap<String, String> = mutableMapOf()
            map += MapEntry("key", "value")
            map.assert("key" to "value")
        }

        @Test
        fun newKey() {
            val map: MutableMap<String, String> = mutableMapOf("otherKey" to "someValue")
            map += MapEntry("key", "value")
            map.assert("otherKey" to "someValue", "key" to "value")
        }

        @Test
        fun keyExists() {
            val map: MutableMap<String, String> = mutableMapOf("key" to "firstValue")
            map += MapEntry("key", "newValue")
            map.assert("key" to "newValue")
        }
    }

    class MutableMapKeyValueMinusAssign {
        @Test
        fun empty() {
            val map: MutableMap<String, String> = mutableMapOf()
            map -= MapEntry("key", "value")
            map.assertEmpty()
        }

        @Test
        fun newKey() {
            val map: MutableMap<String, String> = mutableMapOf("otherKey" to "someValue")
            map -= MapEntry("key", "value")
            map.assert( "otherKey" to "someValue")
        }

        @Test
        fun keyExists() {
            val map: MutableMap<String, String> = mutableMapOf("key" to "firstValue")
            map += MapEntry("key", "newValue")
            map.assert( "key" to "newValue")
        }
    }
}