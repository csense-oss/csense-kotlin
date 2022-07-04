package csense.kotlin.specificExtensions.list

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class ListModificationsTest {

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