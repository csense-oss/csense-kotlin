@file:Suppress("unused")

package csense.kotlin.extensions.collections.array.generic

import csense.kotlin.tests.assertions.*
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
            input.assertSingle(42)
        }


        @Test
        fun multiple() {
            val input: MutableList<Int> = arrayOf(
                "1",
                "2"
            ).mapToMutable { it: String ->
                it.toInt()
            }
            input.assertSize(2)
            input.assertContainsInOrder(1, 2)
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
            calledWithValues.assertSize(2)
            calledWithValues.assertContainsInOrder("abc", "1234")
        }
    }
}