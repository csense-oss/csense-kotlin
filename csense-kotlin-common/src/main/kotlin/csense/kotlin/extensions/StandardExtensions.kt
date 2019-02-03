package csense.kotlin.extensions

import csense.kotlin.*
import kotlin.contracts.*

/**
 * Like run except it works for nullable blocks; iff null does nothing
 * @receiver T
 * @param block ReceiverFunction0<T, R>?
 * @return R?
 *
 * TODO potentially a better name..
 */
fun <T, R> T.runIfNotNull(block: ReceiverFunction0<T, R>?): R? = block?.let(::run)

/**
 * Like also, but for empty functions / lambdas.
 * @receiver T
 * @param block EmptyFunction
 * @return T
 */
@UseExperimental(ExperimentalContracts::class)
inline fun <T> T.also(block: EmptyFunction): T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    block()
    return this
}
