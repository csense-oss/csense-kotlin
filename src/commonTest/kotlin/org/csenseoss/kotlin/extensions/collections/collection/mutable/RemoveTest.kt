@file:Suppress("unused")

package org.csenseoss.kotlin.extensions.collections.collection.mutable

import org.csenseoss.kotlin.tests.assertions.*
import org.csenseoss.kotlin.tests.assertions.collections.iterable.*
import org.csenseoss.kotlin.tests.assertions.primitives.boolean.*
import kotlin.test.*

class RemoveTest {
    class MutableCollectionERemoveIf {

        @Test
        fun ifFalseNotThere() {
            val lst: MutableList<String> = mutableListOf()
            lst.removeIf(condition = false, "item").assertFalse()
            lst.assertEmpty()
        }

        @Test
        fun ifFalseThere() {
            val lst: MutableList<String> = mutableListOf("item")
            lst.removeIf(condition = false, "item").assertFalse()
            lst.assert("item")
        }


        @Test
        fun ifTrueNotThere() {
            val lst: MutableList<String> = mutableListOf("item")
            lst.removeIf(condition = true, "testItem").assertFalse()
            lst.assert("item")
        }

        @Test
        fun ifTrueThere() {
            val lst: MutableList<String> = mutableListOf("item")
            lst.removeIf(condition = true, "item").assertTrue()
            lst.assertEmpty()
        }

        @Test
        fun ifTrueThereMultipleTimes() {
            val lst: MutableList<String> = mutableListOf("item", "item")
            lst.removeIf(condition = true, "item").assertTrue()
            lst.assert("item")
            lst.removeIf(condition = true, "item").assertTrue()
            lst.assertEmpty()
        }

    }

    class MutableCollectionERemoveIfNotNull {

        @Test
        fun notThereAndNullEmpty() {
            val lst: MutableList<String> = mutableListOf<String>()
            lst.removeIfNotNull(null).assertFalse()
            lst.assertEmpty()
        }

        @Test
        fun notThereAndNullSingle() {
            val lst: MutableList<String> = mutableListOf("lst")
            lst.removeIfNotNull(null).assertFalse()
            lst.assert("lst")
        }

        @Test
        fun notThere() {
            val lst: MutableList<String> = mutableListOf("lst")
            lst.removeIfNotNull("item").assertFalse()
            lst.assert("lst")
        }

        @Test
        fun thereSingle() {
            val lst: MutableList<String> = mutableListOf("lst")
            lst.removeIfNotNull("lst").assertTrue()
            lst.assertEmpty()
        }

        @Test
        fun thereMultiple() {
            val lst: MutableList<String> = mutableListOf("lst", "lst")
            lst.removeIfNotNull("lst").assertTrue()
            lst.assert("lst")
            lst.removeIfNotNull("lst").assertTrue()
            lst.assertEmpty()
        }

    }
}