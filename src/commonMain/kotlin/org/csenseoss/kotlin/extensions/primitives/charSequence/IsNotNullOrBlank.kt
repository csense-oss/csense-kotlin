@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER")

package org.csenseoss.kotlin.extensions.primitives.charSequence

import org.csenseoss.kotlin.general.*
import kotlin.contracts.*


/**
 * is this NOT null or blank, akk it's not null nor a "blank" [CharSequence]
 * @return [Boolean] true if this is not null, and it is not blank , false otherwise
 */
public inline fun CharSequence?.isNotNullOrBlank(): Boolean {
    contract {
        returns(true) implies (this@isNotNullOrBlank != null)
    }
    return !this.isNullOrBlank()
}

@Suppress("UnusedReceiverParameter")
@Deprecated(
    "receiver known at compile time to not be null. should use isNotBlank instead",
    level = DeprecationLevel.ERROR
)
public inline fun CharSequence.isNotNullOrBlank(): Nothing = unexpected()