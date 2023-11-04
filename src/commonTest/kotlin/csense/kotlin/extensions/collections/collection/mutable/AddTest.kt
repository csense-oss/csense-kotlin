@file:Suppress("unused")

package csense.kotlin.extensions.collections.collection.mutable

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class AddTest {

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
            val lst: MutableList<String> = mutableListOf()
            lst.addIfNotNull(null).assertFalse("should not be added to list")
            lst.assertEmpty()
        }

        @Test
        fun onNotNull() {
            val lst: MutableList<String> = mutableListOf()
            lst.addIfNotNull("item").assertTrue("should be added")
            lst.assertSingle("item")
        }

    }

    class MutableCollectionEAddIf {

        @Test
        fun ifFalse() {
            val lst: MutableList<String> = mutableListOf()
            lst.addIf(condition = false, "").assertFalse()
            lst.assertEmpty()
        }


        @Test
        fun ifTrue() {
            val lst: MutableList<String> = mutableListOf()
            lst.addIf(condition = true, "testItem").assertTrue()
            lst.assertSingle("testItem")
        }
    }



    class MutableCollectionEAddAllItems {

        @Test
        fun nullLst() {
            val lst: MutableList<String> = mutableListOf()
            lst.addAll(items = null).assertTrue()
            lst.assertEmpty()
        }

        @Test
        fun empty() {
            val lst: MutableList<String> = mutableListOf()
            lst.addAll(items = listOf()).assertTrue()
            lst.assertEmpty()
        }


        @Test
        fun singleOnEmpty() {
            val lst: MutableList<String> = mutableListOf()
            lst.addAll(items = listOf("asd")).assertTrue()
            lst.assertSingle("asd")
        }

        @Test
        fun singleStarting() {
            val startLst: MutableList<String> = mutableListOf("123")
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
            val startLst: MutableList<String> = mutableListOf()
            startLst.addAll(items = listOf("1234", "abc")).assertTrue()
            startLst.assertSize(2)
            startLst.assertContainsInOrder("1234", "abc")
        }

        @Test
        fun multipleStarting() {
            val startLst: MutableList<String> = mutableListOf("1234", "abc")

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