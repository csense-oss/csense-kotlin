@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions

import csense.kotlin.*
import csense.kotlin.extensions.primitives.*
import kotlin.contracts.*


/**
 * performs the given action if we are null
 * @receiver Any? the optional value
 * @param action EmptyFunction the function to run if the receiver is not null
 */
@UseExperimental(kotlin.contracts.ExperimentalContracts::class)
inline fun Any?.ifNull(action: EmptyFunction) {
    this.isNull.ifTrue(action)
}

/**
 * performs the given action, if we are not null
 * @receiver T? the optional value
 * @param action FunctionUnit<T> the action to call if the receiver is not null
 */
inline fun <T> T?.ifNotNull(action: FunctionUnit<T>) {
    this?.let(action)
}

/**
 * returns true if this is null
 */
@UseExperimental(kotlin.contracts.ExperimentalContracts::class)
inline val Any?.isNull: Boolean
    get() {
        contract {
            returns(true) implies (this@isNull == null)
        }
        return this == null
    }

/**
 * returns true if this is not null.
 */
@UseExperimental(kotlin.contracts.ExperimentalContracts::class)
inline val Any?.isNotNull: Boolean
    get() {
        contract {
            returns(true) implies (this@isNotNull != null)
        }
        return this != null
    }
