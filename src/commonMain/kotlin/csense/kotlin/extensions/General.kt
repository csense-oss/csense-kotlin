@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package csense.kotlin.extensions

import csense.kotlin.*
import kotlin.contracts.*

/**
 * Creates a simpler cast than regular due to the reified type T.
 * @receiver Any the value to cast
 * @return T? the potential cast, if it is unable, null will be returned
 */
public inline fun <reified T> Any.cast(): T? {
    contract {
        returnsNotNull() implies (this@cast is T)
    }
    return this as? T
}


public inline fun <reified T, U> Any.castMap(
    mapper: ReceiverFunction0<T, U>
): U? {
    contract {
        callsInPlace(mapper, kind = InvocationKind.AT_MOST_ONCE)
        returnsNotNull() implies (this@castMap is T)
    }
    return (this as? T)?.mapper()
}

//TODO is this a good strategy or should remove first edition and use "toUnit" ext fun ?
/**
 *
 * @receiver Any
 * @param action [Function1]<T, [Unit]>
 */
public inline fun <reified T> Any.invokeIsInstance(action: FunctionUnit<T>) {
    contract {
        callsInPlace(action, kind = InvocationKind.AT_MOST_ONCE)
    }
    invokeIsInstance<T, Unit>(action).toUnit()
}


/**
 * invokes the given action if this is the specific type [T] (and returns the result) or null if this is
 * not the specific type
 * @receiver [Any] the unknown type
 * @param action [Function1]<T, R> the action to call if this is actually a T
 * @return R? the result
 */

public inline fun <reified T, R> Any.invokeIsInstance(action: Function1<T, R>): R? {
    contract {
        callsInPlace(action, kind = InvocationKind.AT_MOST_ONCE)
        returnsNotNull() implies (this@invokeIsInstance is T)
    }
    return this.cast<T>()?.let(action)
}


/**
 * Converts any type into a "[Unit]"
 * @receiver [Any]?
 */
@Suppress("UnusedReceiverParameter")
public inline fun Any?.toUnit(): Unit = Unit


//region toUnitFunction
/**
 * Converts a function with a result to a function "without" a result.
 * @receiver [Function0R]<T, *>
 * @return [FunctionUnit]<T>
 */
public inline fun <T> Function0R<T>.toUnitFunction(): FunctionUnit<T> = { this() }

/**
 * Converts a function with a result to a function "without" a result.
 * @receiver [Function1]<T, *>
 * @return [FunctionUnit]<T>
 */
public inline fun <T> Function1<T, *>.toUnitFunction(): FunctionUnit<T> = { this(it) }
//TODO more of the toUnitFunction (for empty, for multiple args) ??
//endregion


/**
 * Uses this value iff not null or another if it is.
 * the first function (argument) will receive the value iff it is not null, the second is without any parameters
 *
 * @receiver T?
 * @param ifNotNull T.() -> Unit the action to perform iff this is not null
 * @param ifNull [EmptyFunction]  if the receiver is null this action will be performed
 */
public inline fun <T> T?.useOr(
    ifNotNull: ReceiverFunctionUnit<T>,
    ifNull: EmptyFunction
) {
    contract {
        callsInPlace(ifNotNull, InvocationKind.AT_MOST_ONCE)
        callsInPlace(ifNull, InvocationKind.AT_MOST_ONCE)
    }
    if (this != null) {
        ifNotNull(this)
    } else {
        ifNull()
    }
}

@Deprecated(
    "Using \"useOr\" on compile time known not null is always ifNotNull",
    level = DeprecationLevel.ERROR,
    replaceWith = ReplaceWith("ifNotNull")
)
@Suppress("UnusedReceiverParameter", "Unused", "MissingTestFunction","UNUSED_PARAMETER")
public inline fun <T> Any.useOr(
    ifNotNull: ReceiverFunctionUnit<T>,
    ifNull: EmptyFunction
): Nothing = unexpected()

/**
 * Another way of writing "!is" with is not "inverse" logic (not is), this "is not"
 * NB TYPE ERASURE STILL APPLIES SO LIST<STRING> IS == LIST<OBJECT> (because they become LIST<*>)
 * @receiver [Any]
 * @return [Boolean] true if this is not the given type, false if this is
 */

public inline fun <reified T> Any.isNot(): Boolean {
    contract {
        returns(false) implies (this@isNot is T)
        returns(true) implies (this@isNot !is T)
    }
    return (this !is T)
}

/**
 * this if it is not null, or the other if this is null
 * the same as ?:
 *
 * @receiver T? the optional value to either use or the supplied
 * @param ifNull T the other value to use if this receiver is null
 * @return T the non-null value
 */
public inline infix fun <@kotlin.internal.OnlyInputTypes reified T> T?.orIfNull(
    ifNull: T & Any
): T & Any = this ?: ifNull

@Deprecated(
    "Using \"orIfNull\" on compile time known not null is always this",
    level = DeprecationLevel.ERROR,
    replaceWith = ReplaceWith("this")
)
@Suppress("UnusedReceiverParameter", "Unused", "MissingTestFunction","UNUSED_PARAMETER")
public inline fun <T> Any.orIfNull(
    ifNull: T & Any
): Nothing = unexpected()


/**
 * this if it is not null, or the other if this is null
 * the same as ?:
 *
 * @receiver T? the optional value to either use or the supplied
 * @param ifNullAction [Function0R] the other value (to compute) if this receiver is null
 * @return T the non-null value
 */

