package org.csenseoss.kotlin.extensions.collections.array.generic

import org.csenseoss.kotlin.tests.assertions.collections.iterable.*
import org.csenseoss.kotlin.tests.assertions.general.*
import kotlin.test.*

class DropOrTest {
    class DropOrEmpty {
        @Test
        fun empty() {
            val empty: Array<String> = arrayOf()
            empty.dropOrEmpty(dropCount = 0).assertEmpty()
        }

        @Test
        fun single() {
            val single: Array<String> = arrayOf("test")
            single.dropOrEmpty(dropCount = 0).assert("test")
            single.dropOrEmpty(dropCount = 1).assertEmpty()
            single.dropOrEmpty(dropCount = 2).assertEmpty()
        }

        @Test
        fun multiple() {
            val multi: Array<String> = arrayOf("1", "2", "3")
            multi.dropOrEmpty(dropCount = 1).assert(listOf("2", "3"))
            multi.dropOrEmpty(dropCount = 2).assert(listOf("3"))
            multi.dropOrEmpty(dropCount = 3).assertEmpty()
            multi.dropOrEmpty(dropCount = 4).assertEmpty()
        }
    }

    class DropOrNull {
        @Test
        fun empty() {
            val empty: Array<String> = arrayOf()
            empty.dropOrNull(dropCount = 0).assertNull()
        }

        @Test
        fun single() {
            val single: Array<String> = arrayOf("test")
            single.dropOrNull(dropCount = 0).assert("test")
            single.dropOrNull(dropCount = 1).assertNull()
            single.dropOrNull(dropCount = 2).assertNull()
        }

        @Test
        fun multiple() {
            val multi: Array<String> = arrayOf("1", "2", "3")
            multi.dropOrNull(dropCount = 0).assert(listOf("1", "2", "3"))
            multi.dropOrNull(dropCount = 1).assert(listOf("2", "3"))
            multi.dropOrNull(dropCount = 2).assert(listOf("3"))
            multi.dropOrNull(dropCount = 3).assertNull()
            multi.dropOrNull(dropCount = 4).assertNull()
        }
    }

    class DropOrValue {
        @Test
        fun empty() {
            val empty: Array<String> = arrayOf()
            empty.dropOr(dropCount = 0, defaultValue = listOf("test"))
                .assert("test")
        }

        @Test
        fun single() {
            val single: Array<String> = arrayOf("test")
            single.dropOr(dropCount = 0, defaultValue = listOf("orValue")).assert("test")
            single.dropOr(dropCount = 1, defaultValue = listOf("orValue")).assert("orValue")
            single.dropOr(dropCount = 2, defaultValue = listOf("orValue")).assert("orValue")
        }

        @Test
        fun multiple() {
            val multi: Array<String> = arrayOf("1", "2", "3")
            multi.dropOr(dropCount = 0, defaultValue = listOf("orValue")).assert(listOf("1", "2", "3"))
            multi.dropOr(dropCount = 1, defaultValue = listOf("orValue")).assert(listOf("2", "3"))
            multi.dropOr(dropCount = 2, defaultValue = listOf("orValue")).assert(listOf("3"))
            multi.dropOr(dropCount = 3, defaultValue = listOf("orValue")).assert("orValue")
            multi.dropOr(dropCount = 4, defaultValue = listOf("orValue")).assert("orValue")
        }
    }

    class DropOrAction {
        @Test
        fun empty(): Unit = assertCalled { shouldBeCalled: () -> Unit ->
            val empty: Array<String> = arrayOf()
            empty.dropOr(dropCount = 0, defaultValue = { shouldBeCalled(); listOf("test") })
                .assert("test")
        }

        @Test
        fun single() {
            val single: Array<String> = arrayOf("test")
            single.dropOrNull(dropCount = 0).assert("test")
            assertCalled { shouldBeCalled: () -> Unit ->
                single.dropOr(
                    dropCount = 1,
                    defaultValue = {
                        shouldBeCalled()
                        listOf("success")
                    }
                ).assert("success")
            }
            assertCalled { shouldBeCalled: () -> Unit ->
                single.dropOr(
                    dropCount = 2,
                    defaultValue = {
                        shouldBeCalled()
                        listOf("success")
                    }
                ).assert("success")
            }
        }

        @Test
        fun multiple() {
            val multi: Array<String> = arrayOf("1", "2", "3")
            multi.dropOr(dropCount = 0, defaultValue = { listOf("orValue") }).assert(listOf("1", "2", "3"))
            multi.dropOr(dropCount = 1, defaultValue = { listOf("orValue") }).assert(listOf("2", "3"))
            multi.dropOr(dropCount = 2, defaultValue = { listOf("orValue") }).assert(listOf("3"))
            multi.dropOr(dropCount = 3, defaultValue = { listOf("orValue") }).assert("orValue")
            multi.dropOr(dropCount = 4, defaultValue = { listOf("orValue") }).assert("orValue")
        }
    }

}