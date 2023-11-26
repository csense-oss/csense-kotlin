package csense.kotlin.extensions.collections.collection

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class OnEmptyTest {
    class OnEmptyItem {

        @Test
        fun empty() {
            val input: List<String> = listOf()
            input.onEmpty(item = "test").assertSingle("test")
        }


        @Test
        fun single() {
            val input: List<String> = listOf("test")
            input.onEmpty(item = "1234").assertSingle("test")
        }


        @Test
        fun multiple() {
            val input: List<String> = listOf("test", "abcd")
            input.onEmpty(item = "1234").assertContainsInOrder("test", "abcd")
        }

    }

    class OnEmptyItems {

        @Test
        fun empty() {
            val input: List<String> = listOf()
            input.onEmpty(items = listOf("test")).assertSingle("test")
        }


        @Test
        fun single() {
            val input: List<String> = listOf("test")
            input.onEmpty(items = listOf("1234")).assertSingle("test")
        }


        @Test
        fun multiple() {
            val input: List<String> = listOf("test", "abcd")
            input.onEmpty(items = listOf("1234")).assertContainsInOrder("test", "abcd")
        }

    }

    class OnEmptyLazyItem {

        @Test
        fun empty() {
            val input: List<String> = listOf()
            input.onEmptyLazy(item = {
                "test"
            }).assertSingle("test")
        }


        @Test
        fun single() {
            val input: List<String> = listOf("test")
            input.onEmptyLazy(item = {
                shouldNotBeCalled()
            }).assertSingle("test")
        }


        @Test
        fun multiple() {
            val input: List<String> = listOf("test", "1234")
            input.onEmptyLazy(item = {
                shouldNotBeCalled()
            }).assertContainsInOrder("test", "1234")
        }

    }

    class OnEmptyLazyItems {

        @Test
        fun empty() {
            val input: List<String> = listOf()
            input.onEmptyLazy(items = {
                listOf("test","1234")
            }).assertContainsInOrder("test", "1234")
        }


        @Test
        fun single() {
            val input: List<String> = listOf("test")
            input.onEmptyLazy(items = {
                listOf("not","not")
            }).assertContainsInOrder("test")
        }


        @Test
        fun multiple() {
            val input: List<String> = listOf("test", "1234")
            input.onEmptyLazy(items = {
                listOf("not","not")
            }).assertContainsInOrder("test","1234")
        }

    }
}
