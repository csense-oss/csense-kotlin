package csense.kotlin.extensions.collections.map

import csense.kotlin.tests.assertions.assertEmpty
import csense.kotlin.tests.assertions.assertNotNullApply
import csense.kotlin.tests.assertions.assertSize
import kotlin.test.Test


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

    @Test
    fun setIfNotEmpty() {
        val map = mutableMapOf<String, List<String>>()
        map.setIfNotEmpty("a", listOf())
        map.assertEmpty()

        map.setIfNotEmpty("a", listOf("a"))
        map.assertSize(1)
        map["a"].assertNotNullApply { assertSize(1) }

        map.setIfNotEmpty("a", listOf("a", "b"))
        map.assertSize(1)
        map["a"].assertNotNullApply { assertSize(2) }

    }

    @Test
    fun putSubList() {
        val map = mutableMapOf<String, MutableList<String>>()
        map.putSubList("a", "a")
        map.assertSize(1)
        map["a"].assertNotNullApply { assertSize(1) }

        map.putSubList("a", "b")
        map.assertSize(1)
        map["a"].assertNotNullApply { assertSize(2) }

        map.putSubList("b", "a")
        map.assertSize(2)
        map["a"].assertNotNullApply { assertSize(2) }
        map["b"].assertNotNullApply { assertSize(1) }

    }
}