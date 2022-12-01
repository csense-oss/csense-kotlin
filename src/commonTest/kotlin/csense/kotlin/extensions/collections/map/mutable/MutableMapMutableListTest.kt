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

}