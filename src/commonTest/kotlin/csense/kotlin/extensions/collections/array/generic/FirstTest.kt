package csense.kotlin.extensions.collections.array.generic

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class FirstTest {

    class ArrayoutTFirstOr {

        @Test
        fun empty() {
            arrayOf<String>().firstOr("test").assert("test")
        }


        @Test
        fun single() {
            arrayOf("test").firstOr("failed").assert("test")
        }


        @Test
        fun multiple() {
            arrayOf("test", "1234").firstOr("failed").assert("test")
        }
    }

    class ArrayoutTFirstOrBy {

        @Test
        fun empty() {
            arrayOf<String>().firstOrBy { "test" }.assert("test")
        }


        @Test
        fun single() {
            arrayOf("test").firstOrBy { shouldNotBeCalled() }.assert("test")
        }


        @Test
        fun multiple() {
            arrayOf("test", "1234").firstOrBy { shouldNotBeCalled() }.assert("test")
        }
    }
}