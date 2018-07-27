package csense.kotlin.extensions.collections.array

import csense.kotlin.extensions.primitives.*


/**
 * Fills this array with the given value
 */
internal inline fun <T> fillArray(
        count: Int,
        value: T,
        crossinline setter: Function2<Int, T, Unit>) {
    count.forEach {
        setter(it, value)
    }
}