package csense.kotlin.extensions.general

import csense.kotlin.*
import csense.kotlin.general.*
import kotlin.contracts.*


/**
 * Uses this value iff not null or another if it is.
 * the first function (argument) will receive the value iff it is not null, the second is without any parameters
 *
 * @receiver T?
 * @param ifNotNull T.() -> Unit the action to perform iff this is not null
 * @param ifNull [EmptyFunction]  if the receiver is null this action will be performed
 */
public inline fun <T> T?.useOr(
    ifNotNull: ReceiverFunctionUnit<T>,
    ifNull: EmptyFunction
) {
    contract {
        callsInPlace(ifNotNull, InvocationKind.AT_MOST_ONCE)
        callsInPlace(ifNull, InvocationKind.AT_MOST_ONCE)
    }
    if (this != null) {
        ifNotNull(this)
    } else {
        ifNull()
    }
}

@Deprecated(
    "Using \"useOr\" on compile time known not null is always ifNotNull",
    level = DeprecationLevel.ERROR,
    replaceWith = ReplaceWith("ifNotNull")
)
@Suppress("UnusedReceiverParameter", "Unused", "MissingTestFunction", "UNUSED_PARAMETER")
public inline fun <T> Any.useOr(
    ifNotNull: ReceiverFunctionUnit<T>,
    ifNull: EmptyFunction
): Nothing = unexpected()
