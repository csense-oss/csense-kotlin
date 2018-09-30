package csense.kotlin.extensions

import csense.kotlin.EmptyFunction
import csense.kotlin.FunctionUnit
import csense.kotlin.extensions.primitives.ifTrue


/**
 * performs the given action if we are null
 * @receiver Any? the optional value
 * @param action EmptyFunction the function to run if the receiver is not null
 */
inline fun Any?.ifNull(crossinline action: EmptyFunction) {
    this.isNull.ifTrue(action)
}

/**
 * performs the given action, if we are not null
 * @receiver T? the optional value
 * @param action FunctionUnit<T> the action to call if the receiver is not null
 */
inline fun <T> T?.ifNotNull(crossinline action: FunctionUnit<T>) {
    this?.let { action(it) }
}

/**
 * returns true if this is null
 */
inline val Any?.isNull
    get() = this == null

/**
 * returns true if this is not null.
 */
inline val Any?.isNotNull
    get() = this != null

