package csense.kotlin.general.coroutine

import kotlinx.coroutines.*
import kotlin.contracts.*
import kotlin.coroutines.*

/**
 * This is intended for some very select situations. One might be in a suspending function
 * (eg "suspend fun main(..")
 * @param block [@kotlin.ExtensionFunctionType] Function1<CoroutineScope, R>
 * @return R
 */
@DelicateCoroutinesApi
public suspend inline fun <R> withCurrentCoroutineScope(
    block: CoroutineScope.() -> R
): R {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return with(
        receiver = CoroutineScope(context = coroutineContext),
        block = block
    )
}