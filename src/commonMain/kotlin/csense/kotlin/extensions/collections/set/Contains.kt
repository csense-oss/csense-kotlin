@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE") // see https://youtrack.jetbrains.com/issue/KT-60866/Phase-out-usages-of-SuppressINVISIBLEREFERENCE-INVISIBLEMEMBER-in-libraries


package csense.kotlin.extensions.collections.set


/**
 * Tells if the given value is not in the set
 * @receiver [Set]<T> the set to test for the given value
 * @param value T the value to test existence of
 * @return [Boolean] true if the value is not found / contained
 */
public inline fun <@kotlin.internal.OnlyInputTypes T> Set<T>.doesNotContain(value: T): Boolean =
    !contains(value)


/**
 * Tells if this set does contain any of the given data
 * Also known as "disjoint"
 * Optimized for other being a [Set]
 * @receiver [Set]<E> the
 * @param other [Iterable]<E>
 * @return [Boolean] true if at least one element from other is in this set.
 * @timecomplexity worst case o(n * lg(n))
 */
public inline fun <@kotlin.internal.OnlyInputTypes E> Set<E>.containsAny(other: Iterable<E>): Boolean {
    return if (other is Set) {
        //if we have 2 sets, then the optimization is to iterate over the smallest
        // set and call contains on the larger (linear is slower than logarithm)
        val smallest: Set<E>
        val largest: Set<E>
        if (size > other.size) {
            smallest = other
            largest = this
        } else {
            smallest = this
            largest = other
        }
        smallest.any { largest.contains(it) }
    } else {
        other.any { contains(it) }
    }

}

/**
 * Tells if this set does not contain any of the given data
 *
 * Optimized for other being a [Set]
 * @receiver [Set]<E>
 * @param other [Iterable]<E>
 * @return [Boolean] true if there are no disjoint elements
 * @timecomplexity worst case o(n * lg(n))
 */
public inline fun <@kotlin.internal.OnlyInputTypes E> Set<E>.doesNotContainAny(other: Iterable<E>): Boolean {
    return if (other is Set) {
        //if we have 2 sets, then the optimization is to iterate over the smallest
        // set and call contains on the larger (linear is slower than logarithm)
        val smallest: Set<E>
        val largest: Set<E>
        if (size > other.size) {
            smallest = other
            largest = this
        } else {
            smallest = this
            largest = other
        }
        smallest.none { largest.contains(it) }
    } else {
        other.none { contains(it) }
    }
}
