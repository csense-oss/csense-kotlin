@file:Suppress("NOTHING_TO_INLINE") // see https://youtrack.jetbrains.com/issue/KT-60866/Phase-out-usages-of-SuppressINVISIBLEREFERENCE-INVISIBLEMEMBER-in-libraries

package org.csenseoss.kotlin.extensions.comparable

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