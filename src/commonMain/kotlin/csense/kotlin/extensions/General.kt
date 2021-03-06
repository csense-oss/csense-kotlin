@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions

import csense.kotlin.*
import kotlin.contracts.*


/**
 * Creates a simpler cast than regular due to the reified type T.
 * @receiver Any the value to cast
 * @return T? the potential cast, if it is unable, null will be returned
 */
@OptIn(ExperimentalContracts::class)
public inline fun <reified T> Any.cast(): T? {
    contract {
        returnsNotNull() implies (this@cast is T)
    }
    return this as? T
}


@OptIn(ExperimentalContracts::class)
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
@OptIn(ExperimentalContracts::class)
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
 * @return R? the return result
 */
@OptIn(ExperimentalContracts::class)
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
 * @param ifNull [EmptyFunction]  if the this is null this action will be performed
 */
@OptIn(ExperimentalContracts::class)
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

/**
 * Another way of writing "!is" with is not "inverse" logic (not is), this "is not"
 * NB TYPE ERASURE STILL APPLIES SO LIST<STRING> IS == LIST<OBJECT> (because they become LIST<*>)
 * @receiver [Any]
 * @return [Boolean] true if this is not the given type, false if this is
 */
@OptIn(ExperimentalContracts::class)
public inline fun <reified T> Any.isNot(): Boolean {
    contract {
        returns(false) implies (this@isNot is T)
    }
    return (this !is T)
}

/**
 * this if it is not null, or the other if this is null
 * the same as ?:
 *
 * @receiver T? the optional value to either use or the supplied
 * @param ifNull T the other value to use if this receiver is null
 * @return T the non null value
 */
public inline infix fun <reified T> T?.or(ifNull: T): T = this ?: ifNull

/**
 * this if it is not null, or the other if this is null
 * the same as ?:
 *
 * @receiver T? the optional value to either use or the supplied
 * @param ifNullAction [Function0R] the other value (to compute) if this receiver is null
 * @return T the non null value
 */
@OptIn(ExperimentalContracts::class)
public inline infix fun <reified T> T?.orLazy(ifNullAction: Function0R<T>): T {
    contract {
        callsInPlace(ifNullAction, InvocationKind.AT_MOST_ONCE)
    }
    return this ?: ifNullAction()
}