package csense.kotlin.extensions.primitives

import csense.kotlin.AsyncEmptyFunction
import csense.kotlin.EmptyFunction


/**
 * performs the action if the boolean is false.
 * @receiver Boolean
 * @param action EmptyFunction
 * @return Boolean
 */
inline fun Boolean.onFalse(crossinline action: EmptyFunction): Boolean {
    if (!this) {
        action()
    }
    return this
}

/**
 * performs the action if the boolean is true.
 * @receiver Boolean
 * @param action EmptyFunction
 * @return Boolean
 */
inline fun Boolean.onTrue(crossinline action: EmptyFunction): Boolean {
    if (this) {
        action()
    }
    return this
}


/**
 *  performs the action if the boolean is true.
 * @receiver Boolean
 * @param action AsyncEmptyFunction
 * @return Boolean
 */
suspend inline fun Boolean.onTrueAsync(crossinline action: AsyncEmptyFunction): Boolean {
    if (this) {
        action()
    }
    return this
}


/**
 * Makes a more "elegant" sentence for some expressions, same as "com.commonsense.android.kotlin.com.commonsense.android.kotlin.base.onTrue"
 * @receiver Boolean
 * @param action EmptyFunction
 * @return Boolean
 */
inline fun Boolean.ifTrue(crossinline action: EmptyFunction): Boolean = onTrue(action)

/**
 * Makes a more "elegant" sentence for some expressions, same as "com.commonsense.android.kotlin.com.commonsense.android.kotlin.base.onTrue"
 * @receiver Boolean
 * @param action AsyncEmptyFunction
 * @return Boolean
 */
suspend inline fun Boolean.ifTrueAsync(crossinline action: AsyncEmptyFunction): Boolean =
        onTrueAsync(action)


/**
 * Makes a more "elegant" sentence for some expressions, same as "com.commonsense.android.kotlin.com.commonsense.android.kotlin.base.onTrue"
 * @receiver Boolean
 * @param action EmptyFunction
 * @return Boolean
 */
inline fun Boolean.ifFalse(crossinline action: EmptyFunction): Boolean =
        onFalse(action)

