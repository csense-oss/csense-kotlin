@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.extensions

import kotlin.contracts.*


/**
 * returns true if this is null or equal to the given argument. (using equals)
 * does not return true if we are not null but the argument is null.
 * @receiver T?
 * @param other T? the value to compare to
 * @return [Boolean] true this is null or is equal to the other object (equals)
 */

public inline fun <T> T?.isNullOrEqualTo(other: T?): Boolean {
    contract {
        returns(false) implies (this@isNullOrEqualTo != null)
    }
    return this == null || this == other
}
