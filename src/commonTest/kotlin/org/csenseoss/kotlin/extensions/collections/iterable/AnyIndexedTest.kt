@file:Suppress("unused")

package org.csenseoss.kotlin.extensions.collections.iterable

import org.csenseoss.kotlin.tests.assertions.*
import org.csenseoss.kotlin.tests.assertions.comparable.*
import org.csenseoss.kotlin.tests.assertions.general.*
import org.csenseoss.kotlin.tests.assertions.primitives.boolean.*
import kotlin.test.*

class AnyIndexedTest {
    class IterableTAnyIndexed {

        @Test
        fun empty() {
            listOf<String>().asIterable().anyIndexed { _, _ -> shouldNotBeCalled() }.assertFalse()
        }


        @Test
        fun singleAlways() {
            listOf("test").asIterable().anyIndexed { index, item ->
                index.assert(0)
                item.assert("test")
                true
            }.assertTrue()
        }

        @Test
        fun singleNever() {
            listOf("1234").asIterable().anyIndexed { index, item ->
                index.assert(0)
                item.assert("1234")
                false
            }.assertFalse()
        }


        @Test
        fun multipleNever() {
            listOf("1234", "abc").asIterable().anyIndexed { _, _ ->
                false
            }.assertFalse()
        }

        @Test
        fun multipleAlways() {
            listOf("1234", "abc").asIterable().anyIndexed { _, _ ->
                true
            }.assertTrue()
        }

        @Test
        fun multipleOnLastElement() {
            listOf("1234", "abc").asIterable().anyIndexed { _, item ->
                item == "abc"
            }.assertTrue()
        }

    }
}