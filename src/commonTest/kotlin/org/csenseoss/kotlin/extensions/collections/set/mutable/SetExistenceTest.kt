package org.csenseoss.kotlin.extensions.collections.set.mutable

import org.csenseoss.kotlin.tests.assertions.*
import org.csenseoss.kotlin.tests.assertions.collections.iterable.*
import org.csenseoss.kotlin.tests.assertions.collections.iterable.*
import org.csenseoss.kotlin.tests.assertions.collections.iterable.assertContains
import org.csenseoss.kotlin.tests.assertions.collections.iterable.assertSize
import kotlin.test.*

class SetExistenceTest {

    @Test
    fun setExistence() {
        val col: MutableSet<String> = mutableSetOf()

        col.setExistence(item = "asd", shouldExists = false)
        col.assertEmpty()

        col.setExistence(item = "asd", shouldExists = true)
        col.assertSize(1)
        col.assertContains("asd")

        col.setExistence(item = "asd", shouldExists = true)
        col.assertSize(1)
        col.assertContains("asd")

        col.setExistence(item = "asd", shouldExists = false)
        col.assertEmpty()

        col.setExistence(item = "asd1", shouldExists = true)
        col.setExistence(item = "asd2", shouldExists = true)
        col.assert("asd1", "asd2")

        col.setExistence(item = "asd2", shouldExists = false)
        col.assertSize(1)
        col.assertContains("asd1")
    }
}