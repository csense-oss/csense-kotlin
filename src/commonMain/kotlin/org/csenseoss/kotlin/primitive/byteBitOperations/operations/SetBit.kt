@file:Suppress("NOTHING_TO_INLINE")

package org.csenseoss.kotlin.primitive.byteBitOperations.operations

import org.csenseoss.kotlin.primitive.byteBitOperations.ByteBitOperations
import kotlin.experimental.*


public inline fun ByteBitOperations.setBit(bitIndex: Int): Byte {
    val bitMask: Byte = 1.shl(bitIndex).toByte()
    return byte.or(bitMask)
}