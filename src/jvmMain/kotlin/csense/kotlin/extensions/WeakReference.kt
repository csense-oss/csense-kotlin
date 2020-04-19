@file:Suppress("NOTHING_TO_INLINE", "unused")

package csense.kotlin.extensions

import csense.kotlin.*
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
 * Uses this weak reference if valid and calls the method if there
 * @receiver WeakReference<EmptyFunction>
 * @return Unit
 */
inline fun WeakReference<EmptyFunction>.useInvoke(): Unit =
        get()?.let { it() }.toUnit()

/**
 * Uses this weak reference if valid and calls the function and returns the result.
 * returns null if the weak reference is null (or if the function returns null)
 * @receiver WeakReference<Function0R<R>>
 * @return R?
 */
inline fun <R> WeakReference<Function0R<R>>.useInvoke(): R? =
        get()?.let { it() }

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
//TODO useInvoke
@Deprecated("This will be renamed in the following version")
inline fun <T> WeakReference<FunctionUnit<T>?>.use(input: T): Unit =
        get()?.invoke(input).toUnit()

/**
 * invokes the function ( wrapped in a weakReference) with the input, if the WeakReference is not pointing to null
 * @receiver WeakReference<Function1<T,R>?> the weak reference
 * @param input T the type of wrapped object
 * @return R? the result of the function, or null if the weak reference is null (or the return type could be nullable).
 */
//TODO useInvoke
@Deprecated("This will be renamed in the following version")
inline fun <T, R> WeakReference<Function1<T, R>?>.use(input: T): R? =
        get()?.invoke(input)


/**
 * Creates a weak reference, if this is not null. otherwise returns null.
 * @receiver T? the potential not null object
 * @return WeakReference<T>? the weak reference of the object, or null if this parameter was null
 */
inline fun <T> T?.weakReference(): WeakReference<T>? where T : Optional<*> =
        this?.let(::WeakReference)
