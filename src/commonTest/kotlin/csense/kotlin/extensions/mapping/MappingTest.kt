@file:Suppress("unused")

package csense.kotlin.extensions.mapping

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class MappingTest {


    @Test
    fun map() {
        true.map(ifTrue = 42, ifFalse = 0).assert(42)
        false.map(ifTrue = 42, ifFalse = 0).assert(0)
    }

    @Test
    fun mapLazy() {
        true.mapLazy(ifTrue = { 42 }, ifFalse = { failTest() }).assert(42)
        false.mapLazy(ifTrue = { failTest() }, ifFalse = { 42 }).assert(42)
    }

    class MapToSet {

        @Test
        fun testEmpty() {
            val lst: List<String> = listOf<String>()
            lst.mapToSet { it }.assertEmpty()
        }

        @Test
        fun testDuplicates() {
            (0 until 10).mapToSet { it.rem(2) }.apply {
                assertSize(size = 2, message = "since we either get 0 or 1, there will only be 2 elements")
                assertContainsAll(0, 1)
            }
        }

        @Test
        fun testNonDuplicates() {
            (0 until 10).mapToSet { "$it" }.apply {
                assertSize(10, "should map each element (and since non is duplicated)")
                for (i in 0 until 10) {
                    this.contains("$i").assertTrue()
                }
            }
        }
    }


    private class FirstChild : Parent()
    private class SecondChild : Parent()
    private open class Parent

    class IterableTMapToTypedArray {
        @Test
        fun empty() {
            setOf<Int>().mapToTypedArray<Int, String> { shouldNotBeCalled() }.assertEmpty()
        }

        @Test
        fun single() {
            val col: Collection<Int> = setOf(42)
            col.mapToTypedArray { "a" }.apply {
                assertSize(1)
                first().assert("a")
            }
            val secondCol: Collection<Int> = listOf(42)
            secondCol.mapToTypedArray { "$it" }.apply {
                assertSize(1)
                first().assert("42")
            }
        }

        @Test
        fun multiple() {
            assertCalled(times = 2) { shouldBeCalled: () -> Unit ->
                val col: Collection<Int> = setOf(42, 11)
                col.mapToTypedArray { shouldBeCalled();"$it" }.apply {
                    assertSize(2)
                    first().assert("42")
                    last().assert("11")
                }
            }
        }
    }

    class ListTMapToTypedArrayMapper {
        @Test
        fun empty() {
            listOf<String>().mapToTypedArray<String, Int> { _: String ->
                shouldNotBeCalled()
            }.assertSize(0)
        }

        @Test
        fun single() {
            listOf(42).mapToTypedArray { "a" }.apply {
                assertSize(1)
                first().assert("a")
            }
            listOf(42).mapToTypedArray { "$it" }.apply {
                assertSize(1)
                first().assert("42")
            }
        }

        @Test
        fun multiple() {
            assertCalled(times = 2) { shouldBeCalled: () -> Unit ->
                listOf(42, 11).mapToTypedArray { shouldBeCalled();"$it" }.apply {
                    assertSize(2)
                    first().assert("42")
                    last().assert("11")
                }
            }
        }
    }

}
