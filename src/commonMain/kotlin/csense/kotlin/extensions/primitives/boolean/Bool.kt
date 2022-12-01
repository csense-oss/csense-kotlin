@file:Suppress("unused", "NOTHING_TO_INLINE")


package csense.kotlin.extensions.primitives.boolean

import csense.kotlin.*
import kotlin.contracts.*


/**
 * performs the action if the boolean is false.
 * @receiver [Boolean]
 * @param action [EmptyFunction]
 * @return [Boolean]
 */
public inline fun Boolean.onFalse(action: EmptyFunction): Boolean {
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }
    if (!this) {
        action()
    }
    return this
}

/**
 * performs the action if the boolean is true.
 * @receiver [Boolean]
 * @param action [EmptyFunction]
 * @return [Boolean]
 */
public inline fun Boolean.onTrue(action: EmptyFunction): Boolean {
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }
    if (this) {
        action()
    }
    return this
}


/**
 * Makes a more "elegant" sentence for some expressions same as "OnTrue"
 * @receiver [Boolean]
 * @param action [EmptyFunction]
 * @return [Boolean]
 */
public inline fun Boolean.ifTrue(action: EmptyFunction): Boolean {
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }
    return onTrue(action)
}


/**
 * Makes a more "elegant" sentence for some expressions, same as "onFalse"
 * @receiver [Boolean]
 * @param action [EmptyFunction]
 * @return [Boolean]
 */
public inline fun Boolean.ifFalse(action: EmptyFunction): Boolean {
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }
    return onFalse(action)
}

