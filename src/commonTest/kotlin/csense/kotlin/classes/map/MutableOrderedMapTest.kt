package csense.kotlin.classes.map

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class MutableOrderedMapTest {

    @Test
    fun isOrdered() {
        val map: MutableOrderedMap<String, String> = MutableOrderedMap()
        map["1111"] = "1111"
        map["1234"] = "1234"

        map.entries.assertSize(2)
        map.entries.elementAt(0).assert(key = "1111", value = "1111")
        map.entries.elementAt(1).assert(key = "1234", value = "1234")

        map.remove("1111")
        map.assertSingle("1234" to "1234")
    }
}