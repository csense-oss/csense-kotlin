package org.csenseoss.kotlin.extensions.collections.iterable

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class DropOrTest {
    class OrValue {
        @Test
        fun empty() {
            val iterable: Iterable<String> = listOf()
            iterable.dropOr(
                dropCount = 0,
                orValue = listOf("orValue")
            ).assertSingle("orValue")

            iterable.dropOr(
                dropCount = 1,
                orValue = listOf("orValue")
            ).assertSingle("orValue")
        }

        @Test
        fun single() {
            val iterable: Iterable<String> = listOf("single")
            iterable.dropOr(
                dropCount = 0,
                orValue = listOf("orValue")
            ).assertSingle(expected = "single")

            iterable.dropOr(
                dropCount = 1,
                orValue = listOf("orValue")
            ).assertSingle("orValue")
        }

        @Test
        fun multiple() {
            val iterable: Iterable<String> = listOf("first", "second")
            iterable.dropOr(
                dropCount = 0,
                orValue = listOf("orValue")
            ).assertContentAndOrder(listOf("first", "second"))

            iterable.dropOr(
                dropCount = 1,
                orValue = listOf("orValue")
            ).assertSingle("second")
            iterable.dropOr(
                dropCount = 2,
                orValue = listOf("orValue")
            ).assertSingle("orValue")
        }
    }

    class Action {
        @Test
        fun empty() {
            val iterable: Iterable<String> = listOf()
            iterable.dropOr(
                dropCount = 0,
                orAction = { listOf("orValue") }
            ).assertSingle("orValue")

            iterable.dropOr(
                dropCount = 1,
                orAction = { listOf("orValue") }
            ).assertSingle("orValue")
        }

        @Test
        fun single() {
            val iterable: Iterable<String> = listOf("single")
            iterable.dropOr(
                dropCount = 0,
                orAction = { shouldNotBeCalled() }
            ).assertSingle(expected = "single")

            iterable.dropOr(
                dropCount = 1,
                orAction = { listOf("orValue") }
            ).assertSingle("orValue")
        }

        @Test
        fun multiple() {
            val iterable: Iterable<String> = listOf("first", "second")
            iterable.dropOr(
                dropCount = 0,
                orAction = { shouldNotBeCalled() }
            ).assertContentAndOrder(listOf("first", "second"))

            iterable.dropOr(
                dropCount = 1,
                orAction = { shouldNotBeCalled() }
            ).assertSingle("second")
            iterable.dropOr(
                dropCount = 2,
                orAction = { listOf("orValue") }
            ).assertSingle("orValue")
        }
    }

    class IterableTDropOrEmpty {
        @Test
        fun empty() {
            //TODO test empty condition here.
        }

        @Test
        fun single() {
            //TODO test single element condition here.
        }

        @Test
        fun multiple() {
            //TODO test multiple element condition here.
        }
    }

    class IterableTDropOrNull {
        @Test
        fun empty() {
            //TODO test empty condition here.
        }

        @Test
        fun single() {
            //TODO test single element condition here.
        }

        @Test
        fun multiple() {
            //TODO test multiple element condition here.
        }
    }
}