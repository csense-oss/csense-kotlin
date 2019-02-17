@file:Suppress("NOTHING_TO_INLINE", "unused")

package csense.kotlin.extensions.primitives

inline infix fun Short.shl(shift: Int): Short = (this.toInt() shl shift).toShort()

inline infix fun Short.shr(shift: Int): Short = (this.toInt() shr shift).toShort()
