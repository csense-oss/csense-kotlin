@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.extensions.general

import csense.kotlin.*
import kotlin.Function1


/**
 * Converts any type into a "[Unit]"
 * @receiver [Any]?
 */
@Suppress("UnusedReceiverParameter")
public inline fun Any?.toUnit(): Unit = Unit


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
