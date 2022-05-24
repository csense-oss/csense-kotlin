@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions

import csense.kotlin.*
import kotlin.contracts.*
import kotlin.jvm.*


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
    if(this == null){
        action()
    }
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
    @JvmName("_isNull")
    get() = this == null

/**
 * returns true if this is not null.
 */
public inline val <T> T?.isNotNull: Boolean
    @JvmName("_isNotNull")
    get() = this != null

/**
 * returns true if this is null.
 */
@OptIn(ExperimentalContracts::class)
public inline fun <T> T?.isNull(): Boolean {
    contract {
        returns(true) implies (this@isNull != null)
        returns(false) implies (this@isNull == null)
    }
    return this == null
}

/**
 * returns true if this is not null.
 */
@OptIn(ExperimentalContracts::class)
public inline fun <T> T?.isNotNull(): Boolean {
    contract {
        returns(true) implies (this@isNotNull != null)
        returns(false) implies (this@isNotNull == null)
    }
    return this != null
}