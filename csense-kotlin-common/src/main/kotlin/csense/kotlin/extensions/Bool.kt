package csense.kotlin.extensions

import csense.kotlin.AsyncEmptyFunction
import csense.kotlin.EmptyFunction
import csense.kotlin.EmptyFunctionResult

/**
 * Makes a more "elegant" sentence for some expressions, same as "com.commonsense.android.kotlin.com.commonsense.android.kotlin.base.onTrue"
 */
inline fun Boolean.ifTrue(crossinline action: EmptyFunction): Boolean = onTrue(action)

/**
 * Makes a more "elegant" sentence for some expressions, same as "com.commonsense.android.kotlin.com.commonsense.android.kotlin.base.onTrue"
 */
suspend inline fun Boolean.ifTrueAsync(crossinline action: AsyncEmptyFunction): Boolean =
        onTrueAsync(action)

/**
 * performs the action if the boolean is false.
 */
inline fun Boolean.onFalse(crossinline action: EmptyFunction): Boolean {
    if (!this) {
        action()
    }
    return this
}


/**
 * Makes a more "elegant" sentence for some expressions, same as "com.commonsense.android.kotlin.com.commonsense.android.kotlin.base.onTrue"
 */
inline fun Boolean.ifFalse(crossinline action: EmptyFunction): Boolean =
        onFalse(action)


/**
 * performs the action if the boolean is true.
 */
inline fun Boolean.onTrue(crossinline action: EmptyFunction): Boolean {
    if (this) {
        action()
    }
    return this
}

/**
 *  performs the action if the boolean is true.
 */
suspend inline fun Boolean.onTrueAsync(crossinline action: AsyncEmptyFunction): Boolean {
    if (this) {
        action()
    }
    return this
}


/**
 * Maps a boolean into a value.
 */
@Suppress("NOTHING_TO_INLINE")
inline fun <T> Boolean.map(ifTrue: T, ifFalse: T): T = if (this) {
    ifTrue
} else {
    ifFalse
}

/**
 * Maps lazyly the given parameters.
 * since its inline, then the code would be as if you wrote the "if else statement"
 * and then only did the computation iff that branch was chosene.
 *
 */
inline fun <T> Boolean.mapLazy(crossinline ifTrue: EmptyFunctionResult<T>,
                               crossinline ifFalse: EmptyFunctionResult<T>): T =
        if (this) {
            ifTrue()
        } else {
            ifFalse()
        }