package org.csenseoss.kotlin.general.coroutine

import csense.kotlin.tests.assertions.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.csenseoss.kotlin.general.coroutine.*
import kotlin.coroutines.*
import kotlin.test.*

class WithCurrentCoroutineScopeTest {

    @OptIn(DelicateCoroutinesApi::class)
    @Test
    fun usesCurrentContext(): TestResult = runTest {
        val startingContext: CoroutineContext = coroutineContext
        assertCalled { shouldBeCalled: () -> Unit ->
            withCurrentCoroutineScope {
                coroutineContext.assertByEquals(startingContext)
                shouldBeCalled()
            }
        }

    }
}