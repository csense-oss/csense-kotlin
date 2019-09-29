@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions

import csense.kotlin.*
import csense.kotlin.extensions.primitives.*


/**
 * performs the given action if we are null
 * @receiver Any? the optional value
 * @param action EmptyFunction the function to run if the receiver is not null
 */
inline fun <T> T?.ifNull(action: EmptyFunction) {
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
inline val <T> T?.isNull: Boolean
    get() {
        return this == null
    }

/**
 * returns true if this is not null.
 */
inline val <T> T?.isNotNull: Boolean
    get() {
        return this != null
    }
