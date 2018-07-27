package csense.kotlin.extensions.collections.array


/**
 * Fills this array with the given value
 */
fun LongArray.fill(value: Long) =
        fillArray(count(), value, this::set)