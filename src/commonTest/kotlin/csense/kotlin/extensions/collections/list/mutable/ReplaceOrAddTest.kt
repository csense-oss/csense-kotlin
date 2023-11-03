package csense.kotlin.extensions.collections.list.mutable

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class ReplaceOrAddTest {
    @Test
    fun empty() {
        val lst: MutableList<String> = mutableListOf()
        lst.replaceOrAdd(item = "new", index = 0)
        lst.assertSingle("new")
    }

    @Test
    fun emptyBadIndex() {
        val lst: MutableList<String> = mutableListOf()
        lst.replaceOrAdd(item = "new", index = 500)
        lst.assertSingle("new")
    }

    @Test
    fun emptyNegativeIndex() {
        val lst: MutableList<String> = mutableListOf()
        lst.replaceOrAdd(item = "new", index = -500)
        lst.assertSingle("new")
    }

    @Test
    fun singleIndexZero() {
        val lst: MutableList<String> = mutableListOf("first")
        lst.replaceOrAdd(item = "new", index = 0)
        lst.assertSingle("new")
    }

    @Test
    fun singleIndexOutOfBounds() {
        val lst: MutableList<String> = mutableListOf("first")
        lst.replaceOrAdd(item = "new", index = 1)
        lst.assertSize(2)
        lst.assertContainsInOrder("first", "new")
    }

    @Test
    fun multipleIndexInBunds(){
        val lst: MutableList<String> = mutableListOf("first", "second", "third")
        lst.replaceOrAdd(item = "new", index = 1)
        lst.assertSize(3)
        lst.assertContainsInOrder("first", "new", "third")

        lst.replaceOrAdd(item = "_", index = 2)
        lst.assertSize(3)
        lst.assertContainsInOrder("first", "new", "_")


        lst.replaceOrAdd(item = "_", index = 0)
        lst.assertSize(3)
        lst.assertContainsInOrder("_", "new", "_")


        lst.replaceOrAdd(item = "a", index = 3)
        lst.assertSize(4)
        lst.assertContainsInOrder("_", "new", "_","a")
    }

}