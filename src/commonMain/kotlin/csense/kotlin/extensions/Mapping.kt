@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions

import csense.kotlin.EmptyFunctionResult
import csense.kotlin.Function1

/**
 * Maps an optional value into another value
 * @receiver Any?
 * @param ifNotNull U the value if 'this' is not null
 * @param ifNull U the value if 'this' is null
 * @return U the value depending on 'this' value
 */
inline fun <U> Any?.mapOptional(
        ifNotNull: U,
        ifNull: U): U {
    return this.isNotNull.map(ifNotNull, ifNull)
}

/**
 * Maps an optional value into another value
 * @receiver Any?
 * @param ifNotNull EmptyFunctionResult<U> the value if 'this' is not null
 * @param ifNull EmptyFunctionResult<U> the value if 'this' is null
 * @return U the value depending on 'this' value
 */
inline fun <U> Any?.mapLazyOptional(
        ifNotNull: EmptyFunctionResult<U>,
        ifNull: EmptyFunctionResult<U>): U {
    return if (this.isNotNull) {
        ifNotNull()
    } else {
        ifNull()
    }
}

/**
 * Maps a boolean into a value.
 * @receiver Boolean
 * @param ifTrue T
 * @param ifFalse T
 * @return T
 */

inline fun <T> Boolean.map(
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
 * @receiver Boolean
 * @param ifTrue EmptyFunctionResult<T>
 * @param ifFalse EmptyFunctionResult<T>
 * @return T
 */
inline fun <T> Boolean.mapLazy(
        ifTrue: EmptyFunctionResult<T>,
        ifFalse: EmptyFunctionResult<T>
): T = if (this) {
    ifTrue()
} else {
    ifFalse()
}


/**
 * Maps the receiver into a set.
 * @receiver Iterable<T>
 * @param mapper Function1<T, U>
 * @return Set<U>
 */
fun <T, U> Iterable<T>.mapToSet(
        mapper: Function1<T, U>
): Set<U> = mapTo(mutableSetOf(), mapper)

