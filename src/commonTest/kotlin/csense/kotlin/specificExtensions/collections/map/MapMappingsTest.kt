package csense.kotlin.specificExtensions.collections.map

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class MapMappingsTest {

    @Test
    fun mapKeyValueMappings() {
        val first: MapMappings<String, String> = mapOf("a" to "1").mappings
        val second: MapMappings<String, String> = mapOf("b" to "2").mappings
        (first != second).assertTrue("should not be the same instance of mappings")
    }

    class MapMappingsKeyValueMapEachEntryWith {
        @Test
        fun empty() {
            val empty: MapMappings<String, String> = mapOf<String, String>().mappings
            val result: Int = empty.mapEachEntryWith(42) { shouldNotBeCalled() }
            result.assert(42)
        }

        @Test
        fun single(): Unit = assertCalled { shouldBeCalled: () -> Unit ->
            val empty: MapMappings<String, Int> = mapOf("test" to 1).mappings
            val result: String = empty.mapEachEntryWith("result") { it: Map.Entry<String, Int> ->
                it.assert(key = "test", value = 1)
                shouldBeCalled()
            }
            result.assert("result")
        }

        @Test
        fun multiple(): Unit = assertCalled(times = 2) { shouldBeCalled: () -> Unit ->
            val empty: MapMappings<String, Int> = mapOf("test" to 1, "1234" to 2).mappings
            val result: MutableList<Map.Entry<String, Int>> = empty.mapEachEntryWith(
                mutableListOf()
            ) { it: Map.Entry<String, Int> ->
                shouldBeCalled()
                this += it
            }
            result.assertSize(2)
            result.first().assert(key = "test", value = 1)
            result.last().assert(key = "1234", value = 2)
        }

    }

    class ReverseKeyValue {
        @Test
        fun empty() {
            val map: Map<String, String> = mapOf()
            val reverse: MutableMap<String, String> = map.mappings.reverseKeyValue()
            reverse.assertEmpty()
        }

        @Test
        fun single() {
            val map: Map<String, String> = mapOf("abc" to "123")
            val reverse: MutableMap<String, String> = map.mappings.reverseKeyValue()
            reverse.assertSingle(keyValue = "123" to "abc")
        }

        @Test
        fun multipleNoCollision() {
            val map: Map<String, String> = mapOf(
                "abc" to "1",
                "qwer" to "2"
            )
            val reverse: MutableMap<String, String> = map.mappings.reverseKeyValue()
            reverse.assertSize(2)
            reverse.assertContains("1" to "abc")
            reverse.assertContains("2" to "qwer")
        }

        @Test
        fun collision() {
            val map: Map<String, String> = mapOf(
                "abc" to "1",
                "qwer" to "1"
            )
            val reverse: MutableMap<String, String> = map.mappings.reverseKeyValue()
            reverse.assertSize(1)
            reverse.assertContains("1" to "qwer")
        }
    }

}