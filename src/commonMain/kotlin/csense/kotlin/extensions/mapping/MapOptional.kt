@file:Suppress("unused", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.mapping

import csense.kotlin.*
import csense.kotlin.extensions.nullabillity.*
import csense.kotlin.general.*
import kotlin.Function1
import kotlin.contracts.*


/**
 * Maps an optional value into another value
 * @receiver [Any]?
 * @param ifNotNull U the value if 'this' is not null
 * @param ifNull U the value if 'this' is null
 * @return U the value depending on 'this' value
 */
public inline fun <U> Any?.mapOptional(
    ifNotNull: U,
    ifNull: U
): U {
    return this.isNotNull().map(ifNotNull, ifNull)
}

@Deprecated(
    "Using \"mapOptional\" on compile time known not null is always ifNotNull",
    level = DeprecationLevel.ERROR,
    replaceWith = ReplaceWith("ifNotNull")
)
@Suppress("UnusedReceiverParameter", "Unused", "UNUSED_PARAMETER")
public inline fun <U> Any.mapOptional(
    ifNotNull: U,
    ifNull: U
): Nothing = unexpected()

/**
 * Maps an optional value into another value
 * @receiver [Any]?
 * @param ifNotNull [Function1]<[U>] the value if 'this' is not null (with the non-null value)
 * @param ifNull [EmptyFunctionResult]<[U]> the value if 'this' is null
 * @return [U] the value depending on 'this' value
 */

public inline fun <U, T> T?.mapLazyOptional(
    ifNotNull: Function1<T, U>,
    ifNull: EmptyFunctionResult<U>
): U {
    contract {
        callsInPlace(ifNotNull, InvocationKind.AT_MOST_ONCE)
        callsInPlace(ifNull, InvocationKind.AT_MOST_ONCE)
    }
    return if (this != null) {
        ifNotNull(this)
    } else {
        ifNull()
    }
}

@Deprecated(
    "Using \"mapLazyOptional\" on compile time known not null is always ifNotNull",
    level = DeprecationLevel.ERROR,
    replaceWith = ReplaceWith("ifNotNull")
)
@Suppress("UnusedReceiverParameter", "Unused", "MissingTestFunction", "UNUSED_PARAMETER")
public inline fun <U, T> Any.mapLazyOptional(
    ifNotNull: Function1<T, U>,
    ifNull: EmptyFunctionResult<U>
): Nothing = unexpected()
