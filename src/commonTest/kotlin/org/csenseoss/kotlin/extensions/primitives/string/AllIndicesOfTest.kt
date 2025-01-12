package org.csenseoss.kotlin.extensions.primitives.string

import org.csenseoss.kotlin.tests.assertions.collections.iterable.*
import org.csenseoss.kotlin.tests.assertions.comparable.*
import kotlin.test.*

class AllIndicesOfTest {

    @Test
    fun empty() {
        "".allIndicesOf("", searchByWord = false, ignoreCase = false)
            .assertEmpty("nothing in nothing is nothing")
    }

    @Test
    fun some() {
        val textString = "\"a very funny quote\""
        textString.allIndicesOf("\"", searchByWord = false, ignoreCase = false).apply {
            assertSize(expectedCount = 2, message = "since there are 2 \" in the text")
            first().assert(expected = 0, message = "since the first \" is at the first location")
            last().assert(expected = textString.length - 1, message = "since the last \" is at the end")
        }

        textString.allIndicesOf("abc", searchByWord = false, ignoreCase = false).assertEmpty()
    }
}