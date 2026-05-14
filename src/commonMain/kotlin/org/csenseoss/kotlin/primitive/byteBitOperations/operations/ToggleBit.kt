@file:Suppress("NOTHING_TO_INLINE")

package org.csenseoss.kotlin.primitive.byteBitOperations.operations

import org.csenseoss.kotlin.primitive.byteBitOperations.ByteBitOperations

public inline fun ByteBitOperations.toggleBit(bitIndex: Int, setBit: Boolean): Byte = when {
    setBit -> setBit(bitIndex)
    else -> zeroBit(bitIndex)
}