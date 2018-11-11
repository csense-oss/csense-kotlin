package csense.kotlin.extensions.collections.array


/**
 * A foreach, but not taking any result for the given receiver
 * @receiver
 * @param receiver (T) -> U
 */
inline fun <U> ShortArray.forEachDiscard(crossinline receiver: Function1<Short, U>) =
        ForeachDiscardResult(count(), this::get, receiver)


/**
 * Fills this array with the given value
 */
fun ShortArray.fill(value: Short) =
        fillArray(count(), value, this::set)