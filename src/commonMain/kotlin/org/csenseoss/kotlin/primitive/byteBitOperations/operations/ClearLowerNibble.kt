@file:Suppress("NOTHING_TO_INLINE")

package org.csenseoss.kotlin.primitive.byteBitOperations.operations

import org.csenseoss.kotlin.annotations.numbers.limit.*
import org.csenseoss.kotlin.primitive.byteBitOperations.ByteBitOperations
import kotlin.experimental.*


@ByteLimit(from = 0x0F, to = 0xFF.toByte())
public inline fun ByteBitOperations.clearLowerNibble(): Byte =
    byte.and(0b1111_0000.toByte())
