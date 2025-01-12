package org.csenseoss.kotlin.extensions.collections.collection

import org.csenseoss.kotlin.tests.assertions.collections.iterable.*
import org.csenseoss.kotlin.tests.assertions.general.*
import kotlin.test.*

class OnNullOrEmptyTest {
    class CollectionItemOnNullOrEmptyItem {
        @Test
        fun nullable() {
            val lst: List<String>? = null
            lst.onNullOrEmpty(item = "test").assert("test")
        }

        @Test
        fun empty() {
            listOf<String>().nullable().onNullOrEmpty(item = "test").assert("test")
        }

        @Test
        fun single() {
            listOf("starting").nullable().onNullOrEmpty(item = "failed").assert("starting")
        }

        @Test
        fun multiple() {
            listOf("a", "b", "c").nullable().onNullOrEmpty(item = "failed").assert(
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
            lst.onNullOrEmpty(items = listOf("items")).assert("items")
        }

        @Test
        fun empty() {
            listOf<String>().nullable().onNullOrEmpty(items = listOf("test")).assert("test")
        }

        @Test
        fun single() {
            listOf("starting").nullable().onNullOrEmpty(items = listOf("failed")).assert("starting")
        }

        @Test
        fun multiple() {
            listOf("a", "b", "c").nullable().onNullOrEmpty(items = listOf("failed")).assert(
                expected = listOf(
                    "a",
                    "b",
                    "c"
                )
            )
        }
    }


    class CollectionItemOnNullOrEmptyLazy {
        @Test
        fun empty(): Unit = assertCalled { shouldBeCalled ->
            listOf<String>().nullable().onNullOrEmptyLazy {
                shouldBeCalled()
                listOf("test")
            }.assert("test")
        }

        @Test
        fun single() {
            listOf("test").nullable().onNullOrEmptyLazy { shouldNotBeCalled() }.assert("test")
        }

        @Test
        fun multiple() {
            listOf("test", "1234").nullable().onNullOrEmptyLazy { shouldNotBeCalled() }.assert(
                "test", "1234"
            )
        }
    }

}