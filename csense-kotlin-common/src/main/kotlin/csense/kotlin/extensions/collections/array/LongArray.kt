package csense.kotlin.extensions.collections.array




/**
 * A foreach, but not taking any result for the given receiver
 * @receiver
 * @param receiver (T) -> U
 */
inline fun <U> LongArray.forEachDiscard(crossinline receiver: Function1<Long, U>) =
        ForeachDiscardResult(count(), this::get, receiver)

/**
 * Fills this array with the given value
 */
fun LongArray.fill(value: Long) =
        fillArray(count(), value, this::set)