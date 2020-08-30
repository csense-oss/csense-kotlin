@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.primitives

import csense.kotlin.*
import kotlin.contracts.*


/**
 * performs the action if the boolean is false.
 * @receiver [Boolean]
 * @param action [EmptyFunction]
 * @return [Boolean]
 */
public inline fun Boolean.onFalse(action: EmptyFunction): Boolean {
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
public inline fun Boolean.onTrue(action: EmptyFunction): Boolean {
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
public inline fun Boolean.ifTrue(action: EmptyFunction): Boolean = onTrue(action)


/**
 * Makes a more "elegant" sentence for some expressions, same as "onFalse"
 * @receiver [Boolean]
 * @param action [EmptyFunction]
 * @return [Boolean]
 */
public inline fun Boolean.ifFalse(action: EmptyFunction): Boolean =
        onFalse(action)


@OptIn(ExperimentalContracts::class)
public inline fun Boolean?.isNullOrFalse(): Boolean {
    contract {
        returns(false) implies (this@isNullOrFalse != null)
    }
    return this == null || !this
}

@OptIn(ExperimentalContracts::class)
public inline fun Boolean?.isNullOrTrue(): Boolean {
    contract {
        returns(false) implies (this@isNullOrTrue != null)
    }
    return this == null || this
}

@OptIn(ExperimentalContracts::class)
public inline fun Boolean?.isNotNullOrTrue(): Boolean {
    contract {
        returns(true) implies (this@isNotNullOrTrue != null)
    }
    return this != null && !this
}

@OptIn(ExperimentalContracts::class)
public inline fun Boolean?.isNotNullOrFalse(): Boolean {
    contract {
        returns(true) implies (this@isNotNullOrFalse != null)
    }
    return this != null && this
}