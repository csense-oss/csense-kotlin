@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions

import csense.kotlin.*
import csense.kotlin.extensions.primitives.*
import kotlin.contracts.*


/**
 * Performs the given action if we are null
 * @receiver [Any]? the optional value
 * @param action [EmptyFunction] the function to run if the receiver is not null
 */
@OptIn(ExperimentalContracts::class)
public inline fun <T> T?.ifNull(action: EmptyFunction) {
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }
    this.isNull.ifTrue(action)
}

/**
 * Performs the given action, if we are not null
 * @receiver T? the optional value
 * @param action [FunctionUnit]<T> the action to call if the receiver is not null
 */
@OptIn(ExperimentalContracts::class)
public inline fun <T> T?.ifNotNull(action: FunctionUnit<T>) {
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }
    this?.let(action)
}

/**
 * returns true if this is null
 */
public inline val <T> T?.isNull: Boolean
    get() {
        return this == null
    }

/**
 * returns true if this is not null.
 */
public inline val <T> T?.isNotNull: Boolean
    get() {
        return this != null
    }
