@file:Suppress("unused")

package csense.kotlin.extensions.collections.array.typed.job

import csense.kotlin.extensions.collections.array.typed.Job.*
import csense.kotlin.tests.assertions.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import kotlin.test.*

class JoinAllTest {
    @Test
    fun empty(): TestResult = runTest {
        arrayOf<Job>().joinAll()
    }

    @Test
    fun single(): TestResult = runTest {
        assertCalled { shouldBeCalled: () -> Unit ->
            val jobs: Array<Job> = arrayOf(launch { shouldBeCalled() })
            jobs.joinAll()
        }
    }

    @Test
    fun multiple(): TestResult = runTest {
        assertCalled(times = 2) { shouldBeCalled: () -> Unit ->
            val jobs: Array<Job> = arrayOf(
                launch { shouldBeCalled() },
                launch { shouldBeCalled() }
            )
            jobs.joinAll()
        }
    }
}