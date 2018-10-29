package csense.kotlin.extensions.collections.generic

import csense.kotlin.extensions.primitives.*


typealias Function2Unit<T, U> = (T, U) -> Unit
typealias Function2IndexedUnit<T, U> = (Int, T, U) -> Unit

/**
 * This is the "internal" generic function ,from which all the instances will be using.
 * @receiver GenericCollectionExtensions
 */
inline fun <T> GenericCollectionExtensions.forEach2(length: Int, getter: Function1<Int, T>, action: Function2Unit<T, T>) {
    if (length < 0 || length.isOdd) {
        return
    }
    for (i in (0 until length step 2)) {
        val first = getter(i)
        val second = getter(i + 1)
        action(first, second)
    }

}