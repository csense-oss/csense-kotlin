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


}