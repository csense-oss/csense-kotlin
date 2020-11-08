package csense.kotlin.extensions.collections.map

import csense.kotlin.tests.assertions.assert
import csense.kotlin.tests.assertions.assertSize
import kotlin.test.Test

class MapEntryKtTest {

    private val emptryEntry = listOf<Map.Entry<String, String>>()
    private val singlEntry = listOf(SimpleMapEntry("1234", "asdf"))

    private val multipleEntries = listOf(
        SimpleMapEntry("123", "asdf"),
        SimpleMapEntry("456", "qwerty"),
        SimpleMapEntry("789", "zxcv")
    )

    @Test
    fun mapKeys() {
        emptryEntry.mapKeys().assertSize(0)
        singlEntry.mapKeys().apply {
            assertSize(1)
            first().assert("1234")
        }
        multipleEntries.mapKeys().apply {
            assertSize(multipleEntries.size)
            first().assert(multipleEntries.first().key)
            last().assert(multipleEntries.last().key)
        }
    }

    @Test
    fun mapValues() {
        emptryEntry.mapValues().assertSize(0)
        singlEntry.mapValues().apply {
            assertSize(1)
            first().assert("asdf")
        }
        multipleEntries.mapValues().apply {
            assertSize(multipleEntries.size)
            first().assert(multipleEntries.first().value)
            last().assert(multipleEntries.last().value)
        }

    }
}

class SimpleMapEntry<K, V>(
    override val key: K,
    override val value: V
) : Map.Entry<K, V>