@file:Suppress("NOTHING_TO_INLINE")

package org.csenseoss.kotlin.primitive.byteBitOperations.operations

import org.csenseoss.kotlin.annotations.numbers.limit.*
import org.csenseoss.kotlin.primitive.byteBitOperations.ByteBitOperations


@ByteLimit(from = 0x0F, to = 0xFF.toByte())
public inline fun ByteBitOperations.shiftLowerNibbleToUpper(): Byte =
    shl(4)
