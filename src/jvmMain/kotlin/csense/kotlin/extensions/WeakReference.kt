@file:Suppress("NOTHING_TO_INLINE", "unused")

package csense.kotlin.extensions

import csense.kotlin.EmptyFunction
import csense.kotlin.Function1
import csense.kotlin.FunctionUnit
import csense.kotlin.ReceiverFunctionUnit
import java.lang.ref.WeakReference
import java.util.*


/**
 * Creates a weak reference of this object.
 * @receiver T the object to create a weak reference of
 * @return WeakReference<T> a weak reference to the given object
 */
inline fun <T> T.weakReference(): WeakReference<T> =
        WeakReference(this)

/**
 * Will use the value if the weak reference is not pointing to null
 * This differes from `use` where we cannot use it on an optional value inside of a WeakReference
 */
inline fun <T> WeakReference<T>.use(action: ReceiverFunctionUnit<T>): Unit =
        get()?.let(action).toUnit()

/**
 * Uses the given weak reference if available or does the other action
 * @receiver WeakReference<T>
 * @param ifAvailable ReceiverFunctionUnit<T> the action to perform iff the weak reference did contain something (not null)
 * @param ifNotAvailable EmptyFunction if the weakreference gave null,this action will be performed
 */
inline fun <T> WeakReference<T>.useOr(
        ifAvailable: ReceiverFunctionUnit<T>,
        ifNotAvailable: EmptyFunction
): Unit = get().useOr(ifAvailable, ifNotAvailable)


/**
 * invokes the function ( wrapped in a weakReference) with the input, if the WeakReference is not pointing to null
 * @receiver WeakReference<FunctionUnit<T>?> the weak reference
 * @param input T the type of wrapped object
 */
inline fun <T> WeakReference<FunctionUnit<T>?>.use(input: T): Unit =
        get()?.invoke(input).toUnit()

/**
 * invokes the function ( wrapped in a weakReference) with the input, if the WeakReference is not pointing to null
 * @receiver WeakReference<Function1<T,R>?> the weak reference
 * @param input T the type of wrapped object
 * @return R? the result of the function, or null if the weak reference is null (or the return type could be nullable).
 */
inline fun <T, R> WeakReference<Function1<T, R>?>.use(input: T): R? =
        get()?.invoke(input)


/**
 * Creates a weak reference, if this is not null. otherwise returns null.
 * @receiver T? the potential not null object
 * @return WeakReference<T>? the weak reference of the object, or null if this parameter was null
 */
inline fun <T> T?.weakReference(): WeakReference<T>? where T : Optional<*> =
        this?.let(::WeakReference)
