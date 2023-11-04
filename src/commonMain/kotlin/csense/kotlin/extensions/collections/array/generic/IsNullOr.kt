@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")


package csense.kotlin.extensions.collections.array.generic

import csense.kotlin.general.*
import kotlin.contracts.*


/**
 * Tells if this [Array] is NOT null And NOT empty (size > 0)
 * @receiver [Array]<T>? the nullable Array
 * @return [Boolean] true if the Array is NOT null AND NOT empty
 */

public inline fun <T> Array<T>?.isNotNullOrEmpty(): Boolean {
    contract {
        returns(true) implies (this@isNotNullOrEmpty != null)
    }
    return this != null && this.isNotEmpty()
}

@Suppress("UnusedReceiverParameter", "MissingTestFunction")
@Deprecated(
    "Receiver known at compile time to not be null, thus isNotNull will always be true. Use isNotEmpty instead",
    level = DeprecationLevel.ERROR,
    replaceWith = ReplaceWith("this.isNotEmpty()")
)
public inline fun Array<Any>.isNotNullOrEmpty(): Nothing = unexpected()

