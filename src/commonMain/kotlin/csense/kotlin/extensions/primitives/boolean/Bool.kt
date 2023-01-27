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
public inline fun <Result> Boolean.onFalse(action: () -> Result): Result? {
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }
    return when (this) {
        true -> null
        false -> action()
    }
}

/**
 * performs the action if the boolean is true.
 * @receiver [Boolean]
 * @param action [EmptyFunction]
 * @return [Boolean]
 */
public inline fun <Result> Boolean.onTrue(action: () -> Result): Result? {
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }
    return when (this) {
        true -> action()
        false -> null
    }
}


/**
 * Makes a more "elegant" sentence for some expressions same as "OnTrue"
 * @receiver [Boolean]
 * @param action [EmptyFunction]
 * @return [Boolean]
 */
public inline fun <Result> Boolean.ifTrue(action: () -> Result): Result? {
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
public inline fun <Result> Boolean.ifFalse(action: () -> Result): Result? {
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }
    return onFalse(action)
}

