@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")


package csense.kotlin.extensions.nullabillity

import csense.kotlin.general.*
import kotlin.contracts.*
import kotlin.jvm.*

/**
 * returns true if this is null
 */
public inline val <T> T?.isNull: Boolean
    @JvmName("_isNull")
    get() = isNull()


@Deprecated(
    "Using \"isNull\" on compile time known as not null is always false",
    level = DeprecationLevel.ERROR,
    replaceWith = ReplaceWith("false")
)

@Suppress("UnusedReceiverParameter", "Unused", "MissingTestProperty")
public inline val Any.isNull: Nothing
    @JvmName("__isNull")
    get() = unexpected()

/**
 * returns true if this is not null.
 */
public inline val <T> T?.isNotNull: Boolean
    @JvmName("_isNotNull")
    get() = this != null

@Deprecated(
    "Using \"isNotNull\" on compile time known as not null is always true",
    level = DeprecationLevel.ERROR,
    replaceWith = ReplaceWith("true")
)
@Suppress("UnusedReceiverParameter", "Unused", "MissingTestProperty")
public inline val Any.isNotNull: Nothing
    @JvmName("__isNotNull")
    get() = unexpected()


/**
 * returns true if this is null.
 */

public inline fun <T> T?.isNull(): Boolean {
    contract {
        returns(true) implies (this@isNull == null)
        returns(false) implies (this@isNull != null)
    }
    return this == null
}


@Deprecated(
    "Using \"isNull()\" on compile time known as not null is always false",
    level = DeprecationLevel.ERROR,
    replaceWith = ReplaceWith("false")
)
@Suppress("UnusedReceiverParameter", "Unused", "MissingTestFunction")
public inline fun Any.isNull(): Nothing = unexpected()


/**
 * returns true if this is not null.
 */

public inline fun <T> T?.isNotNull(): Boolean {
    contract {
        returns(true) implies (this@isNotNull != null)
        returns(false) implies (this@isNotNull == null)
    }
    return this != null
}

@Deprecated(
    "Using \"isNotNull()\" on compile time known as not null is always true",
    level = DeprecationLevel.ERROR,
    replaceWith = ReplaceWith("false")
)
@Suppress("UnusedReceiverParameter", "Unused", "MissingTestFunction")
public inline fun Any.isNotNull(): Nothing = unexpected()
