package csense.kotlin.classes.map

import csense.kotlin.tests.assertions.*
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