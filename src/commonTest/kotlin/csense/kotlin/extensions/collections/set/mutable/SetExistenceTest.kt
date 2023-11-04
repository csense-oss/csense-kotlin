package csense.kotlin.extensions.collections.set.mutable

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class SetExistenceTest {

    @Test
    fun setExistence() {
        val col: MutableSet<String> = mutableSetOf()

        col.setExistence(item = "asd", shouldExists = false)
        col.assertSize(0)

        col.setExistence(item = "asd", shouldExists = true)
        col.assertSize(1)
        col.assertContains("asd")

        col.setExistence(item = "asd", shouldExists = true)
        col.assertSize(1)
        col.assertContains("asd")

        col.setExistence(item = "asd", shouldExists = false)
        col.assertSize(0)

        col.setExistence(item = "asd1", shouldExists = true)
        col.setExistence(item = "asd2", shouldExists = true)
        col.assertSize(2)
        col.assertContainsAll("asd1", "asd2")

        col.setExistence(item = "asd2", shouldExists = false)
        col.assertSize(1)
        col.assertContains("asd1")
    }
}