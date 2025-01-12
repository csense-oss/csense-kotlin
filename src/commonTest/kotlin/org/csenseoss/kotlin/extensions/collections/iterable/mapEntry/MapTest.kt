package org.csenseoss.kotlin.extensions.collections.iterable.mapEntry

import org.csenseoss.kotlin.tests.assertions.*
import org.csenseoss.kotlin.tests.assertions.collections.iterable.*
import org.csenseoss.kotlin.tests.assertions.primitives.charSequence.*
import kotlin.test.*

class MapTest {

    private val emptyEntry: List<Map.Entry<String, String>> = listOf()
    private val singleEntry: List<SimpleMapEntry<String, String>> = listOf(SimpleMapEntry("1234", "asdf"))

    private val multipleEntries: List<SimpleMapEntry<String, String>> = listOf(
        SimpleMapEntry("123", "asdf"),
        SimpleMapEntry("456", "qwerty"),
        SimpleMapEntry("789", "zxcv")
    )

    @Test
    fun mapKeys() {
        emptyEntry.mapKeys().assertEmpty()
        singleEntry.mapKeys().apply {
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
        emptyEntry.mapValues().assertEmpty()
        singleEntry.mapValues().apply {
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