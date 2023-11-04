@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package csense.kotlin.extensions.nullabillity

import csense.kotlin.*
import csense.kotlin.general.*
import kotlin.contracts.*


/**
 * Performs the given action if we are null
 * @receiver [Any]? the optional value
 * @param action [EmptyFunction] the function to run if the receiver is not null
 */
public inline fun <T> T?.ifNull(action: EmptyFunction) {
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }
    if (this == null) {
        action()
    }
}

@Deprecated(
    "Using \"ifNull\" on compiletime known not null is useless",
    level = DeprecationLevel.ERROR,
    replaceWith = ReplaceWith("")
)
@Suppress("UnusedReceiverParameter", "Unused", "UNUSED_PARAMETER")
public inline fun Any.ifNull(action: EmptyFunction): Nothing = unexpected()


/**
 * Performs the given action, if we are not null
 * @receiver T? the optional value
 * @param action [FunctionUnit]<T> the action to call if the receiver is not null
 */

public inline fun <T> T?.ifNotNull(action: FunctionUnit<T>) {
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }
    this?.let(action)
}

@Deprecated(
    "Using \"ifNotNull\" on compile time known as not null is equivilent of always calling it",
    level = DeprecationLevel.ERROR,
    replaceWith = ReplaceWith("action()")
)
@Suppress("UnusedReceiverParameter", "Unused", "UNUSED_PARAMETER")
public inline fun <T> Any.ifNotNull(action: FunctionUnit<T>): Nothing = unexpected()
