package org.csenseoss.kotlin.extensions.collections.map.linkedHashMap

import org.csenseoss.kotlin.classes.map.*
import org.csenseoss.kotlin.tests.assertions.collections.map.*
import org.csenseoss.kotlin.tests.assertions.primitives.charSequence.*
import kotlin.test.*

class LinkedHashMapTest {

    class LinkedHashMapMapEntry {
        @Test
        fun empty() {
            val map: LinkedHashMap<String, String> = LinkedHashMap(listOf<Map.Entry<String, String>>())
            map.assertEmpty()
        }

        @Test
        fun single() {
            val map: LinkedHashMap<String, String> = LinkedHashMap(listOf(MapEntry("key", "value")))
            map.assertSize(1)
            val first = map.entries.first()
            first.key.assert("key")
            first.value.assert("value")
        }

        @Test
        fun multiple() {
            val map: LinkedHashMap<String, String> =
                LinkedHashMap(listOf(MapEntry("key", "value"), MapEntry("key2", "value2")))
            map.assertSize(2)
        }
    }

    class LinkedHashMapPair {
        @Test
        fun empty() {
            val map: LinkedHashMap<String, String> = LinkedHashMap(listOf<Pair<String, String>>())
            map.assertEmpty()
        }

        @Test
        fun single() {
            val map: LinkedHashMap<String, String> = LinkedHashMap(listOf("key" to "value"))
            map.assertSize(1)
            val first = map.entries.first()
            first.key.assert("key")
            first.value.assert("value")
        }

        @Test
        fun multiple() {
            val map: LinkedHashMap<String, String> = LinkedHashMap(listOf("key" to "value", "key2" to "value2"))
            map.assertSize(2)
        }
    }
}