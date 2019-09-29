@file:Suppress("unused")

package csense.kotlin.extensions.collections.list

import csense.kotlin.extensions.collections.*
import csense.kotlin.test.assertions.*
import kotlin.test.*

class MutableExtensionsKtTest {

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

    @Test
    fun replace() {
        val list = mutableListOf("1", "2")
        list.replace("3", 1)
        list[1].assert("3")
        list[0].assert("1")
    }

    @Test
    fun set() {
        val list = mutableListOf(
                42, 2, 1
        )
        list.set(listOf())
        list.assertEmpty("should have removed all things before setting")
        list.set(listOf(989))
        list.assertSize(1)
        list.first().assert(989)
        list.set(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
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

    @Ignore
    @Test
    fun removeAll() {

    }

    @Test
    fun removeAtOr() {
        val list = mutableListOf("ab", "ba")
        list.removeAtOr(0, "").assertNotNullAndEquals("ab")
        list.assertSize(1)
        list.removeAtOr(10, "qwe").assertNotNullAndEquals("qwe")
        list.assertSize(1)
    }

    object Replace {
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