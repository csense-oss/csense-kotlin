package csense.kotlin.extensions.collections.array

inline fun <U> CharArray.forEachDiscard(receiver: (Char) -> U) {
    forEach {
        receiver(it)
    }
}
