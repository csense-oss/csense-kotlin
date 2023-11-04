package csense.kotlin.extensions.collections.set.mutable

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class ToggleTest {

    @Test
    fun toggleExistence() {
        val col: MutableSet<String> = mutableSetOf()

        col.toggleExistence("asd")
        col.assertSize(1)
        col.assertContains("asd")

        col.toggleExistence("asd")
        col.assertSize(0)
        col.assertContainsNot("asd")


        col.toggleExistence("asd1")
        col.toggleExistence("asd2")
        col.assertSize(2)
        col.assertContainsAll("asd1", "asd2")
    }

}