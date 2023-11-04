@file:Suppress("unused")

package csense.kotlin.extensions.collections.iterable

import csense.kotlin.tests.assertions.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
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
                }.awaitAll().assertSingle("output")
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
                    assertSize(2)
                    first().assert("output")
                    last().assert("output")
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
            }.assertSingle("test")
        }

        @Test
        fun multiple(): TestResult = runTest {
            val lst: List<String> = listOf("input1", "input2").mapAsyncAwait(this) { it: String ->
                it
            }
            lst.assertSize(2)
            lst.assertContainsAll("input1", "input2")
        }
    }
}