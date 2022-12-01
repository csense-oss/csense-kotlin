package csense.kotlin.patterns.lists

import csense.kotlin.testHelpers.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class ToLists {
    @Test
    fun empty() {
        listOf<String>().toLists().assertEmpty()
    }


    @Test
    fun single() {
        val lists = listOf("test").toLists()
        lists.assertContent()
        lists.size.assert(1)
        lists.assertContainsInOrder("test")
    }


    @Test
    fun multiple() {
        val lists = listOf("test", "1234").toLists()
        lists.assertContent()
        lists.size.assert(2)
        lists.assertContainsInOrder("test", "1234")
    }

}