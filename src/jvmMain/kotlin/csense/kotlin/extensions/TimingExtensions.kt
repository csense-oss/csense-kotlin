@file:Suppress("unused")

package csense.kotlin.extensions

import csense.kotlin.EmptyFunctionResult
import csense.kotlin.annotations.numbers.*
import csense.kotlin.logger.L
import csense.kotlin.logger.LoggingFunctionType
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * Measure the execution time the given action, and returns the time and the result of the method invocation.
 * @param block [EmptyFunctionResult]<R>
 * @return [Pair]<[Long], R> the first is the time in ms the second is the result of the function.
 */
@OptIn(ExperimentalContracts::class)
public inline fun <R> measureTimeMillisResult(
    block: EmptyFunctionResult<R>
): Pair<@LongLimit(from = 0) Long, R> {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    val start = System.currentTimeMillis()
    val result = block()
    val time = System.currentTimeMillis() - start
    return Pair(time, result)
}

/**
 * Logs the time (in ms) for the given operation, via the given logging function.
 * @param loggingTitle [String]
 * @param loggingMethod [LoggingFunctionType]<Unit>
 * @param action [EmptyFunctionResult]<T>
 * @return T the result type of the action invocation.
 */
@OptIn(ExperimentalContracts::class)
public inline fun <T> logMeasureTimeInMillis(
    loggingTitle: String = "Timing",
    loggingMethod: LoggingFunctionType<Unit> = L::debug,
    action: EmptyFunctionResult<T>
): T {
    contract {
        callsInPlace(loggingMethod, InvocationKind.EXACTLY_ONCE)
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    val (time, result) = measureTimeMillisResult { action() }
    loggingMethod(loggingTitle, "${time}ms", null)
    return result
}