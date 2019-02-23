@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.extensions


/**
 * returns true if this is null or equal to the given argument.
 * does not return true if we are not null but the argument is null.
 * @receiver T?
 * @param other T? the value to compare to
 * @return Boolean true this is null or is equal to the other object (equals)
 */
inline fun <T> T?.isNullOrEqualTo(other: T?): Boolean = this == null || this == other
