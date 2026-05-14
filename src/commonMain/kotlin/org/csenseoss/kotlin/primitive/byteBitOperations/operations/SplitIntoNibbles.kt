@file:Suppress("NOTHING_TO_INLINE")

package org.csenseoss.kotlin.primitive.byteBitOperations.operations

import org.csenseoss.kotlin.primitive.byteBitOperations.ByteBitOperations
import org.csenseoss.kotlin.primitive.byteBitOperations.NibblePair
import org.csenseoss.kotlin.primitive.byteBitOperations.bits


public inline fun ByteBitOperations.splitIntoNibbles(): NibblePair {
    val lower: Byte = clearUpperNibble()
    val upper: Byte = (this shr 4).bits.clearUpperNibble()
    return NibblePair(upper, lower)
}
