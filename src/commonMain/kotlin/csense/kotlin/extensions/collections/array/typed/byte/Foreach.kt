@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER")

package csense.kotlin.extensions.collections.array.typed.byte

import csense.kotlin.*
import csense.kotlin.extensions.collections.generic.collection.*
import csense.kotlin.extensions.collections.generic.collection.operations.*

/**
 * A foreach, but not taking any result for the given receiver
 * @receiver [ByteArray]
 * @param action [Function1]<[Byte], U>
 */
public inline fun <U> ByteArray.forEachDiscard(action: Function1<Byte, U>): Unit =
    GenericCollections.foreachDiscardResult(
        count = count(),
        getter = this::get,
        receiver = action
    )


/**
 * Performs backwards traversal on this [ByteArray].
 */
public inline fun ByteArray.forEachBackwards(action: FunctionUnit<Byte>): Unit =
    GenericCollections.forEachBackwards(
        length = count(),
        getter = this::elementAt,
        action = action
    )
