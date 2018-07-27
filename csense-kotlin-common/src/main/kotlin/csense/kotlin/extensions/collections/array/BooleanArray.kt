package csense.kotlin.extensions.collections.array

/**
 * Fills this array with the given value
 */
fun BooleanArray.fill(value: Boolean) =
        fillArray(count(), value, this::set)