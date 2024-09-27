package org.csenseoss.kotlin.extensions.coroutines.coroutineScope

import csense.kotlin.tests.assertions.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
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
    fun coroutineScopeWithContextMain(): TestResult = runTestForMainDispatcher {
        withContextMain {
            assertDispatcher(Dispatchers.Main)
            "result"
        }.assert("result")
    }
}