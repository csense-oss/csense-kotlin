@file:Suppress("unused")

package csense.kotlin.extensions.collections.collection.mutable

import csense.kotlin.tests.assertions.*
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
            lst.assertSingle("item")
        }


        @Test
        fun ifTrueNotThere() {
            val lst: MutableList<String> = mutableListOf("item")
            lst.removeIf(condition = true, "testItem").assertFalse()
            lst.assertSingle("item")
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
            lst.assertSingle("item")
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
            lst.assertSingle("lst")
        }

        @Test
        fun notThere() {
            val lst: MutableList<String> = mutableListOf("lst")
            lst.removeIfNotNull("item").assertFalse()
            lst.assertSingle("lst")
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
            lst.assertSingle("lst")
            lst.removeIfNotNull("lst").assertTrue()
            lst.assertEmpty()
        }

    }
}