package csense.kotlin.extensions.collections.set

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class MutableSetTest {

    @Test
    fun toggleExistence() {
        val col = mutableSetOf<String>()

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

    @Test
    fun setExistence() {
        val col = mutableSetOf<String>()

        col.setExistence("asd", false)
        col.assertSize(0)

        col.setExistence("asd", true)
        col.assertSize(1)
        col.assertContains("asd")

        col.setExistence("asd", true)
        col.assertSize(1)
        col.assertContains("asd")

        col.setExistence("asd", false)
        col.assertSize(0)

        col.setExistence("asd1", true)
        col.setExistence("asd2", true)
        col.assertSize(2)
        col.assertContainsAll("asd1", "asd2")

        col.setExistence("asd2", false)
        col.assertSize(1)
        col.assertContains("asd1")
    }
}