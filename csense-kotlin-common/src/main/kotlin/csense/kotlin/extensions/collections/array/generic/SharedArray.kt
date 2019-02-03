package csense.kotlin.extensions.collections.array.generic

import csense.kotlin.*
import csense.kotlin.extensions.collections.*
import csense.kotlin.extensions.primitives.*


/**
 * Fills this array with the given value
 * @param count Int the number of items to fill
 * @param value T with this value
 * @param setter Function2<Int, T, Unit> and the "setter" function.
 */
inline fun <T> fillArray(
        count: Int,
        value: T,
        crossinline setter: GenericSetterIndexMethod<T>) {
    count.forEach {
        setter(it, value)
    }
}

/**
 * The standard lib only have a foreach on (T) -> Unit
 * but if the given lambda produces a result , that we just ignore, then we are out of luck.
 *
 * @param receiver Function1<T,U>
 */
inline fun <T, U> ForeachDiscardResult(
        count: Int,
        getter: GenericGetterIndexMethod<T>,
        receiver: Function1<T, U>
) {
    count.forEach {
        receiver(getter(it))
    }
}