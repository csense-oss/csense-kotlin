package csense.kotlin.extensions.collections.iterable

import kotlin.contracts.*

public inline fun <T> Iterable<T>.skipIfEmptyOr(function: () -> Unit) {
    contract {
        callsInPlace(function, InvocationKind.AT_MOST_ONCE)
    }
    if (isNotEmpty()) {
        function()
    }
}
