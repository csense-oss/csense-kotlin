@file:Suppress("unused")

package csense.kotlin.extensions.collections.array.generic

import csense.kotlin.*
import csense.kotlin.extensions.collections.*
import csense.kotlin.extensions.primitives.*

/**
 * The standard lib only have a foreach on (T) -> Unit
 * but if the given lambda produces a result , that we just ignore, then we are out of luck.
 *
 * @param receiver Function1<T,U>
 */
inline fun <T, U> GenericArray.foreachDiscardResult(
        count: Int,
        getter: GenericGetterIndexMethod<T>,
        receiver: Function1<T, U>
) {
    count.forEach {
        receiver(getter(it))
    }
}