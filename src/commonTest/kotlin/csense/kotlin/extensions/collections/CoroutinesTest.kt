@file:OptIn(ExperimentalCoroutinesApi::class)

package csense.kotlin.extensions.collections

import csense.kotlin.extensions.collections.iterable.*
import csense.kotlin.tests.assertions.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import kotlin.test.*

class CoroutinesTest {


    class IterableTMapAsyncAwait {
        @Test
        fun empty() = runTest {
            listOf<String>().mapAsyncAwait(this) {
                shouldNotBeCalled()
            }.assertEmpty()
        }

        @Test
        fun single() = runTest {
            listOf("input").mapAsyncAwait(this) {
                it.assert("input")
                "test"
            }.assertSingle("test")
        }

        @Test
        fun multiple() = runTest {
            val lst = listOf("input1", "input2").mapAsyncAwait(this) {
                it
            }
            lst.assertSize(2)
            lst.assertContainsAll("input1", "input2")
        }
    }

    class IterableTMapAsync {
        @Test
        fun empty(): TestResult = runTest {
            listOf<String>().mapAsync(this) {
                shouldNotBeCalled()
            }.awaitAll().assertEmpty()
        }

        @Test
        fun single(): TestResult = runTest {
            assertCalled { shouldBeCalled: () -> Unit ->
                listOf("input").mapAsync(this) {
                    shouldBeCalled()
                    it.assert("input")
                    "output"
                }.awaitAll().assertSingle("output")
            }
        }

        @Test
        fun multiple(): TestResult = runTest {
            assertCalled(times = 2) { shouldBeCalled: () -> Unit ->
                listOf("input1", "input2").mapAsync(this) {
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


}