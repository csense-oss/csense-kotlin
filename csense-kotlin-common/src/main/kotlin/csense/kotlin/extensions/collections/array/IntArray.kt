package csense.kotlin.extensions.collections.array


/**
 * A foreach, but not taking any result for the given receiver
 * @receiver
 * @param receiver (T) -> U
 */
inline fun <U> IntArray.forEachDiscard(crossinline receiver: Function1<Int, U>) =
        ForeachDiscardResult(count(), this::get, receiver)

/**
 * Fills this array with the given value
 */
fun IntArray.fill(value: Int) =
        fillArray(count(), value, this::set)