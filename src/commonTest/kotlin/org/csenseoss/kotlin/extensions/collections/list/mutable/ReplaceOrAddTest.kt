package org.csenseoss.kotlin.extensions.collections.list.mutable

import org.csenseoss.kotlin.tests.assertions.collections.iterable.*
import kotlin.test.*

class ReplaceOrAddTest {
    @Test
    fun empty() {
        val lst: MutableList<String> = mutableListOf()
        lst.replaceOrAdd(item = "new", index = 0)
        lst.assert("new")
    }

    @Test
    fun emptyBadIndex() {
        val lst: MutableList<String> = mutableListOf()
        lst.replaceOrAdd(item = "new", index = 500)
        lst.assert("new")
    }

    @Test
    fun emptyNegativeIndex() {
        val lst: MutableList<String> = mutableListOf()
        lst.replaceOrAdd(item = "new", index = -500)
        lst.assert("new")
    }

    @Test
    fun singleIndexZero() {
        val lst: MutableList<String> = mutableListOf("first")
        lst.replaceOrAdd(item = "new", index = 0)
        lst.assert("new")
    }

    @Test
    fun singleIndexOutOfBounds() {
        val lst: MutableList<String> = mutableListOf("first")
        lst.replaceOrAdd(item = "new", index = 1)
        lst.assert("first", "new")
    }

    @Test
    fun multipleIndexInBunds(){
        val lst: MutableList<String> = mutableListOf("first", "second", "third")
        lst.replaceOrAdd(item = "new", index = 1)
        lst.assert("first", "new", "third")

        lst.replaceOrAdd(item = "_", index = 2)
        lst.assert("first", "new", "_")


        lst.replaceOrAdd(item = "_", index = 0)
        lst.assert("_", "new", "_")


        lst.replaceOrAdd(item = "a", index = 3)
        lst.assert("_", "new", "_","a")
    }

}