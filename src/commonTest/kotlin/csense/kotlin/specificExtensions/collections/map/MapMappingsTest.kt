package csense.kotlin.specificExtensions.collections.map

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class MapMappingsTest {

    @Test
    fun mapKeyValueMappings() {
        val first = mapOf("a" to "1").mappings
        val second = mapOf("b" to "2").mappings
        (first != second).assertTrue("should not be the same instance of mappings")
    }

    class MapMappingsKeyValueMapEachEntryWith {
        @Test
        fun empty() {
            val empty = mapOf<String, String>().mappings
            val result = empty.mapEachEntryWith(42) { shouldNotBeCalled() }
            result.assert(42)
        }

        @Test
        fun single() = assertCalled { shouldBeCalled ->
            val empty = mapOf("test" to 1).mappings
            val result = empty.mapEachEntryWith("result") {
                it.assert("test", 1)
                shouldBeCalled()
            }
            result.assert("result")
        }

        @Test
        fun multiple() = assertCalled(times = 2) { shouldBeCalled ->
            val empty = mapOf("test" to 1, "1234" to 2).mappings
            val result = empty.mapEachEntryWith(mutableListOf<Map.Entry<String, Int>>()) {
                shouldBeCalled()
                this += it
            }
            result.assertSize(2)
            result.first().assert("test", 1)
            result.last().assert("1234", 2)
        }

    }

    class ReverseKeyValue {
        @Test
        fun empty() {
            val map = mapOf<String, String>()
            val reverse = map.mappings.reverseKeyValue()
            reverse.assertEmpty()
        }

        @Test
        fun single() {
            val map = mapOf("abc" to "123")
            val reverse = map.mappings.reverseKeyValue()
            reverse.assertSingle {
                it.key.assert("123")
                it.value.assert("abc")
            }
        }

        @Test
        fun multipleNoCollision() {
            val map = mapOf(
                "abc" to "1",
                "qwer" to "2"
            )
            val reverse = map.mappings.reverseKeyValue()
            reverse.assertSize(2)
            reverse.assertContains("1" to "abc")
            reverse.assertContains("2" to "qwer")
        }

        @Test
        fun collision() {
            val map = mapOf(
                "abc" to "1",
                "qwer" to "1"
            )
            val reverse = map.mappings.reverseKeyValue()
            reverse.assertSize(1)
            reverse.assertContains("1" to "qwer")
        }
    }

}