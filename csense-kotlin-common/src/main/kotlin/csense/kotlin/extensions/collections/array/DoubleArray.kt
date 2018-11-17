package csense.kotlin.extensions.collections.array


/**
 * A foreach, but not taking any result for the given receiver
 * @receiver
 * @param receiver (T) -> U
 */
inline fun <U> DoubleArray.forEachDiscard(crossinline receiver: Function1<Double, U>) =
        ForeachDiscardResult(count(), this::get, receiver)

/**
 * Fills this array with the given value
 */
fun DoubleArray.fill(value: Double) =
        fillArray(count(), value, this::set)