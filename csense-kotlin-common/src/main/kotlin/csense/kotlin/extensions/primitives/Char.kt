package csense.kotlin.extensions.primitives

import csense.kotlin.extensions.mapLazy


@Suppress("NOTHING_TO_INLINE")
inline fun Char.toCase(upperCase: Boolean): Char =
        upperCase.mapLazy(this::toUpperCase,
                this::toLowerCase)

