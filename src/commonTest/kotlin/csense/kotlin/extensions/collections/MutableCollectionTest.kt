@file:Suppress("unused")

package csense.kotlin.extensions.collections

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class MutableCollectionTest {

    class SetAllCollection {

        @Test
        fun testEmpty() {
            mutableListOf<String>().apply {
                setAll(listOf())
                assertSize(0)
            }
            mutableListOf<String>().apply {
                setAll(listOf("a"))
                assertSize(1)
                assertContains("a")
            }
            mutableListOf<String>().apply {
                setAll(listOf("a", "b"))
                assertSize(2)
                assertContainsAll("a", "b")
            }
        }

        @Test
        fun testSingle() {
            mutableListOf("a").apply {
                setAll(listOf())
                assertSize(0)
            }
            mutableListOf("a").apply {
                setAll(listOf("a"))
                assertSize(1)
                assertContains("a")
            }
            mutableListOf("a").apply {
                setAll(listOf("a", "b"))
                assertSize(2)
                assertContainsAll("a", "b")
            }
        }

        @Test
        fun testMultiple() {
            mutableListOf("a", "c").apply {
                setAll(listOf())
                assertSize(0)
            }
            mutableListOf("a", "c").apply {
                setAll(listOf("a"))
                assertSize(1)
                assertContains("a")
            }
            mutableListOf("a", "c").apply {
                setAll(listOf("a", "b"))
                assertSize(2)
                assertContainsAll("a", "b")
            }
        }
    }

    class MutableCollectionESet {

        @Test
        fun testEmpty() {
            mutableListOf<String>().apply {
                set("a")
                assertSize(1)
                assertContains("a")
            }
        }

        @Test
        fun testSingle() {
            mutableListOf("b").apply {
                set("a")
                assertSize(1)
                assertContains("a")
            }
        }

        @Test
        fun testMultiple() {
            mutableListOf("a", "c").apply {
                set("d")
                assertSize(1)
                assertContains("d")
            }
        }
    }


    class SetAllItems {
        @Test
        fun testEmpty() {
            mutableListOf<String>().apply {
                setAll()
                assertSize(0)
            }
            mutableListOf<String>().apply {
                setAll("a")
                assertSize(1)
                assertContains("a")
            }
            mutableListOf<String>().apply {
                setAll("a", "b")
                assertSize(2)
                assertContainsAll("a", "b")
            }
        }

        @Test
        fun testSingle() {
            mutableListOf("a").apply {
                setAll()
                assertSize(0)
            }
            mutableListOf("a").apply {
                setAll("a")
                assertSize(1)
                assertContains("a")
            }
            mutableListOf("a").apply {
                setAll("a", "b")
                assertSize(2)
                assertContainsAll("a", "b")
            }
        }

        @Test
        fun testMultiple() {
            mutableListOf("a", "c").apply {
                setAll()
                assertSize(0)
            }
            mutableListOf("a", "c").apply {
                setAll("a")
                assertSize(1)
                assertContains("a")
            }
            mutableListOf("a", "c").apply {
                setAll("a", "b")
                assertSize(2)
                assertContainsAll("a", "b")
            }
        }
    }

    class AddAll {
        @Test
        fun testEmpty() {
            mutableListOf<String>().apply {
                addAll()
                assertSize(0)
            }
            mutableListOf<String>().apply {
                addAll("a")
                assertSize(1)
                assertContains("a")
            }
            mutableListOf<String>().apply {
                addAll("a", "b")
                assertSize(2)
                assertContainsAll("a", "b")
            }
        }

        @Test
        fun testSingle() {
            mutableListOf("a").apply {
                addAll()
                assertSize(1)
                assertContains("a")
            }
            mutableListOf("a").apply {
                addAll("a")
                assertSize(2)
                assertContains("a")
            }
            mutableListOf("a").apply {
                addAll("a", "b")
                assertSize(3)
                assertContainsAll("a", "b")
            }
        }

        @Test
        fun testMultiple() {
            mutableListOf("a", "c").apply {
                addAll()
                assertSize(2)
                assertContains("a", "c")
            }
            mutableListOf("a", "c").apply {
                addAll("a")
                assertSize(3)
                assertContains("a", "c")
            }
            mutableListOf("a", "c").apply {
                addAll("a", "b")
                assertSize(4)
                assertContainsAll("a", "b", "c")
            }
        }
    }


    class MutableCollectionEAddIfNotNull {

        @Test
        fun onNull() {
            val lst = mutableListOf<String>()
            lst.addIfNotNull(null).assertFalse("should not be added to list")
            lst.assertEmpty()
        }

        @Test
        fun onNotNull() {
            val lst = mutableListOf<String>()
            lst.addIfNotNull("item").assertTrue("should be added")
            lst.assertSingle("item")
        }

    }

    class MutableCollectionEAddIf {

        @Test
        fun ifFalse() {
            val lst = mutableListOf<String>()
            lst.addIf(condition = false, "").assertFalse()
            lst.assertEmpty()
        }


        @Test
        fun ifTrue() {
            val lst = mutableListOf<String>()
            lst.addIf(condition = true, "testItem").assertTrue()
            lst.assertSingle("testItem")
        }
    }

    class MutableCollectionERemoveIf {

        @Test
        fun ifFalseNotThere() {
            val lst = mutableListOf<String>()
            lst.removeIf(condition = false, "item").assertFalse()
            lst.assertEmpty()
        }

        @Test
        fun ifFalseThere() {
            val lst = mutableListOf<String>("item")
            lst.removeIf(condition = false, "item").assertFalse()
            lst.assertSingle("item")
        }


        @Test
        fun ifTrueNotThere() {
            val lst = mutableListOf<String>("item")
            lst.removeIf(condition = true, "testItem").assertFalse()
            lst.assertSingle("item")
        }

        @Test
        fun ifTrueThere() {
            val lst = mutableListOf<String>("item")
            lst.removeIf(condition = true, "item").assertTrue()
            lst.assertEmpty()
        }

        @Test
        fun ifTrueThereMultipleTimes() {
            val lst = mutableListOf<String>("item", "item")
            lst.removeIf(condition = true, "item").assertTrue()
            lst.assertSingle("item")
            lst.removeIf(condition = true, "item").assertTrue()
            lst.assertEmpty()
        }

    }

    class MutableCollectionERemoveIfNotNull {

        @Test
        fun notThereAndNullEmpty() {
            val lst = mutableListOf<String>()
            lst.removeIfNotNull(null).assertFalse()
            lst.assertEmpty()
        }

        @Test
        fun notThereAndNullSingle() {
            val lst = mutableListOf<String>("lst")
            lst.removeIfNotNull(null).assertFalse()
            lst.assertSingle("lst")
        }

        @Test
        fun notThere() {
            val lst = mutableListOf<String>("lst")
            lst.removeIfNotNull("item").assertFalse()
            lst.assertSingle("lst")
        }

        @Test
        fun thereSingle() {
            val lst = mutableListOf<String>("lst")
            lst.removeIfNotNull("lst").assertTrue()
            lst.assertEmpty()
        }

        @Test
        fun thereMultiple() {
            val lst = mutableListOf<String>("lst", "lst")
            lst.removeIfNotNull("lst").assertTrue()
            lst.assertSingle("lst")
            lst.removeIfNotNull("lst").assertTrue()
            lst.assertEmpty()
        }

    }

    class MutableCollectionEAddAllItems {

        @Test
        fun nullLst() {
            val lst = mutableListOf<String>()
            lst.addAll(items = null).assertTrue()
            lst.assertEmpty()
        }

        @Test
        fun empty() {
            val lst = mutableListOf<String>()
            lst.addAll(items = listOf()).assertTrue()
            lst.assertEmpty()
        }


        @Test
        fun singleOnEmpty() {
            val lst = mutableListOf<String>()
            lst.addAll(items = listOf("asd")).assertTrue()
            lst.assertSingle("asd")
        }

        @Test
        fun singleStarting() {
            val startLst = mutableListOf("123")
            startLst.addAll(items = null).assertTrue()
            startLst.assertSingle("123")

            startLst.addAll(items = listOf()).assertTrue()
            startLst.assertSingle("123")

            startLst.addAll(items = listOf("test")).assertTrue()
            startLst.assertSize(2)
            startLst.assertContainsInOrder("123", "test")
        }


        @Test
        fun multipleOnEmpty() {
            val startLst = mutableListOf<String>()
            startLst.addAll(items = listOf("1234", "abc")).assertTrue()
            startLst.assertSize(2)
            startLst.assertContainsInOrder("1234", "abc")
        }

        @Test
        fun multipleStarting() {
            val startLst = mutableListOf("1234", "abc")

            startLst.addAll(items = null).assertTrue()
            startLst.assertSize(2)
            startLst.assertContainsInOrder("1234", "abc")

            startLst.addAll(items = listOf()).assertTrue()
            startLst.assertSize(2)
            startLst.assertContainsInOrder("1234", "abc")


            startLst.addAll(items = listOf("1")).assertTrue()
            startLst.assertSize(3)
            startLst.assertContainsInOrder("1234", "abc", "1")

            startLst.addAll(items = listOf("2", "3")).assertTrue()
            startLst.assertSize(5)
            startLst.assertContainsInOrder("1234", "abc", "1", "2", "3")

        }
    }
}