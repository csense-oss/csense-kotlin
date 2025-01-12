@file:Suppress("unused")

package org.csenseoss.kotlin.extensions.collections.array.generic

import org.csenseoss.kotlin.tests.assertions.*
import org.csenseoss.kotlin.tests.assertions.collections.iterable.*
import org.csenseoss.kotlin.tests.assertions.comparable.*
import org.csenseoss.kotlin.tests.assertions.general.*
import org.csenseoss.kotlin.tests.assertions.primitives.charSequence.*
import kotlin.test.*

class MapTest {

    class ArrayItemMapToMutable {
        @Test
        fun empty() {
            val input: MutableList<Nothing> = arrayOf<String>().mapToMutable { shouldNotBeCalled() }
            input.assertEmpty()
        }


        @Test
        fun single() {
            val input: MutableList<Int> = arrayOf(
                "a"
            ).mapToMutable { it: String ->
                it.assert("a")
                42
            }
            input.assert(42)
        }


        @Test
        fun multiple() {
            val input: MutableList<Int> = arrayOf(
                "1",
                "2"
            ).mapToMutable { it: String ->
                it.toInt()
            }
            input.assert(1, 2)
        }

    }

    class ArrayItemMapEachWith {
        @Test
        fun empty() {
            val result: Int = arrayOf<String>().mapEachWith(42) { shouldNotBeCalled() }
            result.assert(42)
        }


        @Test
        fun single(): Unit = assertCalled { shouldBeCalled: () -> Unit ->
            val result: String = arrayOf("abc").mapEachWith("test") { it: String ->
                shouldBeCalled()
                this.assert("test")
                it.assert("abc")
            }
            result.assert("test")
        }


        @Test
        fun multiple() = assertCalled(times = 2) { shouldBeCalled: () -> Unit ->
            val calledWithValues: MutableList<String> = arrayOf(
                "abc",
                "1234"
            ).mapEachWith(mutableListOf()) { it: String ->
                shouldBeCalled()
                add(it)
            }
            calledWithValues.assert("abc", "1234")
        }
    }
}