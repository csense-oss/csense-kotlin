package csense.kotlin.extensions.primitives.string

import kotlin.contracts.*


/**
 * Opposite of "[ifEmpty]"
 * if this [String] is not empty , executes the method and returns that
 * if it is empty, then it returns "this".
 * @receiver C
 * @param ifNotEmpty [Function1]<C, C>
 * @return C
 */
public inline fun String.ifNotEmpty(ifNotEmpty: Function1<String, String>): String {
    contract {
        callsInPlace(ifNotEmpty, InvocationKind.AT_MOST_ONCE)
    }
    return if (isEmpty()) {
        this
    } else {
        ifNotEmpty(this)
    }
}

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
    return if (isBlank()) {
        this
    } else {
        ifNotBlank(this)
    }
}