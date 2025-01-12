package org.csenseoss.kotlin.extensions.collections.iterable

import org.csenseoss.kotlin.tests.assertions.*
import org.csenseoss.kotlin.tests.assertions.collections.iterable.*
import org.csenseoss.kotlin.tests.assertions.general.*
import kotlin.test.*

class DropOrTest {
    class OrValue {
        @Test
        fun empty() {
            val iterable: Iterable<String> = listOf()
            iterable.dropOr(
                dropCount = 0,
                orValue = listOf("orValue")
            ).assert("orValue")

            iterable.dropOr(
                dropCount = 1,
                orValue = listOf("orValue")
            ).assert("orValue")
        }

        @Test
        fun single() {
            val iterable: Iterable<String> = listOf("single")
            iterable.dropOr(
                dropCount = 0,
                orValue = listOf("orValue")
            ).assert("single")

            iterable.dropOr(
                dropCount = 1,
                orValue = listOf("orValue")
            ).assert("orValue")
        }

        @Test
        fun multiple() {
            val iterable: Iterable<String> = listOf("first", "second")
            iterable.dropOr(
                dropCount = 0,
                orValue = listOf("orValue")
            ).assert(listOf("first", "second"))

            iterable.dropOr(
                dropCount = 1,
                orValue = listOf("orValue")
            ).assert("second")
            iterable.dropOr(
                dropCount = 2,
                orValue = listOf("orValue")
            ).assert("orValue")
        }
    }

    class Action {
        @Test
        fun empty() {
            val iterable: Iterable<String> = listOf()
            iterable.dropOr(
                dropCount = 0,
                orAction = { listOf("orValue") }
            ).assert("orValue")

            iterable.dropOr(
                dropCount = 1,
                orAction = { listOf("orValue") }
            ).assert("orValue")
        }

        @Test
        fun single() {
            val iterable: Iterable<String> = listOf("single")
            iterable.dropOr(
                dropCount = 0,
                orAction = { shouldNotBeCalled() }
            ).assert("single")

            iterable.dropOr(
                dropCount = 1,
                orAction = { listOf("orValue") }
            ).assert("orValue")
        }

        @Test
        fun multiple() {
            val iterable: Iterable<String> = listOf("first", "second")
            iterable.dropOr(
                dropCount = 0,
                orAction = { shouldNotBeCalled() }
            ).assert(listOf("first", "second"))

            iterable.dropOr(
                dropCount = 1,
                orAction = { shouldNotBeCalled() }
            ).assert("second")
            iterable.dropOr(
                dropCount = 2,
                orAction = { listOf("orValue") }
            ).assert("orValue")
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