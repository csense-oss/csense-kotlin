@file:Suppress("unused")

package csense.kotlin.extensions.collections

import csense.kotlin.tests.assertions.assertContains
import csense.kotlin.tests.assertions.assertContainsAll
import csense.kotlin.tests.assertions.assertSize
import kotlin.test.Test

class MutableCollectionTest {

    class SetCollection {

        @Test
        fun testEmpty() {
            mutableListOf<String>().apply {
                set(listOf())
                assertSize(0)
            }
            mutableListOf<String>().apply {
                set(listOf("a"))
                assertSize(1)
                assertContains("a")
            }
            mutableListOf<String>().apply {
                set(listOf("a", "b"))
                assertSize(2)
                assertContainsAll("a", "b")
            }
        }

        @Test
        fun testSingle() {
            mutableListOf("a").apply {
                set(listOf())
                assertSize(0)
            }
            mutableListOf("a").apply {
                set(listOf("a"))
                assertSize(1)
                assertContains("a")
            }
            mutableListOf("a").apply {
                set(listOf("a", "b"))
                assertSize(2)
                assertContainsAll("a", "b")
            }
        }

        @Test
        fun testMultiple() {
            mutableListOf("a", "c").apply {
                set(listOf())
                assertSize(0)
            }
            mutableListOf("a", "c").apply {
                set(listOf("a"))
                assertSize(1)
                assertContains("a")
            }
            mutableListOf("a", "c").apply {
                set(listOf("a", "b"))
                assertSize(2)
                assertContainsAll("a", "b")
            }
        }
    }

    class SetItem {

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
}