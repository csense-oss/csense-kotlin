package csense.kotlin.extensions.collections.generic.collection.operations

import csense.kotlin.annotations.numbers.*
import csense.kotlin.extensions.collections.*
import csense.kotlin.extensions.collections.generic.collection.*
import csense.kotlin.extensions.primitives.int.*

/**
 * The standard library only have a foreach on (T) -> Unit
 * but if the given lambda produces a result , that we just ignore, then we are out of luck.

 * @receiver [GenericArray]
 * @param count [Int]
 * @param getter [Function1]<Int,T>
 * @param receiver [Function1]<T, U>
 */
@Suppress("UnusedReceiverParameter")
public inline fun <T, U> GenericCollections.foreachDiscardResult(
    @IntLimit(from = 0) count: Int,
    getter: GenericGetterIndexMethod<T>,
    receiver: Function1<T, U>
) {
    count.forEach { it: Int ->
        receiver(getter(it))
    }
}
