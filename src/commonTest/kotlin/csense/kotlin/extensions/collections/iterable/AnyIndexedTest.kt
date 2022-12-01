package csense.kotlin.extensions.collections.iterable

import csense.kotlin.tests.assertions.*
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