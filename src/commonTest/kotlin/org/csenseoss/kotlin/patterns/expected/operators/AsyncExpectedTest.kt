package org.csenseoss.kotlin.patterns.expected.operators

import csense.kotlin.tests.assertions.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.csenseoss.kotlin.patterns.expected.*
import kotlin.test.*

class AsyncExpectedTest {
    @Test
    fun coroutineScopeAsyncExpectedContext(): TestResult = runTest {
        val result: Expected<String, Nothing> = asyncExpected(Dispatchers.Default) {
            assertDispatcherDefault()
            "test".asSuccess()
        }.await()
        result.value.assert("test")
    }

    @Test
    fun coroutineScopeAsyncExpected(): TestResult {
        val testDispatcher: TestDispatcher = StandardTestDispatcher(name = "implicit")
        return runTest(testDispatcher) {
            val result: Expected<String, Nothing> = asyncExpected {
                assertDispatcher(testDispatcher)
                "test2".asSuccess()
            }.await()
            result.value.assert("test2")
        }
    }

    @Test
    fun coroutineScopeAsyncDefaultExpected(): TestResult = runTest {
        val result: Expected<String, Nothing> = asyncDefaultExpected {
            assertDispatcherDefault()
            "default".asSuccess()
        }.await()
        result.value.assert("default")
    }


    @Test
    fun coroutineScopeAsyncMainExpected() {
        runTestForMainDispatcher {
            val result: Expected<String, Nothing> = asyncMainExpected {
                assertDispatcherMain()
                "main".asSuccess()
            }.await()
            result.value.assert("main")
        }
    }
}