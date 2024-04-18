package org.csenseoss.kotlin.specificExtensions.list

import csense.kotlin.tests.assertions.*
import org.csenseoss.kotlin.specificExtensions.list.*
import kotlin.test.*

class ListModificationsTest {

    @Test
    fun listTModification() {
        val first: ListModifications<String> = listOf("a").modification
        val second: ListModifications<String> = listOf("b").modification
        (first != second).assertTrue()
    }

    class ListModificationsTReplaceAllWith {
        @Test
        fun empty() {
            listOf<String>().modification.replaceAllWith(
                predicate = { shouldNotBeCalled() },
                replaceWith = { shouldNotBeCalled() }
            )
        }

        @Test
        fun single() {
            TODO()
        }

        @Test
        fun multiple() {
            TODO()
        }
    }


}