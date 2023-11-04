@file:Suppress("unused")

package csense.kotlin.extensions.collections.collection.mutable

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class SetTest {

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
}