@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package csense.kotlin.extensions.mapping

import csense.kotlin.*
import kotlin.contracts.*

/**
 * Maps a boolean into a value .
 * @receiver [Boolean]
 * @param ifTrue [T] if the [Boolean] is true this will be returned
 * @param ifFalse [T] if the [Boolean] is false this will be returned
 * @return [T]
 */

public inline fun <T> Boolean.map(
    ifTrue: T,
    ifFalse: T
): T = when {
    this -> ifTrue
    else -> ifFalse
}


/**
 * Maps lazy the given parameters.
 * since its inline, then the code would be as if you wrote the "if else statement"
 * and then only did the computation iff that branch was chosen.
 *
 * @receiver [Boolean]
 * @param ifTrue [EmptyFunctionResult]<T>
 * @param ifFalse [EmptyFunctionResult]<T>
 * @return T
 */

public inline fun <T> Boolean.mapLazy(
    ifTrue: EmptyFunctionResult<T>,
    ifFalse: EmptyFunctionResult<T>
): T = if (this) {
    contract {
        callsInPlace(ifTrue, InvocationKind.AT_MOST_ONCE)
        callsInPlace(ifFalse, InvocationKind.AT_MOST_ONCE)
    }
    ifTrue()
} else {
    ifFalse()
}

//TODO this seems off.
/**
 * Maps the receiver into a set.
 * @receiver [Iterable]<T>
 * @param mapper [Function1]<T, U>
 * @return [Set]<U>
 */
public inline fun <T, U> Iterable<T>.mapToSet(
    mapper: Function1<T, U>
): Set<U> = mapTo(mutableSetOf(), mapper)



public inline fun <T, reified U> Iterable<T>.mapToTypedArray(mapper: Function1<T, U>): Array<U> {
    if (this is List<T>) {
        return mapToTypedArray(mapper)
    }
    val size = count()
    val iterator = iterator()
    return Array(size) {
        mapper(iterator.next())
    }
}

public inline fun <T, reified U> List<T>.mapToTypedArray(
    mapper: Function1<T, U>
): Array<U> = Array(size) { index ->
    mapper(this[index])
}
