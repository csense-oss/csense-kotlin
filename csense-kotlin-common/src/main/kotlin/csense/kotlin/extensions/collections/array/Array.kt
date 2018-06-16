package csense.kotlin.extensions.collections.array


inline fun <T, U> Array<T>.forEachDiscard(receiver: (T) -> U) {
    forEach {
        receiver(it)
    }
}
