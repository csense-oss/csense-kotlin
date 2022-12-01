@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.extensions.comparable

import csense.kotlin.extensions.nullabillity.*

public inline fun <T> Comparable<T>.isEqualTo(other: T): Boolean =
    compareTo(other) == 0

public inline fun <T> Comparable<T>.isNotEqualTo(other: T): Boolean =
    compareTo(other) != 0

public inline fun <T> Comparable<T>.isGreaterThan(other: T): Boolean =
    compareTo(other) > 0

public inline fun <T> Comparable<T>.isGreaterThanOrEqualTo(other: T): Boolean =
    compareTo(other) >= 0

public inline fun <T> Comparable<T>.isLessThan(other: T): Boolean =
    compareTo(other) < 0


public inline fun <T> Comparable<T>.isLessThanOrEqualTo(other: T): Boolean =
    compareTo(other) <= 0