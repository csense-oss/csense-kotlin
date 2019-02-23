@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions

import csense.kotlin.*


/**
 * Creates a simpler cast than regular due to the reified type T.
 * @receiver Any the value to cast
 * @return T? the potential cast, if it is unable, null will be returned
 */
inline fun <reified T> Any.cast(): T? = this as? T

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
inline fun <T> T?.useOr(ifNotNull: ReceiverFunctionUnit<T>,
                        ifNull: EmptyFunction) {
    if (this != null) {
        ifNotNull(this)
    } else {
        ifNull()
    }
}