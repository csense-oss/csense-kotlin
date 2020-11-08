@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions

import csense.kotlin.*
import kotlin.contracts.*

/**
 * Maps an optional value into another value
 * @receiver [Any]?
 * @param ifNotNull U the value if 'this' is not null
 * @param ifNull U the value if 'this' is null
 * @return U the value depending on 'this' value
 */
public inline fun <U> Any?.mapOptional(
    ifNotNull: U,
    ifNull: U
): U {

    return this.isNotNull.map(ifNotNull, ifNull)
}

/**
 * Maps an optional value into another value
 * @receiver [Any]?
 * @param ifNotNull [Function1]<[U>] the value if 'this' is not null (with the non null value)
 * @param ifNull [EmptyFunctionResult]<[U]> the value if 'this' is null
 * @return [U] the value depending on 'this' value
 */
@OptIn(ExperimentalContracts::class)
public inline fun <U, T> T?.mapLazyOptional(
    ifNotNull: Function1<T, U>,
    ifNull: EmptyFunctionResult<U>
): U {
    contract {
        callsInPlace(ifNotNull, InvocationKind.AT_MOST_ONCE)
        callsInPlace(ifNull, InvocationKind.AT_MOST_ONCE)
    }
    return if (this != null) {
        ifNotNull(this)
    } else {
        ifNull()
    }
}

/**
 * Maps a boolean into a value.
 * @receiver [Boolean]
 * @param ifTrue T
 * @param ifFalse T
 * @return T
 */

public inline fun <T> Boolean.map(
    ifTrue: T,
    ifFalse: T
): T = if (this) {
    ifTrue
} else {
    ifFalse
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
@OptIn(ExperimentalContracts::class)
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


@OptIn(ExperimentalContracts::class)
public inline fun <reified T, reified TT : T> T.mapIfInstanceOrThis(action: Function1<TT, T>): T {
    contract {
        callsInPlace(action, kind = InvocationKind.AT_MOST_ONCE)
        returnsNotNull() implies (this@mapIfInstanceOrThis is TT)
    }
    return when (this) {
        is TT -> action(this)
        else -> this
    }
}

public inline fun <T, reified U> Iterable<T>.mapToTypedArray(mapper: Function1<T, U>): Array<U> {
    val size = count()
    val iterator = iterator()//todo improve test perf (might want to specialize impls)
    return Array(size) {
        mapper(iterator.next())
    }
}
