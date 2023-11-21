@file:Suppress("unused")

package csense.kotlin.extensions.duration

import csense.kotlin.*
import csense.kotlin.logger.*
import csense.kotlin.logger.models.*
import csense.kotlin.logger.operators.*
import kotlin.contracts.*
import kotlin.time.*

/**
 * Measure the execution time the given action, and returns the time and the result of the method invocation.
 * @param block [EmptyFunctionResult]<R>
 * @return [Pair]<[Long], R> the first is the [Duration] the second is the [Result] of [block].
 */
public inline fun <Result> measureTimeResult(
    block: EmptyFunctionResult<Result>
): Pair<Duration, Result> {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    val result: Result
    val duration = measureTime { result = block() }
    return Pair(duration, result)
}

/**
 * Logs the time (in ms) for the given operation, via the given logging function.
 * @param loggingTitle [String]
 * @param loggingMethod [CLLogFunction]
 * @param sensitivity [LogSensitivity]
 * @param action [EmptyFunctionResult]<T>
 * @return T the result type of the action invocation.
 */

public inline fun <T> logMeasureTimeInMillis(
    loggingTitle: String = "Timing",
    loggingMethod: CLLogFunction = CL.logDebug,
    sensitivity: LogSensitivity = LogSensitivity.Sensitive,
    action: EmptyFunctionResult<T>
): T {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    val (time: Duration, result: T) = measureTimeResult(action)
    loggingMethod(
        tag = loggingTitle,
        message = "{}ms",
        placeholders = arrayOf(time.toString()),
        exception = null,
        sensitivity = sensitivity,
    )
    return result
}
