package org.csenseoss.kotlin.extensions.collections.collection

import org.csenseoss.kotlin.tests.assertions.collections.iterable.*
import org.csenseoss.kotlin.tests.assertions.general.*
import kotlin.test.*

class OnEmptyTest {
    class OnEmptyItem {

        @Test
        fun empty() {
            val input: List<String> = listOf()
            input.onEmpty(item = "test").assert("test")
        }


        @Test
        fun single() {
            val input: List<String> = listOf("test")
            input.onEmpty(item = "1234").assert("test")
        }


        @Test
        fun multiple() {
            val input: List<String> = listOf("test", "abcd")
            input.onEmpty(item = "1234").assert("test", "abcd")
        }

    }

    class OnEmptyItems {

        @Test
        fun empty() {
            val input: List<String> = listOf()
            input.onEmpty(items = listOf("test")).assert("test")
        }


        @Test
        fun single() {
            val input: List<String> = listOf("test")
            input.onEmpty(items = listOf("1234")).assert("test")
        }


        @Test
        fun multiple() {
            val input: List<String> = listOf("test", "abcd")
            input.onEmpty(items = listOf("1234")).assert("test", "abcd")
        }

    }

    class OnEmptyLazyItem {

        @Test
        fun empty() {
            val input: List<String> = listOf()
            input.onEmptyLazy(item = {
                "test"
            }).assert("test")
        }


        @Test
        fun single() {
            val input: List<String> = listOf("test")
            input.onEmptyLazy(item = {
                shouldNotBeCalled()
            }).assert("test")
        }


        @Test
        fun multiple() {
            val input: List<String> = listOf("test", "1234")
            input.onEmptyLazy(item = {
                shouldNotBeCalled()
            }).assert("test", "1234")
        }

    }

    class OnEmptyLazyItems {

        @Test
        fun empty() {
            val input: List<String> = listOf()
            input.onEmptyLazy(items = {
                listOf("test","1234")
            }).assert("test", "1234")
        }


        @Test
        fun single() {
            val input: List<String> = listOf("test")
            input.onEmptyLazy(items = {
                listOf("not","not")
            }).assert("test")
        }


        @Test
        fun multiple() {
            val input: List<String> = listOf("test", "1234")
            input.onEmptyLazy(items = {
                listOf("not","not")
            }).assert("test","1234")
        }

    }
}