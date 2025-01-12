package org.csenseoss.kotlin.general.coroutine

import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.csenseoss.kotlin.tests.assertions.*
import org.csenseoss.kotlin.tests.assertions.general.*
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