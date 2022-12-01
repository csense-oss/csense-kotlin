package csense.kotlin.extensions.collections.iterable

/**
 * Finds the largest element (and returns it if found)
 * @receiver [Iterable]<E> the iterable to go though
 * @param selector [Function1]<E, V> given an element (E) selects a Value (V) to be used for comparisons
 * @return E? the largest element or null if non were found
 * @timecomplexity O(n)
 */
public inline fun <E, V : Comparable<V>> Iterable<E>.largest(
    selector: Function1<E, V>
): E? {
    var currentResult: E? = null
    var currentValue: V? = null
    forEach {
        val value = selector(it)
        val safeCurrentValue = currentValue
        if (safeCurrentValue == null || value > safeCurrentValue) {
            currentResult = it
            currentValue = value
        }
    }
    return currentResult
}
