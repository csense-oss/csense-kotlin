@file:Suppress("unused")

package org.csenseoss.kotlin.extensions.collections.iterable

import org.csenseoss.kotlin.tests.assertions.*
import org.csenseoss.kotlin.tests.assertions.primitives.charSequence.*
import kotlin.test.*

class FirstOrTest {

    class IterableTFirstOr {
        @Test
        fun empty() {
            listOf<String>().firstOr("test").assert("test")
        }


        @Test
        fun single() {
            listOf("first").firstOr("fail").assert("first")
        }


        @Test
        fun multiple() {
            listOf("first", "second").firstOr("fail").assert("first")
        }
    }

    class IterableTFirstOrBy {
        @Test
        fun empty() {
            listOf<String>().firstOr { "test" }.assert("test")
        }


        @Test
        fun single() {
            listOf("first").firstOr { "test" }.assert("first")
        }


        @Test
        fun multiple() {
            listOf("first", "second").firstOr { "fail" }.assert("first")
        }

    }
}