@file:Suppress("unused")

package csense.kotlin.extensions.collections.array.shared

import csense.kotlin.*
import csense.kotlin.annotations.numbers.*
import csense.kotlin.extensions.collections.*
import csense.kotlin.extensions.primitives.*
import csense.kotlin.extensions.primitives.int.*
import csense.kotlin.Function1

/**
 * The standard library only have a foreach on (T) -> Unit
 * but if the given lambda produces a result , that we just ignore, then we are out of luck.

 * @receiver [GenericArray]
 * @param count [Int]
 * @param getter [Function1]<Int,T>
 * @param receiver [Function1]<T, U>
 */
public inline fun <T, U> GenericArray.foreachDiscardResult(
    @IntLimit(from = 0) count: Int,
    getter: GenericGetterIndexMethod<T>,
    receiver: Function1<T, U>
) {
    count.forEach {
        receiver(getter(it))
    }
}
