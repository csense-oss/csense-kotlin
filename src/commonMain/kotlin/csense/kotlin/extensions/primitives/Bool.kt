@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.primitives

import csense.kotlin.EmptyFunction


/**
 * performs the action if the boolean is false.
 * @receiver [Boolean]
 * @param action [EmptyFunction]
 * @return [Boolean]
 */
inline fun Boolean.onFalse(action: EmptyFunction): Boolean {
    if (!this) {
        action()
    }
    return this
}

/**
 * performs the action if the boolean is true.
 * @receiver [Boolean]
 * @param action [EmptyFunction]
 * @return [Boolean]
 */
inline fun Boolean.onTrue(action: EmptyFunction): Boolean {
    if (this) {
        action()
    }
    return this
}


/**
 * Makes a more "elegant" sentence for some expressions same as "OnTrue"
 * @receiver [Boolean]
 * @param action [EmptyFunction]
 * @return [Boolean]
 */
inline fun Boolean.ifTrue(action: EmptyFunction): Boolean = onTrue(action)


/**
 * Makes a more "elegant" sentence for some expressions, same as "onFalse"
 * @receiver [Boolean]
 * @param action [EmptyFunction]
 * @return [Boolean]
 */
inline fun Boolean.ifFalse(action: EmptyFunction): Boolean =
        onFalse(action)

