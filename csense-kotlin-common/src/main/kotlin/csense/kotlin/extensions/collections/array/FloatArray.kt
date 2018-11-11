package csense.kotlin.extensions.collections.array



/**
 * A foreach, but not taking any result for the given receiver
 * @receiver
 * @param receiver (T) -> U
 */
inline fun <U> FloatArray.forEachDiscard(crossinline receiver: Function1<Float, U>) =
        ForeachDiscardResult(count(), this::get, receiver)

/**
 * Fills this array with the given value
 */
fun FloatArray.fill(value: Float)=
        fillArray(count(), value, this::set)