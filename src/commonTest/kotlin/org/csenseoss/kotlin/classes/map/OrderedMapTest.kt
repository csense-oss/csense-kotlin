@file:Suppress("unused")

package org.csenseoss.kotlin.classes.map

import org.csenseoss.kotlin.tests.assertions.collections.iterable.*
import org.csenseoss.kotlin.tests.assertions.collections.map.entry.*
import kotlin.test.*

class OrderedMapTest {

    @Test
    fun isOrdered() {
        val map: OrderedMap<Int, String> = OrderedMap(
            42 to "1234",
            50 to "1234"
        )
        val entries: Set<Map.Entry<Int, String>> = map.entries
        entries.assertSize(2)
        entries.elementAt(0).assert(key = 42, value = "1234")
        entries.elementAt(1).assert(key = 50, value = "1234")
    }
}