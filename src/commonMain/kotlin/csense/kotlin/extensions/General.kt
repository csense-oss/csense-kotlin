@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions

import csense.kotlin.EmptyFunction
import csense.kotlin.Function1
import csense.kotlin.FunctionUnit
import csense.kotlin.ReceiverFunctionUnit
import kotlin.contracts.*


/**
 * Creates a simpler cast than regular due to the reified type T.
 * @receiver Any the value to cast
 * @return T? the potential cast, if it is unable, null will be returned
 */
inline fun <reified T> Any.cast(): T? = this as? T

//TODO is this a good strategy or should remove first edition and use "toUnit" ext fun ?
/**
 *
 * @receiver Any
 * @param action Function1<T, Unit>
 * @return Unit
 */
inline fun <reified T> Any?.invokeIsInstance(action: FunctionUnit<T>): Unit =
        invokeIsInstance<T, Unit>(action).toUnit()


/**
 * invokes the given action if this is the specific type (and returns the result) or null if this is
 * not the specific type
 * @receiver Any the unknown type
 * @param action Function1<T, R> the action to call if this is actually a T
 * @return R? the return result
 */
inline fun <reified T, R> Any?.invokeIsInstance(action: Function1<T, R>): R? = when (this) {
    is T -> action(this)
    else -> null
}

/**
 * Converts any type into a "unit"
 * @receiver Any?
 */
@Suppress("RedundantUnitReturnType")
inline fun Any?.toUnit(): Unit = Unit


/**
 * Converts a function with a result to a function "without" a result.
 * @receiver Function1<T, *>
 * @return FunctionUnit<T>
 */
inline fun <T> Function1<T, *>.toUnitFunction(): FunctionUnit<T> = { this(it) }

//TODO more of the toUnitFunction (for empty, for multiple args)

/**
 * Uses this value iff not null or another if it is.
 * the first function (argument) will receive the value iff it is not null, the second is without any parameters
 *
 * @receiver T?
 * @param ifNotNull T.() -> Unit the action to perform iff this is not null
 * @param ifNull EmptyFunction  if the this is null this action will be performed
 */
inline fun <T> T?.useOr(
        ifNotNull: ReceiverFunctionUnit<T>,
        ifNull: EmptyFunction
) {
    if (this != null) {
        ifNotNull(this)
    } else {
        ifNull()
    }
}

/**
 * Another way of writing "!is" with is not "inverse" logic (not is), this "is not"
 * NB TYPE ERASURE STILL APPLIES SO LIST<STRING> IS == LIST<OBJECT> (because they become LIST<*>)
 * @receiver Any
 * @return Boolean true if this is not the given type, false if this is
 */
@OptIn(ExperimentalContracts::class)
inline fun <reified U> Any.isNot(): Boolean {
//    contract {
//        returns(false) implies (this is U)
//    }
    return (this !is U)
}
