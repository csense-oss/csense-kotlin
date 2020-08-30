package csense.kotlin.extensions

import csense.kotlin.*
import kotlin.contracts.*

/**
 * Like also, but for empty functions / lambdas.
 * @receiver T
 * @param block [EmptyFunction]
 * @return T
 */
@OptIn(ExperimentalContracts::class)
public inline fun <T> T.also(block: EmptyFunction): T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    block()
    return this
}
