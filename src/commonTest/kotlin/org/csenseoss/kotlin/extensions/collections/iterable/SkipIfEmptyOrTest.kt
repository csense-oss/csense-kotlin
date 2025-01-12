package org.csenseoss.kotlin.extensions.collections.iterable

import org.csenseoss.kotlin.tests.assertions.*
import org.csenseoss.kotlin.tests.assertions.general.*
import kotlin.test.*

class SkipIfEmptyOrTest {


    @Test
    fun iterableTSkipIfEmptyOr() {
        val empty: Iterable<String> = arrayListOf()
        empty.skipIfEmptyOr { shouldNotBeCalled() }

        val single: Iterable<String> = arrayListOf("1")
        assertCalled { single.skipIfEmptyOr(it) }

        val multiple: Iterable<String> = arrayListOf("a", "b")
        assertCalled { multiple.skipIfEmptyOr(it) }
    }

}