package org.csenseoss.kotlin.extensions.collections.map.mutable

import org.csenseoss.kotlin.extensions.collections.map.mutable.mutableList.*
import org.csenseoss.kotlin.tests.assertions.*
import org.csenseoss.kotlin.tests.assertions.collections.map.*
import org.csenseoss.kotlin.tests.assertions.collections.map.entry.*
import org.csenseoss.kotlin.tests.assertions.collections.map.iterable.*
import kotlin.test.*

class MutableMapMutableListTest {
    class MutableMapKeyListValueRemoveOnEmptyValue {

        @Test
        fun empty() {
            val map: MutableMap<String, List<String>> = mutableMapOf()
            map.removeOnEmptyValue("nonExistingKey")
            map.assertEmpty()
        }


        @Test
        fun singleWrongKey() {
            val map: MutableMap<String, List<String>> = mutableMapOf("test" to listOf())
            map.removeOnEmptyValue("key2")
            map.assert("test" to listOf())
        }

        @Test
        fun singleKeyEmpty() {
            val map: MutableMap<String, List<String>> = mutableMapOf("key" to listOf())
            map.removeOnEmptyValue("key")
            map.assertEmpty()
        }

        @Test
        fun singleKeyNotEmpty() {
            val map: MutableMap<String, List<String>> = mutableMapOf("key" to listOf("content"))
            map.removeOnEmptyValue("key")
            map.assert("key" to listOf("content"))
        }
    }

    class MutableMapKeyValueMoveToBack {

        @Test
        fun empty() {
            val map: MutableMap<String, String> = mutableMapOf()
            map.moveToBack("missing")
            map.assertEmpty()
        }


        @Test
        fun singleNotFound() {
            val map: MutableMap<String, String> = mutableMapOf("test" to "1234")
            map.moveToBack("missing")
            map.assert("test" to "1234")
        }

        @Test
        fun singleFound() {
            val map: MutableMap<String, String> = mutableMapOf("test" to "1234")
            map.moveToBack("test")
            map.assert("test" to "1234")
        }


        @Test
        fun multipleNotFound() {
            val map: MutableMap<String, String> = mutableMapOf("test" to "1234", "abc" to "1234", "qwerty" to "1234")
            map.moveToBack("missing")
            map.assertSize(3)
        }

        @Test
        fun multipleFoundInFront() {
            val map: MutableMap<String, String> = mutableMapOf("test" to "1234", "abc" to "1234", "qwerty" to "1234")
            map.moveToBack("test")
            map.assertSize(3)
            map.entries.last().assert("test", "1234")
        }

        @Test
        fun multipleFoundInMiddle() {
            val map: MutableMap<String, String> = mutableMapOf("test" to "1234", "abc" to "1234", "qwerty" to "1234")
            map.moveToBack("abc")
            map.assertSize(3)
            map.entries.last().assert("abc", "1234")
        }


        @Test
        fun multipleFoundInEnd() {
            val map: MutableMap<String, String> = mutableMapOf("test" to "1234", "abc" to "1234", "qwerty" to "1234")
            map.moveToBack("qwerty")
            map.assertSize(3)
            map.entries.last().assert("qwerty", "1234")
        }

    }
}