package csense.kotlin.patterns.guard

import kotlin.contracts.*
import kotlin.jvm.*

//TODO consider
@JvmInline
public value class Guard<T>(public val item: T?) {
    public inline infix fun orReturn(action: () -> Nothing): T {
        contract {
            callsInPlace(action, InvocationKind.AT_MOST_ONCE)
        }
        if (item == null) {
            action()
        }
        return item
    }
}

public fun <T> guard(optional: T?): Guard<T> {
    return Guard(optional)
}

@OptIn(ExperimentalContracts::class)
public fun <T> guard(action: () -> T?): Guard<T> {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return Guard(action())
}