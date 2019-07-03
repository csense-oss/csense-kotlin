package csense.kotlin.extensions.collections.map

import csense.kotlin.test.assertions.*
import kotlin.test.*


class MutableMapKtTest {

    @Test
    fun removeAll() {
        val map = mutableMapOf<String, String>()
        //empty case, nothing should happen here.
        map.removeAll { true }
        map.removeAll { false }
        map.assertSize(0)

        map["a"] = "b"
        map.removeAll { false }
        map.assertSize(1, "should not have removed any elements when no predicates matches")

        map.removeAll { it.key == "a" }
        map.assertSize(0, "should have matching predicate and thus remove element")

        map.putAll(listOf(
                "a" to "b",
                "c" to "d",
                "1" to "2",
                "3" to "4"
        ))

        map.removeAll { false }
        map.assertSize(4, "should not have removed any elements when no predicates matches")

        map.removeAll { it.value == "4" }
        map.assertSize(3)
        map.removeAll { it.key == "1" }
        map.assertSize(2)
        map.removeAll { true }
        map.assertSize(0)
    }

    @Ignore
    @Test
    fun setIfNotEmpty() {

    }

    @Ignore
    @Test
    fun putSubList() {

    }

}