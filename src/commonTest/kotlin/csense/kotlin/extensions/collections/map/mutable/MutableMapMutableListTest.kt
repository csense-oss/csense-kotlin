package csense.kotlin.extensions.collections.map.mutable

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class MutableMapMutableListTest {
    class MutableMapKeyListValueRemoveOnEmptyValue {

        @Test
        fun empty() {
            val map = mutableMapOf<String, List<String>>()
            map.removeOnEmptyValue("nonExistingKey")
            map.assertEmpty()
        }


        @Test
        fun singleWrongKey() {
            val map = mutableMapOf<String, List<String>>("test" to listOf())
            map.removeOnEmptyValue("key2")
            map.assertSingle("test" to listOf())
        }

        @Test
        fun singleKeyEmpty() {
            val map = mutableMapOf<String, List<String>>("key" to listOf())
            map.removeOnEmptyValue("key")
            map.assertEmpty()
        }

        @Test
        fun singleKeyNotEmpty() {
            val map = mutableMapOf<String, List<String>>("key" to listOf("content"))
            map.removeOnEmptyValue("key")
            map.assertSingle("key" to listOf("content"))
        }
    }

    class MutableMapKeyValueMoveToBack {

        @Test
        fun empty() {
            val map = mutableMapOf<String, String>()
            map.moveToBack("missing")
            map.assertEmpty()
        }


        @Test
        fun singleNotFound() {
            val map = mutableMapOf("test" to "1234")
            map.moveToBack("missing")
            map.assertSingle("test" to "1234")
        }

        @Test
        fun singleFound() {
            val map = mutableMapOf("test" to "1234")
            map.moveToBack("test")
            map.assertSingle("test" to "1234")
        }


        @Test
        fun multipleNotFound() {
            val map = mutableMapOf("test" to "1234", "abc" to "1234", "qwerty" to "1234")
            map.moveToBack("missing")
            map.assertSize(3)
        }

        @Test
        fun multipleFoundInFront() {
            val map = mutableMapOf("test" to "1234", "abc" to "1234", "qwerty" to "1234")
            map.moveToBack("test")
            map.assertSize(3)
            map.entries.last().assert("test", "1234")
        }

        @Test
        fun multipleFoundInMiddle() {
            val map = mutableMapOf("test" to "1234", "abc" to "1234", "qwerty" to "1234")
            map.moveToBack("abc")
            map.assertSize(3)
            map.entries.last().assert("abc", "1234")
        }


        @Test
        fun multipleFoundInEnd() {
            val map = mutableMapOf("test" to "1234", "abc" to "1234", "qwerty" to "1234")
            map.moveToBack("qwerty")
            map.assertSize(3)
            map.entries.last().assert("qwerty", "1234")
        }

    }
}