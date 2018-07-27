package csense.kotlin.extensions.collections.array

/**
 * Fills this array with the given value
 */
fun DoubleArray.fill(value: Double) =
        fillArray(count(), value, this::set)