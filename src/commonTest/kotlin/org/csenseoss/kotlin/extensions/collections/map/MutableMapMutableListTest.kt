package org.csenseoss.kotlin.extensions.collections.map

import org.csenseoss.kotlin.extensions.collections.map.mutable.mutableList.*
import org.csenseoss.kotlin.tests.assertions.*
import org.csenseoss.kotlin.tests.assertions.collections.iterable.*
import org.csenseoss.kotlin.tests.assertions.collections.map.*
import org.csenseoss.kotlin.tests.assertions.collections.map.iterable.*
import org.csenseoss.kotlin.tests.assertions.comparable.*
import kotlin.test.*

class MutableMapMutableListTest {
    class MutableMapKeyMutableListValueAppendValues {

        @Test
        fun emptyAndEmpty() {
            val result: MutableMap<String, MutableList<String>> = mutableMapOf()
            result.appendValues(mapOf())
            result.assertEmpty("empty + empty is still empty")
        }


        @Test
        fun emptySingle() {
            val result: MutableMap<String, MutableList<String>> = mutableMapOf()
            result.appendValues(mapOf("1" to mutableListOf("1")))
            result.assert("1" to listOf("1"))
        }


        @Test
        fun emptyMultiple() {
            val result: MutableMap<String, MutableList<String>> = mutableMapOf()
            result.appendValues(mapOf("1" to mutableListOf("11", "12"), "2" to mutableListOf("21", "22")))

            result.assert(
                "1" to listOf("11","12"),
                "2" to listOf("21", "22")
            )
        }

    }
}