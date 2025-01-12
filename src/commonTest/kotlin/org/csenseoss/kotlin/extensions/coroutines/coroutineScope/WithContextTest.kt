package org.csenseoss.kotlin.extensions.coroutines.coroutineScope

import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.csenseoss.kotlin.tests.assertions.*
import org.csenseoss.kotlin.tests.assertions.comparable.*
import org.csenseoss.kotlin.tests.assertions.coroutines.*
import kotlin.test.*

class WithContextTest {
    @Test
    fun coroutineScopeWithContextDefault(): TestResult = runTest {
        withContextDefault {
            assertDispatcher(Dispatchers.Default)
            "result"
        }.assert("result")
    }

    @Test
    fun coroutineScopeWithContextMain() {
        runTestForMainDispatcher {
            withContextMain {
                assertDispatcher(Dispatchers.Main)
                "result"
            }.assert("result")
        }
    }
}