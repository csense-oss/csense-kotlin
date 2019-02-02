package csense.kotlin.extensions

import csense.kotlin.EmptyFunctionResult
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 *
 * @param block EmptyFunctionResult<R>
 * @return Pair<Long, R>
 */
@UseExperimental(ExperimentalContracts::class)
fun <R> MeasureTimeResult(block: EmptyFunctionResult<R>): Pair<Long, R> {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    val start = System.currentTimeMillis()
    val result = block()
    val time = System.currentTimeMillis() - start
    return Pair(time, result)
}