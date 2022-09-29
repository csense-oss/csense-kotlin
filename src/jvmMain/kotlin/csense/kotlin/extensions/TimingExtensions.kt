@file:Suppress("unused")

package csense.kotlin.extensions

import csense.kotlin.*
import csense.kotlin.annotations.numbers.*
import csense.kotlin.logger.*
import csense.kotlin.logger.models.*
import kotlin.contracts.*
import kotlin.system.*
import kotlin.time.*

/**
 * Measure the execution time the given action, and returns the time and the result of the method invocation.
 * @param block [EmptyFunctionResult]<R>
 * @return [Pair]<[Long], R> the first is the time in ms the second is the result of the function.
 */
@Deprecated(
    "Use measureTimeResult and extrapolate the time unit you need / want instead",
    replaceWith = ReplaceWith("measureTimeResult(block)")
)
public inline fun <R> measureTimeMillisResult(
    block: EmptyFunctionResult<R>
): Pair<@LongLimit(from = 0) Long, R> {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    val result: R
    val time = measureTimeMillis { result = block() }
    return Pair(time, result)
}

/**
 * Measure the execution time the given action, and returns the time and the result of the method invocation.
 * @param block [EmptyFunctionResult]<R>
 * @return [Pair]<[Long], R> the first is the [Duration] the second is the [Result] of [block].
 */
@OptIn(ExperimentalTime::class)
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
 * @param loggingMethod [LoggingFunctionType]<Unit>
 * @param action [EmptyFunctionResult]<T>
 * @return T the result type of the action invocation.
 */

public inline fun <T> logMeasureTimeInMillis(
    loggingTitle: String = "Timing",
    loggingMethod: CLLogFunction = CL.debug,
    sensitivity: LogSensitivity = LogSensitivity.Sensitive,
    action: EmptyFunctionResult<T>
): T {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    val (time, result) = measureTimeResult(action)
    loggingMethod(
        tag = loggingTitle,
        message = "{}ms",
        placeholders = arrayOf(time.toString()),
        exception = null,
        sensitivity = sensitivity,
    )
    return result
}
