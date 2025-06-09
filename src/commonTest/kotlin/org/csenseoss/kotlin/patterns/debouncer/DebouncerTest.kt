package org.csenseoss.kotlin.patterns.debouncer

import kotlinx.coroutines.test.*
import org.csenseoss.kotlin.tests.assertions.primitives.boolean.*
import kotlin.test.*
import kotlin.time.*
import kotlin.time.Duration.Companion.milliseconds

class DebouncerTest {

    class Stop {
        @Test
        fun stopsWhenHaveNotExecuted(): TestResult = runTest {
            var didCall = false
            val debouncer: Debouncer<Unit> = Debouncer(
                scope = this,
                delay = 500.milliseconds,
                block = { didCall = true },
                dispatcher = StandardTestDispatcher(testScheduler)
            )
            advanceTimeByAndRun(50.milliseconds)
            didCall.assertFalse()

            debouncer.stop()
            advanceTimeByAndRun(500.milliseconds)
            didCall.assertFalse()
        }

        @Test
        fun stopAfterExecuteDoesNothing(): TestResult = runTest {
            var didCall = false
            val debouncer: Debouncer<Unit> = Debouncer(
                scope = this,
                delay = 500.milliseconds,
                block = { didCall = true },
                dispatcher = StandardTestDispatcher(testScheduler)
            )
            debouncer.start()
            advanceTimeByAndRun(500.milliseconds)
            didCall.assertTrue()
        }
    }

    class Start {
        @Test
        fun doesNotStartBeforeStartCall(): TestResult = runTest {
            var didCall = false
            val debouncer: Debouncer<Unit> = Debouncer(
                scope = this,
                delay = 500.milliseconds,
                block = { didCall = true },
                dispatcher = StandardTestDispatcher(testScheduler)
            )
            advanceTimeByAndRun(500.milliseconds)
            didCall.assertFalse("should not invoke before start is called")
        }

        @Test
        fun doesNotStartBeforeDelay(): TestResult = runTest {
            var didCall = false
            val debouncer: Debouncer<Unit> = Debouncer(
                scope = this,
                delay = 500.milliseconds,
                block = { didCall = true },
                dispatcher = StandardTestDispatcher(testScheduler)
            )
            debouncer.start()
            advanceTimeByAndRun(50.milliseconds)
            didCall.assertFalse("should not invoke before delay has passed")
        }

        @Test
        fun startsAfterDelay(): TestResult = runTest {
            var didCall = false
            val debouncer: Debouncer<Unit> = Debouncer(
                scope = this,
                delay = 500.milliseconds,
                block = { didCall = true },
                dispatcher = StandardTestDispatcher(testScheduler)
            )
            debouncer.start()
            advanceTimeByAndRun(500.milliseconds)
            didCall.assertTrue("Should invoke after delay")
        }

        @Test
        fun multipleStartExpandsDelay(): TestResult = runTest {
            var didCall = false
            val debouncer: Debouncer<Unit> = Debouncer(
                scope = this,
                delay = 500.milliseconds,
                block = { didCall = true },
                dispatcher = StandardTestDispatcher(testScheduler)
            )
            debouncer.start()
            advanceTimeByAndRun(400.milliseconds)
            didCall.assertFalse()

            debouncer.start()
            advanceTimeByAndRun(400.milliseconds)
            didCall.assertFalse("Should restart delay after second call to start")

            advanceTimeByAndRun(100.milliseconds)
            didCall.assertTrue("Should run after delay (second time)")
        }
    }


    @Test
    fun join(): TestResult = runTest {
        var didCall = false
        val debouncer: Debouncer<Unit> = Debouncer(
            scope = this,
            delay = 500.milliseconds,
            block = { didCall = true },
            dispatcher = StandardTestDispatcher(testScheduler)
        )
        debouncer.start()
        debouncer.join()
        didCall.assertTrue("Should have joined")
    }
}

//TODO csense tests
fun TestScope.advanceTimeByAndRun(delayTimeMillis: Long) {
    advanceTimeBy(delayTimeMillis)
    runCurrent()
}

fun TestScope.advanceTimeByAndRun(delayTime: Duration) {
    advanceTimeBy(delayTime)
    runCurrent()
}