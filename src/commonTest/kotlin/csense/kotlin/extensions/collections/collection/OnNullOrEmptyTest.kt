package csense.kotlin.extensions.collections.collection

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class OnNullOrEmptyTest {
    class CollectionItemOnNullOrEmptyItem {
        @Test
        fun nullable() {
            val lst: List<String>? = null
            lst.onNullOrEmpty(item = "test").assertSingle("test")
        }

        @Test
        fun empty() {
            listOf<String>().nullable().onNullOrEmpty(item = "test").assertSingle("test")
        }

        @Test
        fun single() {
            listOf("starting").nullable().onNullOrEmpty(item = "failed").assertSingle("starting")
        }

        @Test
        fun multiple() {
            listOf("a", "b", "c").nullable().onNullOrEmpty(item = "failed").assertContentAndOrder(
                expected = listOf(
                    "a",
                    "b",
                    "c"
                )
            )
        }
    }

    class CollectionItemOnNullOrEmptyItems {
        @Test
        fun nullable() {
            val lst: List<String>? = null
            lst.onNullOrEmpty(items = listOf("items")).assertSingle("items")
        }

        @Test
        fun empty() {
            listOf<String>().nullable().onNullOrEmpty(items = listOf("test")).assertSingle("test")
        }

        @Test
        fun single() {
            listOf("starting").nullable().onNullOrEmpty(items = listOf("failed")).assertSingle("starting")
        }

        @Test
        fun multiple() {
            listOf("a", "b", "c").nullable().onNullOrEmpty(items = listOf("failed")).assertContentAndOrder(
                expected = listOf(
                    "a",
                    "b",
                    "c"
                )
            )
        }
    }

    @Test
    fun todo() {
        TODO("missing lazy etc")
    }
}