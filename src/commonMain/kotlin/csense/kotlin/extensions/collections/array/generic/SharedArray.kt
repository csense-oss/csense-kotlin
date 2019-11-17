@file:Suppress("unused")

package csense.kotlin.extensions.collections.array.generic

import csense.kotlin.Function1
import csense.kotlin.annotations.numbers.IntLimit
import csense.kotlin.extensions.collections.GenericGetterIndexMethod
import csense.kotlin.extensions.primitives.forEach

/**
 * The standard lib only have a foreach on (T) -> Unit
 * but if the given lambda produces a result , that we just ignore, then we are out of luck.
 *
 * @param receiver Function1<T,U>
 */
inline fun <T, U> GenericArray.foreachDiscardResult(
        @IntLimit(from = 0) count: Int,
        getter: GenericGetterIndexMethod<T>,
        receiver: Function1<T, U>
) {
    count.forEach {
        receiver(getter(it))
    }
}