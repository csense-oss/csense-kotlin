@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package csense.kotlin.extensions.general

import csense.kotlin.*
import csense.kotlin.general.*
import kotlin.contracts.*


/**
 * this if it is not null, or the other if this is null
 * the same as ?:
 *
 * @receiver T? the optional value to either use or the supplied
 * @param ifNull T the other value to use if this receiver is null
 * @return T the non-null value
 */
public inline infix fun <@kotlin.internal.OnlyInputTypes reified T> T?.orIfNull(
    ifNull: T & Any
): T & Any = this ?: ifNull

@Deprecated(
    "Using \"orIfNull\" on compile time known not null is always this",
    level = DeprecationLevel.ERROR,
    replaceWith = ReplaceWith("this")
)
@Suppress("UnusedReceiverParameter", "Unused", "MissingTestFunction", "UNUSED_PARAMETER")
public inline fun <T> Any.orIfNull(
    ifNull: T & Any
): Nothing = unexpected()


/**
 * this if it is not null, or the other if this is null
 * the same as ?:
 *
 * @receiver T? the optional value to either use or the supplied
 * @param ifNullAction [Function0R] the other value (to compute) if this receiver is null
 * @return T the non-null value
 */

public inline infix fun <reified T> T?.orIfNullLazy(ifNullAction: Function0R<T>): T {
    contract {
        callsInPlace(ifNullAction, InvocationKind.AT_MOST_ONCE)
    }
    return this ?: ifNullAction()
}


@Deprecated(
    "Using \"orIfNullLazy\" on compile time known not null is always this",
    level = DeprecationLevel.ERROR,
    replaceWith = ReplaceWith("this")
)
@Suppress("UnusedReceiverParameter", "Unused", "MissingTestFunction", "UNUSED_PARAMETER")
public inline fun <T> Any.orIfNullLazy(
    ifNullAction: Function0R<T>
): Nothing = unexpected()
