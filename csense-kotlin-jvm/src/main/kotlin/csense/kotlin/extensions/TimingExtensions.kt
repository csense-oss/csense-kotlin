package csense.kotlin.extensions

import csense.kotlin.*
import kotlin.contracts.*

/**
 *
 * @param block EmptyFunctionResult<R>
 * @return Pair<Long, R>
 */
@UseExperimental(ExperimentalContracts::class)
inline fun <R> measureTimeMillisResult(block: EmptyFunctionResult<R>): Pair<Long, R> {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    val start = System.currentTimeMillis()
    val result = block()
    val time = System.currentTimeMillis() - start
    return Pair(time, result)
}

