@file:Suppress("NOTHING_TO_INLINE", "unused")

package csense.kotlin.extensions

import csense.kotlin.*
import java.lang.ref.*
import java.util.*


/**
 * Creates a weak reference of this object.
 * @receiver T the object to create a weak reference of
 * @return WeakReference<T> a weak reference to the given object
 */
inline fun <T> T.weakReference(): WeakReference<T> = WeakReference(this)

/**
 * Will use the value if the weak reference is not pointing to null
 * This differes from `use` where we cannot use it on an optional value inside of a WeakReference
 */
inline fun <T> WeakReference<T?>.useOpt(action: T.() -> Unit) {
    get()?.let(action)
}


/**
 * Performs the given action iff this weak reference is not null
 * @receiver WeakReference<T> the weak reference we want to unpack
 * @param action T.() -> Unit the action to run if the reference is not null
 */
inline fun <T> WeakReference<T>.use(action: T.() -> Unit) {
    get()?.let(action)
}

/**
 * Uses the given weak reaference if available or does the other action
 * @receiver WeakReference<T>
 * @param ifAvailable T.() -> Unit the action to perform iff the weak reference did contain something (not null)
 * @param ifNotAvailable EmptyFunction if the weakreference gave null,this action will be performed
 */
inline fun <T> WeakReference<T>.useRefOr(ifAvailable: T.() -> Unit,
                                         ifNotAvailable: EmptyFunction) {
    get().useOr(ifAvailable, ifNotAvailable)
}


/**
 * invokes the function ( wrapped in a weakReference) with the input, if the WeakReference is not pointing to null
 * @receiver WeakReference<FunctionUnit<T>?> the weak reference
 * @param input T the type of wrapped object
 */
inline fun <T> WeakReference<FunctionUnit<T>?>.use(input: T) {
    get()?.let { it(input) }
}


/**
 * Creates a weak reference, if this is not null. otherwise returns null.
 * @receiver T? the potential not null object
 * @return WeakReference<T>? the weak reference of the object, or null if this parameter was null
 */
inline fun <T> T?.weakReference(): WeakReference<T>? where T : Optional<*> =
        this?.let(::WeakReference)
