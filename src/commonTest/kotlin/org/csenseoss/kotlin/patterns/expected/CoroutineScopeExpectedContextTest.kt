package org.csenseoss.kotlin.patterns.expected

import csense.kotlin.tests.assertions.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.csenseoss.kotlin.extensions.coroutines.coroutineScope.*
import kotlin.coroutines.*
import kotlin.test.*

class CoroutineScopeExpectedContextTest {
    @Test
    fun coroutineScopeToExpectedContext(): TestResult = runTest {
        val context: CoroutineScopeExpectedContext = toExpectedContext()
        val result: Expected.Success<String> = with(context) {
            asyncDefault { "test".asSuccess() }.await()
        }
        result.value.assert("test")

    }

    @Test
    fun coroutineContext(): TestResult = runTest {
        val counter = DummyCoroutineScopeCounter()
        val context: CoroutineScopeExpectedContext = counter.toExpectedContext()
        context.coroutineContext.assertAs(EmptyCoroutineContext)
        counter.counter.assert(1)
    }
}

private class DummyCoroutineScopeCounter : CoroutineScope {
    var counter: Int = 0
    override val coroutineContext: CoroutineContext
        get() {
            counter += 1
            return EmptyCoroutineContext
        }

}