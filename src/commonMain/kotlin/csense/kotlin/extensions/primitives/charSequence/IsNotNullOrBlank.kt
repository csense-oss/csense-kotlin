@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER")

package csense.kotlin.extensions.primitives.charSequence

import csense.kotlin.general.*


/**
 * is this NOT null or blank, akk it's not null nor a "blank" [CharSequence]
 * @return [Boolean] true if this is not null, and it is not blank , false otherwise
 */
public inline fun CharSequence?.isNotNullOrBlank(): Boolean {
    return !this.isNullOrBlank()
}

@Suppress("UnusedReceiverParameter")
@Deprecated(
    "receiver known at compile time to not be null. should use isNotBlank instead",
    level = DeprecationLevel.ERROR
)
public inline fun CharSequence.isNotNullOrBlank(): Nothing = unexpected()
