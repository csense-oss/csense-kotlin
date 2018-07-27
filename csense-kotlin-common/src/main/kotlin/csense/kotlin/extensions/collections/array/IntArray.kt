package csense.kotlin.extensions.collections.array

/**
 * Fills this array with the given value
 */
fun IntArray.fill(value: Int) =
        fillArray(count(), value, this::set)