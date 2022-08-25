package csense.kotlin.specificExtensions.list

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class ListModificationsTest {

    @Test
    fun listTModification() {
        val first = listOf("a").modification
        val second = listOf("b").modification
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

    }


}