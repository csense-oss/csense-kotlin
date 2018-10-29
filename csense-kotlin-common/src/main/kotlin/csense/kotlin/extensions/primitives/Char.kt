package csense.kotlin.extensions.primitives

import csense.kotlin.extensions.*

/**
 *changes the casing of this char to the given casing
 * @receiver Char
 * @param upperCase Boolean
 * @return Char
 */
@Suppress("NOTHING_TO_INLINE")
inline fun Char.toCase(upperCase: Boolean): Char = upperCase.mapLazy(
        ifTrue = this::toUpperCase,
        ifFalse = this::toLowerCase)

