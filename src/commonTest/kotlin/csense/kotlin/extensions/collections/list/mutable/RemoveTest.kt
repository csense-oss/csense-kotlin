package csense.kotlin.extensions.collections.list.mutable

import csense.kotlin.extensions.collections.list.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class RemoveTest {

    class MutableListTRemoveAll {


        @Suppress("EmptyRange")
        @Test
        fun testOutOfBoundsEmpty() {
            val lst = mutableListOf<String>()
            lst.removeAll(0 until 2).assertFalse("should not remove anything")
            lst.removeAll(0 until 1).assertFalse("should not remove anything")
            lst.removeAll(-40 until 1).assertFalse("should not remove anything")
            lst.removeAll(5 until 2).assertFalse("should not remove anything")
        }

        @Suppress("EmptyRange")
        @Test
        fun testOutOfBoundsContent() {
            val lst = mutableListOf("a", "b", "c")
            lst.removeAll(0 until 5).assertFalse("should not remove anything")
            lst.removeAll(0 until 4).assertFalse("should not remove anything")
            lst.removeAll(-40 until 1).assertFalse("should not remove anything")
            lst.removeAll(5 until 2).assertFalse("should not remove anything")
        }

        @Test
        fun testSingle() {
            val lst = mutableListOf("a")
            lst.removeAll(0 until 1).assertTrue("should remove only 1 element")
            lst.assertEmpty()
        }

        @Test
        fun testMultipleRemoveSingleFirst() {
            val lst = mutableListOf("a", "b")
            lst.removeAll(0 until 1).assertTrue("should remove only 1 element")
            lst.assertSize(1)
            lst.first().assert("b")
        }

        @Test
        fun testMultipleRemoveSingleLast() {
            val lst = mutableListOf("a", "b")
            lst.removeAll(1 until 2).assertTrue("should remove only 1 element")
            lst.assertSize(1)
            lst.first().assert("a")
        }

        @Test
        fun testMultipleRemoveAll() {
            val lst = mutableListOf("a", "b")
            lst.removeAll(0 until 2).assertTrue("should remove 2 element")
            lst.assertEmpty()

            val longList = mutableListOf("a").repeatToSize(50).toMutableList()
            longList.assertSize(50)
            longList.removeAll(0 until 50)
            longList.assertEmpty()
        }

        @Test
        fun testMultipleRemoveMiddle() {
            val lst = mutableListOf("a", "b", "c", "d")
            lst.removeAll(1 until 3).assertTrue("should remove only 2 element") //b,c should be removed
            lst.assertSize(2)
            lst.first().assert("a")
            lst.last().assert("d")
        }

    }

    @Test
    fun removeAtOr() {
        val list: MutableList<String> = mutableListOf("ab", "ba")
        list.removeAtOr(index = 0, default = "").assert("ab")
        list.assertSize(size = 1)
        list.removeAtOr(index = 10, default = "qwe").assert("qwe")
        list.assertSize(size = 1)
    }

    class RemoveFirstOrNullTest {
        @Test
        fun empty() {
            val lst: MutableList<String?> = mutableListOf()
            val last = lst.removeFirstOrNull()
            last.assertNull("there are no elements")
        }

        @Test
        fun single() {
            val lst = mutableListOf("test")
            val last = lst.removeFirstOrNull()
            last.assert("test")
            lst.assertSize(0, "there should be no elements left")
        }

        @Test
        fun multiple() {
            val lst = mutableListOf("test", "1234", "abc")
            val last = lst.removeFirstOrNull()
            last.assert("test")
            lst.assertSize(2, "there should still be 2 elements left")
            lst.assertContains("abc")
            lst.assertContains("1234")
        }
    }
}