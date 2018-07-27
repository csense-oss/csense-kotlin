package csense.kotlin.extensions.collections.array

import csense.kotlin.Function1

inline fun <U> CharArray.forEachDiscard(receiver: Function1<Char, U>) = forEach {
    receiver(it)
}


/**
 * Fills this array with the given value
 */
fun CharArray.fill(value: Char) =
        fillArray(count(), value, this::set)