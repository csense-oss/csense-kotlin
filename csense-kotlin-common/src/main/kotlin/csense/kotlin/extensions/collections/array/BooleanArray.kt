package csense.kotlin.extensions.collections.array


/**
 * A foreach, but not taking any result for the given receiver
 * @receiver Array<T>
 * @param receiver (T) -> U
 */
inline fun <U> BooleanArray.forEachDiscard(crossinline receiver: Function1<Boolean, U>) =
        ForeachDiscardResult(count(), this::get, receiver)

/**
 *
 * @receiver BooleanArray
 * @param value Boolean
 */
fun BooleanArray.fill(value: Boolean) =
        fillArray(count(), value, this::set)