@file:Suppress("unused", "NOTHING_TO_INLINE")

package org.csenseoss.kotlin.primitive.byteBitOperations

import org.csenseoss.kotlin.annotations.numbers.limit.*
import kotlin.jvm.*

@JvmInline
public value class ByteBitOperations(public val byte: Byte)

public inline val Byte.bits: ByteBitOperations
    get() = ByteBitOperations(this)

public data class NibblePair(
    @param:ByteLimit(from = 0, to = 0x0F) public val upperNibble: Byte,
    @param:ByteLimit(from = 0, to = 0x0F) public val lowerNibble: Byte
)