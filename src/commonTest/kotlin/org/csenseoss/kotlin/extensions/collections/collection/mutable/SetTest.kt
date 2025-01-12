@file:Suppress("unused")

package org.csenseoss.kotlin.extensions.collections.collection.mutable

import org.csenseoss.kotlin.tests.assertions.collections.iterable.*
import kotlin.test.*

class SetTest {

    class SetAllCollection {

        @Test
        fun testEmpty() {
            mutableListOf<String>().apply {
                setAll(listOf())
                assertEmpty()
            }
            mutableListOf<String>().apply {
                setAll(listOf("a"))
                assert("a")
            }
            mutableListOf<String>().apply {
                setAll(listOf("a", "b"))
                assert("a", "b")
            }
        }

        @Test
        fun testSingle() {
            mutableListOf("a").apply {
                setAll(listOf())
                assertEmpty()
            }
            mutableListOf("a").apply {
                setAll(listOf("a"))
                assert("a")
            }
            mutableListOf("a").apply {
                setAll(listOf("a", "b"))
                assert("a", "b")
            }
        }

        @Test
        fun testMultiple() {
            mutableListOf("a", "c").apply {
                setAll(listOf())
                assertEmpty()
            }
            mutableListOf("a", "c").apply {
                setAll(listOf("a"))
                assert("a")
            }
            mutableListOf("a", "c").apply {
                setAll(listOf("a", "b"))
                assert("a", "b")
            }
        }
    }

    class MutableCollectionESet {

        @Test
        fun testEmpty() {
            mutableListOf<String>().apply {
                set("a")
                assert("a")
            }
        }

        @Test
        fun testSingle() {
            mutableListOf("b").apply {
                set("a")
                assert("a")
            }
        }

        @Test
        fun testMultiple() {
            mutableListOf("a", "c").apply {
                set("d")
                assert("d")
            }
        }
    }


    class SetAllItems {
        @Test
        fun testEmpty() {
            mutableListOf<String>().apply {
                setAll()
                assertEmpty()
            }
            mutableListOf<String>().apply {
                setAll("a")
                assert("a")
            }
            mutableListOf<String>().apply {
                setAll("a", "b")
                assert("a", "b")
            }
        }

        @Test
        fun testSingle() {
            mutableListOf("a").apply {
                setAll()
                assertEmpty()
            }
            mutableListOf("a").apply {
                setAll("a")
                assert("a")
            }
            mutableListOf("a").apply {
                setAll("a", "b")
                assert("a", "b")
            }
        }

        @Test
        fun testMultiple() {
            mutableListOf("a", "c").apply {
                setAll()
                assertEmpty()
            }
            mutableListOf("a", "c").apply {
                setAll("a")
                assert("a")
            }
            mutableListOf("a", "c").apply {
                setAll("a", "b")
                assert("a", "b")
            }
        }
    }
}