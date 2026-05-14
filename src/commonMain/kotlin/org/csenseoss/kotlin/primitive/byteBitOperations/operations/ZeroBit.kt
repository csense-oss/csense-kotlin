@file:Suppress("NOTHING_TO_INLINE")

package org.csenseoss.kotlin.primitive.byteBitOperations.operations

import org.csenseoss.kotlin.primitive.byteBitOperations.ByteBitOperations
import kotlin.experimental.*

public inline fun ByteBitOperations.zeroBit(bitIndex: Int): Byte {
    val bitMask: Byte = 1.shl(bitIndex).inv().toByte()
    return byte.and(bitMask)
}
