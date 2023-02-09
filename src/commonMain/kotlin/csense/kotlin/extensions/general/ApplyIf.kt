package csense.kotlin.extensions.general

import csense.kotlin.*
import kotlin.contracts.*


/**
 * applies the given function iff the given [shouldApply] is true
 * @receiver T
 * @param shouldApply [Boolean] whenever the given  [block] should be applied to the receiver
 * @param block [ReceiverFunctionUnit]<T,T> [block] to execute if [shouldApply] is true
 * @return the original value
 */
public inline fun <T> T.applyIf(
    shouldApply: Boolean,
    block: ReceiverFunctionUnit<T>
): T {
    contract {
        callsInPlace(block, InvocationKind.AT_MOST_ONCE)
    }
    return apply {
        if (shouldApply) {
            block()
        }
    }
}
