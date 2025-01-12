package org.csenseoss.kotlin.extensions.collections.set.mutable

import org.csenseoss.kotlin.tests.assertions.collections.iterable.*
import kotlin.test.*

class ToggleTest {

    @Test
    fun toggleExistence() {
        val col: MutableSet<String> = mutableSetOf()

        col.toggleExistence("asd")
        col.assertSize(1)
        col.assert("asd")

        col.toggleExistence("asd")
        col.assertEmpty()


        col.toggleExistence("asd1")
        col.toggleExistence("asd2")
        col.assert("asd1", "asd2")
    }

}