public inline infix fun <reified T> T?.orIfNullLazy(ifNullAction: Function0R<T>): T {
    contract {
        callsInPlace(ifNullAction, InvocationKind.AT_MOST_ONCE)
    }
    return this ?: ifNullAction()
}


@Deprecated(
    "Using \"orIfNullLazy\" on compile time known not null is always this",
    level = DeprecationLevel.ERROR,
    replaceWith = ReplaceWith("this")
)
@Suppress("UnusedReceiverParameter", "Unused", "MissingTestFunction","UNUSED_PARAMETER")
public inline fun <T> Any.orIfNullLazy(
    ifNullAction: Function0R<T>
): Nothing = unexpected()


/**
 * applies the given function iff the given [shouldApply] is true
 * @receiver T
 * @param shouldApply [Boolean] whenever the given  [transform] should be applied to the receiver
 * @param transform [Function1]<T,T> the transformation function to execute if [shouldApply] is true
 * @return the original value if [shouldApply] is false, otherwise the transformed value (via [transform]) if [shouldApply] is true.
 */
public inline fun <T> T.applyIf(shouldApply: Boolean, transform: Function1<T, T>): T {
    return if (shouldApply) {
        transform(this)
    } else {
        this
    }
}


//region isAnyNull

public inline fun isAnyNull(
    first: Any?
): Boolean {
    contract {
        returns(true) implies (first == null)
        returns(false) implies (first != null)
    }
    return first == null
}


public inline fun isAnyNull(
    first: Any?,
    second: Any?
): Boolean {
    contract {
        returns(true) implies (first == null || second == null)
        returns(false) implies (first != null && second != null)
    }
    return first == null || second == null
}


public inline fun isAnyNull(
    first: Any?,
    second: Any?,
    third: Any?
): Boolean {
    contract {
        returns(true) implies (first == null || second == null || third == null)
        returns(false) implies (first != null && second != null && third != null)
    }
    return first == null || second == null || third == null
}


public inline fun isAnyNull(
    first: Any?,
    second: Any?,
    third: Any?,
    fourth: Any?
): Boolean {
    contract {
        returns(true) implies (first == null || second == null || third == null || fourth == null)
        returns(false) implies (first != null && second != null && third != null && fourth != null)
    }
    return first == null || second == null || third == null || fourth == null
}


public inline fun isAnyNull(
    first: Any?,
    second: Any?,
    third: Any?,
    fourth: Any?,
    fifth: Any?
): Boolean {
    contract {
        returns(true) implies (first == null || second == null || third == null || fourth == null || fifth == null)
        returns(false) implies (first != null && second != null && third != null && fourth != null && fifth != null)
    }
    return first == null || second == null || third == null || fourth == null || fifth == null
}


public inline fun isAnyNull(
    first: Any?,
    second: Any?,
    third: Any?,
    fourth: Any?,
    fifth: Any?,
    sixth: Any?
): Boolean {
    contract {
        returns(true) implies (first == null || second == null || third == null || fourth == null || fifth == null || sixth == null)
        returns(false) implies (first != null && second != null && third != null && fourth != null && fifth != null && sixth != null)
    }
    return first == null || second == null || third == null || fourth == null || fifth == null || sixth == null
}
//endregion

//region isAnyNotNull

public inline fun isAnyNotNull(
    first: Any?
): Boolean {
    contract {
        returns(false) implies (first == null)
        returns(true) implies (first != null)
    }
    return first != null
}


public inline fun isAnyNotNull(
    first: Any?,
    second: Any?
): Boolean {
    contract {
        returns(false) implies (first == null || second == null)
        returns(true) implies (first != null && second != null)
    }
    return first != null || second != null
}


public inline fun isAnyNotNull(
    first: Any?,
    second: Any?,
    third: Any?
): Boolean {
    contract {
        returns(false) implies (first == null || second == null || third == null)
        returns(true) implies (first != null || second != null || third != null)
    }
    return first != null || second != null || third != null
}


public inline fun isAnyNotNull(
    first: Any?,
    second: Any?,
    third: Any?,
    fourth: Any?
): Boolean {
    contract {
        returns(false) implies (first == null || second == null || third == null || fourth == null)
        returns(true) implies (first != null || second != null || third != null || fourth != null)
    }
    return first != null || second != null || third != null || fourth != null
}


public inline fun isAnyNotNull(
    first: Any?,
    second: Any?,
    third: Any?,
    fourth: Any?,
    fifth: Any?
): Boolean {
    contract {
        returns(false) implies (first == null || second == null || third == null || fourth == null || fifth == null)
        returns(true) implies (first != null || second != null || third != null || fourth != null || fifth != null)
    }
    return first != null || second != null || third != null || fourth != null || fifth != null
}


public inline fun isAnyNotNull(
    first: Any?,
    second: Any?,
    third: Any?,
    fourth: Any?,
    fifth: Any?,
    sixth: Any?
): Boolean {
    contract {
        returns(false) implies (first == null || second == null || third == null || fourth == null || fifth == null || sixth == null)
        returns(true) implies (first != null || second != null || third != null || fourth != null || fifth != null || sixth != null)
    }
    return first != null || second != null || third != null || fourth != null || fifth != null || sixth != null
}
//endregion
