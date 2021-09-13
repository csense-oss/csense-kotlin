@file:Suppress("unused")

package csense.kotlin.extensions

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class MappingTest {

    @Test
    fun mapOptional() {
        val strNull: String? = null
        strNull.mapOptional(42, 0).assert(0, "should map to ifNull")
        @Suppress("RedundantNullableReturnType")
        val strNotNull: String? = "NotNull"
        strNotNull.mapOptional(42, 0).assert(42, "should map to ifNotNull")
    }

    @Test
    fun mapLazyOptional() {
        val strNull: String? = null
        assertCalled {
            strNull.mapLazyOptional(
                ifNotNull = { failTest() },
                ifNull = { it() }
            )
        }
        @Suppress("RedundantNullableReturnType")
        val strNotNull: String? = "NotNull"
        assertCalled {
            strNotNull.mapLazyOptional(
                ifNotNull = { it() },
                ifNull = { failTest() }
            )
        }
    }

    @Test
    fun map() {
        true.map(42, 0).assert(42)
        false.map(42, 0).assert(0)
    }

    @Test
    fun mapLazy() {
        true.mapLazy({ 42 }, { failTest() }).assert(42)
        false.mapLazy({ failTest() }, { 42 }).assert(42)
    }

    class MapToSet {

        @Test
        fun testEmpty() {
            val lst = listOf<String>()
            lst.mapToSet { it }.assertEmpty()
        }

        @Test
        fun testDuplicates() {
            (0 until 10).mapToSet { it.rem(2) }.apply {
                assertSize(2, "since we either get 0 or 1, there will only be 2 elements")
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

    @Test
    fun tMapIfInstanceOrThis() {
        val parent = Parent()
        parent.mapIfInstanceOrThis<Parent, FirstChild> { shouldNotBeCalled() }.assertAs(parent, "its not a FirstChild")
        parent.mapIfInstanceOrThis<Parent, SecondChild> { shouldNotBeCalled() }
            .assertAs(parent, "its not a SecondChild")

        val firstChild: Parent = FirstChild()
        assertCalled { called ->
            firstChild.mapIfInstanceOrThis<Parent, FirstChild> { called(); it }.assertAs(firstChild)
        }
        firstChild.mapIfInstanceOrThis<Parent, SecondChild> { shouldNotBeCalled() }.assertAs(firstChild)

        val secondChild: Parent = SecondChild()
        secondChild.mapIfInstanceOrThis<Parent, FirstChild> { shouldNotBeCalled() }.assertAs(secondChild)
        assertCalled { called ->
            secondChild.mapIfInstanceOrThis<Parent, SecondChild> { called(); it }.assertAs(secondChild)
        }
    }

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
            assertCalled(times = 2) { shouldBeCalled ->
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
            listOf<String>().mapToTypedArray<String, Int> {
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
            assertCalled(times = 2) { shouldBeCalled ->
                listOf(42, 11).mapToTypedArray { shouldBeCalled();"$it" }.apply {
                    assertSize(2)
                    first().assert("42")
                    last().assert("11")
                }
            }
        }
    }
}
