@file:Suppress("NOTHING_TO_INLINE")

package org.csenseoss.kotlin.primitive.byteBitOperations.operations

import org.csenseoss.kotlin.annotations.numbers.limit.*
import org.csenseoss.kotlin.primitive.byteBitOperations.ByteBitOperations
import kotlin.experimental.*

@ByteLimit(from = 0, to = 0x0F)
public inline fun ByteBitOperations.clearUpperNibble(): Byte =
    byte.and(0b0000_1111)
