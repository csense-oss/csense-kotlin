@file:Suppress("unused")

package org.csenseoss.kotlin.extensions.collections.iterable

import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.csenseoss.kotlin.tests.assertions.collections.iterable.*
import org.csenseoss.kotlin.tests.assertions.general.*
import org.csenseoss.kotlin.tests.assertions.primitives.charSequence.*
import kotlin.test.*

class MapAsyncTest {
    class IterableTMapAsync {
        @Test
        fun empty(): TestResult = runTest {
            listOf<String>().mapAsync(this) { _: String ->
                shouldNotBeCalled()
            }.awaitAll().assertEmpty()
        }

        @Test
        fun single(): TestResult = runTest {
            assertCalled { shouldBeCalled: () -> Unit ->
                listOf("input").mapAsync(this) { it: String ->
                    shouldBeCalled()
                    it.assert("input")
                    "output"
                }.awaitAll().assert("output")
            }
        }

        @Test
        fun multiple(): TestResult = runTest {
            assertCalled(times = 2) { shouldBeCalled: () -> Unit ->
                listOf("input1", "input2").mapAsync(this) { it: String ->
                    shouldBeCalled()
                    it.assertStartsWith("input")
                    "output"
                }.awaitAll().apply {
                    assert("output","output")
                }
            }
        }
    }

    class IterableTMapAsyncAwait {
        @Test
        fun empty(): TestResult = runTest {
            listOf<String>().mapAsyncAwait(this) { _: String ->
                shouldNotBeCalled()
            }.assertEmpty()
        }

        @Test
        fun single(): TestResult = runTest {
            listOf("input").mapAsyncAwait(this) { it: String ->
                it.assert("input")
                "test"
            }.assert("test")
        }

        @Test
        fun multiple(): TestResult = runTest {
            val lst: List<String> = listOf("input1", "input2").mapAsyncAwait(this) { it: String ->
                it
            }
            lst.assert("input1", "input2")
        }
    }
}