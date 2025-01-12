@file:Suppress("unused")

package org.csenseoss.kotlin.extensions.collections.collection.mutable

import org.csenseoss.kotlin.tests.assertions.collections.iterable.*
import org.csenseoss.kotlin.tests.assertions.primitives.boolean.*
import kotlin.test.*

class AddTest {

    class AddAll {
        @Test
        fun testEmpty() {
            mutableListOf<String>().apply {
                addAll()
                assertEmpty()
            }
            mutableListOf<String>().apply {
                addAll("a")
                assert("a")
            }
            mutableListOf<String>().apply {
                addAll("a", "b")
                assert("a", "b")
            }
        }

        @Test
        fun testSingle() {
            mutableListOf("a").apply {
                addAll()
                assert("a")
            }
            mutableListOf("a").apply {
                addAll("a")

                assert("a", "a")
            }
            mutableListOf("a").apply {
                addAll("a", "b")
                assert("a", "a", "b")
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
                assert("a","c","a")
            }
            mutableListOf("a", "c").apply {
                addAll("a", "b")
                assert("a","c","a","b")
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
            lst.assert("item")
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
            lst.assert("testItem")
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
            lst.assert("asd")
        }

        @Test
        fun singleStarting() {
            val startLst: MutableList<String> = mutableListOf("123")
            startLst.addAll(items = null).assertTrue()
            startLst.assert("123")

            startLst.addAll(items = listOf()).assertTrue()
            startLst.assert("123")

            startLst.addAll(items = listOf("test")).assertTrue()
            startLst.assert("123", "test")
        }


        @Test
        fun multipleOnEmpty() {
            val startLst: MutableList<String> = mutableListOf()
            startLst.addAll(items = listOf("1234", "abc")).assertTrue()
            startLst.assert("1234", "abc")
        }

        @Test
        fun multipleStarting() {
            val startLst: MutableList<String> = mutableListOf("1234", "abc")

            startLst.addAll(items = null).assertTrue()

            startLst.assert("1234", "abc")

            startLst.addAll(items = listOf()).assertTrue()
            startLst.assert("1234", "abc")


            startLst.addAll(items = listOf("1")).assertTrue()
            startLst.assert("1234", "abc", "1")

            startLst.addAll(items = listOf("2", "3")).assertTrue()
            startLst.assert("1234", "abc", "1", "2", "3")

        }
    }

}