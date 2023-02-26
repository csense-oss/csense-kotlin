@file:Suppress("unused", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")


package csense.kotlin.extensions.collections.iterable

import csense.kotlin.*
import csense.kotlin.Function1
import csense.kotlin.extensions.collections.generic.*
import csense.kotlin.extensions.collections.generic.collection.*
import csense.kotlin.extensions.collections.generic.collection.operations.*
import csense.kotlin.extensions.general.*
import csense.kotlin.extensions.mapping.*


/**
 * Iterates over an optional collection and only running the function if the parameter is not null
 * @receiver [Iterable]<E?>
 * @param action [FunctionUnit]<E>
 */
public inline fun <E : Any> Iterable<E?>.forEachNotNull(action: FunctionUnit<E>) {
    forEach { it?.let(action) }
}

/**
 * Performs backwards traversal on this list.
 * @receiver [List]<T>
 * @param action [FunctionUnit]<T>
 * Might be suboptimal for collection that does not store the count
 */
public inline fun <T> Iterable<T>.forEachBackwards(action: FunctionUnit<T>): Unit =
    GenericCollections.forEachBackwards(count(), this::elementAt, action)

/**
 * invokes the given action on each item that is of the expected type (U)
 * @receiver [List]<*>
 * @param action [Function1]<U, *> action to invoke if the element is of type U
 */
public inline fun <reified U> Iterable<*>.forEachIsInstance(
    action: FunctionUnit<U>
): Unit = forEach {
    it?.invokeIsInstance(action)
}
