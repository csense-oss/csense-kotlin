@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package csense.kotlin.extensions.primitives.charSequence

import csense.kotlin.general.*


/**
 * is this NOT null or empty, akk it's not null nor an empty [CharSequence] (length = 0)
 *
 * it can be a single space though (which will yield true)
 * @return [Boolean] true if this is not null, and it is not empty (length =0) false otherwise
 */
public inline fun CharSequence?.isNotNullOrEmpty(): Boolean {
    return !this.isNullOrEmpty()
}

@Suppress("UnusedReceiverParameter")
@Deprecated(
    "receiver known at compile time to not be null. should use isNotEmpty instead",
    level = DeprecationLevel.ERROR
)
public inline fun CharSequence.isNotNullOrEmpty(): Nothing = unexpected()
