@file:Suppress("unused")

package csense.kotlin.extensions.collections.list

import csense.kotlin.extensions.collections.*
import csense.kotlin.extensions.collections.collection.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class MutableExtensionsTest {

    @Test
    fun findAndRemove() {
        val list = mutableListOf("a", "b")
        list.findAndRemove { it == "a" }
        list.assertSize(1, "should have removed a")
        list.first().assert("b")
        list.addAll(listOf("a", "a", "b"))
        list.findAndRemove { it == "a" }
        list.assertSize(3, "should have 1 a and 2 b's ")
        list.first().assert("b")
        list.findAndRemove { it == "b" }
        list.assertSize(2, "should have 1 a and 1 b")
        list.first().assert("a")
        list.last().assert("b")
    }

    @Test
    fun findAndRemoveAll() {
        val list = mutableListOf("a", "a", "b", "abc")
        list.findAndRemoveAll { it == "a" }
        list.assertSize(2)
        list.findAndRemoveAll { it == "abc" }
        list.assertSize(1)
        list.findAndRemoveAll { it == "x" }
        list.assertSize(1)

    }

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
    fun replaceItem() {
        val list = mutableListOf("1", "2")
        list.replace("3", 1)
        list[1].assert("3")
        list[0].assert("1")
    }

    class Set {
        @Test
        fun set() {
            val list = mutableListOf(
                42, 2, 1
            )
            list.setAll(listOf())
            list.assertEmpty("should have removed all things before setting")
            list.setAll(listOf(989))
            list.assertSize(1)
            list.first().assert(989)
            list.setAll(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
            list.assertSize(10)
            list.first().assert(1)
            list.last().assert(10)
        }

        @Test
        fun setSingle() {
            val list = mutableListOf<String>()
            list.add("test")
            list.add("test2")
            list.set("nope")
            list.assertSize(1, "setting a single item should give 1 item")
            list.first().assert("nope", "should have set item")
            list.set("test23")
            list.assertSize(1)
            list.first().assert("test23")
        }

    }


    @Test
    fun removeAtOr() {
        val list = mutableListOf("ab", "ba")
        list.removeAtOr(0, "").assert("ab")
        list.assertSize(1)
        list.removeAtOr(10, "qwe").assert("qwe")
        list.assertSize(1)
    }

    class ReplaceToReplace {
        @Test
        fun replaceWithEmpty() {
            val lst = mutableListOf<String>()
            lst.replace("", "")
            lst.assertSize(0)
        }

        @Test
        fun replaceWithSingle() {
            val lst = mutableListOf("a")
            lst.replace("b", "c")
            lst.assertContains("a")
            lst.assertContainsNot("b")
            lst.assertContainsNot("c")
            lst.replace("a", "b")
            lst.assertContainsNot("a")
            lst.assertContains("b")
            lst.assertContainsNot("c")
        }

        @Test
        fun replaceWithMultiple() {
            val lst = mutableListOf("a", "b", "c")
            lst.replace("d", "e")
            lst.assertContainsNot("e")
            lst.replace("a", "b")
            lst.assertContainsNot("a")
            lst.count { it == "b" }.assert(2)
            lst.replace("b", "c")
            lst.count { it == "c" }.assert(2, "should only replace the first encounter.")
            lst.count { it == "b" }.assert(1, "should only replace the first encounter.")
        }

    }


}

class MutableListTest {
    @Test
    fun mutableListTRemoveLast() {

        class MutableListTRemoveLast {
            @Test
            fun empty() {
                val lst = mutableListOf<String>()
                val last = lst.removeLast()
                last.assertNull("there are no elements")
            }

            @Test
            fun single() {
                val lst = mutableListOf("test")
                val last = lst.removeLast()
                last.assert("test")
                lst.assertSize(0, "there should be no elements left")
            }

            @Test
            fun multiple() {
                val lst = mutableListOf("test", "1234", "abc")
                val last = lst.removeLast()
                last.assert("abc")
                lst.assertSize(2, "there should still be 2 elements left")
                lst.assertContains("test")
                lst.assertContains("1234")
            }
        }

    }

    @Test
    fun mutableListTRemoveFirst() {
        class MutableListTRemoveFirst {
            @Test
            fun empty() {
                val lst = mutableListOf<String>()
                val last = lst.removeFirst()
                last.assertNull("there are no elements")
            }

            @Test
            fun single() {
                val lst = mutableListOf("test")
                val last = lst.removeFirst()
                last.assert("test")
                lst.assertSize(0, "there should be no elements left")
            }

            @Test
            fun multiple() {
                val lst = mutableListOf("test", "1234", "abc")
                val last = lst.removeFirst()
                last.assert("test")
                lst.assertSize(2, "there should still be 2 elements left")
                lst.assertContains("abc")
                lst.assertContains("1234")
            }
        }

    }

    class MutableListTAddAll {
        @Test
        fun emptyListBadIndexes() {
            val lst = mutableListOf<String>()
            lst.addAll(-1, listOf<String>() as Iterable<String>).assertFalse()
            lst.addAll(-50, listOf<String>() as Iterable<String>).assertFalse()
            lst.addAll(0, listOf<String>() as Iterable<String>).assertTrue()
            lst.assertEmpty()
        }

        @Test
        fun emptyListValidInsertSingle() {
            val lst = mutableListOf<String>()
            lst.addAll(0, listOf("asd") as Iterable<String>).assertTrue()
            lst.assertSize(1)
            lst.first().assert("asd")
        }

        @Test
        fun emptyListValidInsertMultiple() {
            val lst = mutableListOf<String>()
            lst.addAll(0, listOf("asd", "1234") as Iterable<String>).assertTrue()
            lst.assertSize(2)
            lst.assertContainsAll("asd", "1234")
        }

        @Test
        fun singleListInvalidInsertSingle() {
            val lst = mutableListOf("456")
            lst.addAll(2, listOf("asd") as Iterable<String>).assertFalse()
            lst.assertSize(1)
            lst.assertContainsAll("456")
        }

        @Test
        fun singleListInsertAtSizeIsValid() {
            val lst = mutableListOf("456")
            lst.addAll(1, listOf("asd") as Iterable<String>).assertTrue()
            lst.assertSize(2)
            lst.assertContainsAll("456", "asd")
        }

        @Test
        fun multipleListInsertInMiddle() {
            val lst = mutableListOf("456", "qwerty", "iop")
            lst.addAll(2, listOf("---", "777", "---") as Iterable<String>).assertTrue()
            lst.assertSize(6)
            lst.assertContainsAll("456", "qwerty", "---", "777", "---", "iop")
        }
    }

    class MutableListTReplaceFirst {
        @Test
        fun empty() {
            val lst = mutableListOf<String>()
            val didReplace = lst.replaceFirst("test") {
                true
            }
            didReplace.assertFalse()
            lst.assertEmpty()
        }

        @Test
        fun singleFound() {
            val lst = mutableListOf("a")
            val didReplace = lst.replaceFirst("2") {
                true
            }
            didReplace.assertTrue()
            lst.assertSize(1)
            lst.first().assert("2")
        }

        @Test
        fun singleNotFound() {
            val lst = mutableListOf("b")
            val didReplace = lst.replaceFirst("1") {
                false
            }
            didReplace.assertFalse()
            lst.assertSize(1)
            lst.first().assert("b")
        }

        @Test
        fun multipleNoneFound() {
            val lst = mutableListOf("b", "qwerty")
            val didReplace = lst.replaceFirst("1") {
                false
            }
            didReplace.assertFalse()
            lst.assertSize(2)
            lst.assertContainsInOrder("b", "qwerty")
        }

        @Test
        fun multipleAllFound() {
            val lst = mutableListOf("b", "qwerty")
            val didReplace = lst.replaceFirst("1") {
                true
            }
            didReplace.assertTrue()
            lst.assertSize(2)
            lst.assertContainsInOrder("1", "qwerty")
        }

        @Test
        fun multipleOneFound() {
            val lst = mutableListOf("1234", "c", "qwerty")
            val didReplace = lst.replaceFirst("1") {
                it == "c"
            }
            didReplace.assertTrue()
            lst.assertSize(3)
            lst.assertContainsInOrder("1234", "1", "qwerty")
        }
    }
}