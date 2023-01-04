package csense.kotlin.general.coroutine

import kotlinx.coroutines.*
import kotlin.contracts.*
import kotlin.coroutines.*

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