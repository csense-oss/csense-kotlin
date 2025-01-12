package org.csenseoss.kotlin.extensions.collections.array.generic

import org.csenseoss.kotlin.tests.assertions.*
import org.csenseoss.kotlin.tests.assertions.comparable.*
import org.csenseoss.kotlin.tests.assertions.general.*
import org.csenseoss.kotlin.tests.assertions.primitives.boolean.*
import kotlin.test.*

class AnyIndexedTest {

    @Test
    fun empty() {
        arrayOf<String>().anyIndexed { _: Int, _: String ->
            shouldNotBeCalled()
        }.assertFalse()

    }

    @Test
    fun singleFound() {
        arrayOf("test").anyIndexed { index: Int, item: String ->
            index.assert(0)
            item.assert("test")
            true
        }.assertTrue()
    }

    @Test
    fun singleNotFound() {
        arrayOf("1234").anyIndexed { index: Int, item: String ->
            index.assert(0)
            item.assert("1234")
            false
        }.assertFalse()
    }

    @Test
    fun multipleNonFound() {
        arrayOf("1", "2", "3").anyIndexed { _: Int, _: String ->
            false
        }.assertFalse()
    }

    @Test
    fun multipleFirstFound() {
        arrayOf("1", "2", "3").anyIndexed { index: Int, item: String ->
            index == 0 && item == "1"
        }.assertTrue()
    }

    @Test
    fun multipleMiddleFound() {
        arrayOf("1", "2", "3").anyIndexed { index: Int, item: String ->
            index == 1 && item == "2"
        }.assertTrue()
    }

    @Test
    fun multipleLastFound() {
        arrayOf("1", "2", "3").anyIndexed { index: Int, item: String ->
            index == 2 && item == "3"
        }.assertTrue()
    }
}