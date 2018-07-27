package csense.kotlin.extensions.collections.array

/**
 * Fills this array with the given value
 */
fun FloatArray.fill(value: Float)=
        fillArray(count(), value, this::set)