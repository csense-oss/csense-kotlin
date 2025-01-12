@file:Suppress("unused")

package org.csenseoss.kotlin.classes.map

import org.csenseoss.kotlin.tests.assertions.collections.iterable.*
import org.csenseoss.kotlin.tests.assertions.collections.map.*
import org.csenseoss.kotlin.tests.assertions.collections.map.entry.*
import kotlin.test.*

class MutableOrderedMapTest {

    @Test
    fun isOrdered() {
        val map: MutableOrderedMap<String, String> = MutableOrderedMap()
        map["1111"] = "1111"
        map["1234"] = "1234"

        map.assert("1111" to "1111", "1234" to "1234")

        map.remove(key = "1111")
        map.assert("1234" to "1234")
    }
}