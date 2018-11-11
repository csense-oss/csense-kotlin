package csense.kotlin.extensions.collections.array


/**
 * A foreach, but not taking any result for the given receiver
 * @receiver ByteArray
 * @param receiver (T) -> U
 */
inline fun <U> CharArray.forEachDiscard(crossinline receiver: kotlin.Function1<Char, U>) =
        ForeachDiscardResult(count(), this::get, receiver)


/**
 * Fills this array with the given value
 */
fun CharArray.fill(value: Char) =
        fillArray(count(), value, this::set)