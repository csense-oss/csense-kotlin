package org.csenseoss.kotlin.extensions.primitives.string

import kotlin.contracts.*


/**
 * Opposite of "[ifBlank]"
 * if this string is not blank, executes the method and returns that
 * if it is blank, then it returns this.
 * @receiver C
 * @param ifNotBlank [Function1]<C, C>
 * @return C
 */
public inline fun String.ifNotBlank(ifNotBlank: Function1<String, String>): String {
    contract {
        callsInPlace(ifNotBlank, InvocationKind.AT_MOST_ONCE)
    }
    if (isBlank()) {
        return this
    }
    return ifNotBlank(this)
}