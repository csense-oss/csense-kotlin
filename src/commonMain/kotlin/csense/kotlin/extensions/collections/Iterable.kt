@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.collections

import csense.kotlin.*
import csense.kotlin.extensions.*
import csense.kotlin.extensions.collections.generic.*
import kotlin.contracts.*

//region Invoke each Lazy
/**
 * Invokes each function with the given argument(s) computed lazy
 * @receiver [Iterable]<[Function1]<I1, O>>
 * @param element [Function0R]<I1>
 */
@OptIn(ExperimentalContracts::class)
public inline fun <I1, O> Iterable<Function1<I1, O>>.invokeEachWithLazy(
    element: Function0R<I1>
) {
    contract {
        callsInPlace(element, InvocationKind.AT_MOST_ONCE)
    }
    skipIfEmptyOr {
        invokeEachWith(element())
    }
}

/**
 * Invokes each function with the given arguments
 * @receiver [Iterable]<[Function2]<I1, I2, O>>
 * @param firstElement I1
 * @param secondElement I2
 */

@OptIn(ExperimentalContracts::class)
public inline fun <I1, I2, O> Iterable<Function2<I1, I2, O>>.invokeEachWithLazy(
    firstElement: Function0R<I1>,
    secondElement: Function0R<I2>
) {
    contract {
        callsInPlace(firstElement, InvocationKind.AT_MOST_ONCE)
        callsInPlace(secondElement, InvocationKind.AT_MOST_ONCE)
    }
    skipIfEmptyOr {
        invokeEachWith(firstElement(), secondElement())
    }
}


/**
 * Invokes each function with the given arguments
 * @receiver [Iterable]<[Function3]<I1, I2, I3, O>>
 * @param firstElement I1
 * @param secondElement I2
 * @param thirdElement I3
 */
@OptIn(ExperimentalContracts::class)
public inline fun <I1, I2, I3, O>
        Iterable<Function3<I1, I2, I3, O>>.invokeEachWithLazy(
    firstElement: Function0R<I1>,
    secondElement: Function0R<I2>,
    thirdElement: Function0R<I3>
) {
    contract {
        callsInPlace(firstElement, InvocationKind.AT_MOST_ONCE)
        callsInPlace(secondElement, InvocationKind.AT_MOST_ONCE)
        callsInPlace(thirdElement, InvocationKind.AT_MOST_ONCE)
    }
    skipIfEmptyOr {
        invokeEachWith(firstElement(), secondElement(), thirdElement())
    }
}

/**
 *  Invokes each function with the given arguments
 * @receiver [Iterable]<[Function4]<I1, I2, I3, I4, O>>
 * @param firstElement I1
 * @param secondElement I2
 * @param thirdElement I3
 * @param fourthElement I4
 */
@OptIn(ExperimentalContracts::class)
public inline fun <I1, I2, I3, I4, O>
        Iterable<Function4<I1, I2, I3, I4, O>>.invokeEachWithLazy(
    firstElement: Function0R<I1>,
    secondElement: Function0R<I2>,
    thirdElement: Function0R<I3>,
    fourthElement: Function0R<I4>
) {
    contract {
        callsInPlace(firstElement, InvocationKind.AT_MOST_ONCE)
        callsInPlace(secondElement, InvocationKind.AT_MOST_ONCE)
        callsInPlace(thirdElement, InvocationKind.AT_MOST_ONCE)
        callsInPlace(fourthElement, InvocationKind.AT_MOST_ONCE)
    }
    skipIfEmptyOr {
        invokeEachWith(
            firstElement(),
            secondElement(),
            thirdElement(),
            fourthElement()
        )
    }
}

/**
 * Invokes each function with the given arguments
 * @receiver [Iterable]<[Function5]<I1, I2, I3, I4, I5, O>>
 * @param firstElement I1
 * @param secondElement I2
 * @param thirdElement I3
 * @param fourthElement I4
 * @param fifthElement I5
 */
@OptIn(ExperimentalContracts::class)
public inline fun <I1, I2, I3, I4, I5, O>
        Iterable<Function5<I1, I2, I3, I4, I5, O>>.invokeEachWithLazy(
    firstElement: Function0R<I1>,
    secondElement: Function0R<I2>,
    thirdElement: Function0R<I3>,
    fourthElement: Function0R<I4>,
    fifthElement: Function0R<I5>
) {
    contract {
        callsInPlace(firstElement, InvocationKind.AT_MOST_ONCE)
        callsInPlace(secondElement, InvocationKind.AT_MOST_ONCE)
        callsInPlace(thirdElement, InvocationKind.AT_MOST_ONCE)
        callsInPlace(fourthElement, InvocationKind.AT_MOST_ONCE)
        callsInPlace(fifthElement, InvocationKind.AT_MOST_ONCE)
    }
    skipIfEmptyOr {
        invokeEachWith(
            firstElement(),
            secondElement(),
            thirdElement(),
            fourthElement(),
            fifthElement()
        )
    }
}

/**
 * Invokes each function with the given arguments
 * @receiver [Iterable]<[Function6]<I1, I2, I3, I4, I5, I6, O>>
 * @param firstElement I1
 * @param secondElement I2
 * @param thirdElement I3
 * @param fourthElement I4
 * @param fifthElement I5
 * @param sixthElement I6
 */
@OptIn(ExperimentalContracts::class)
public inline fun <I1, I2, I3, I4, I5, I6, O>
        Iterable<Function6<I1, I2, I3, I4, I5, I6, O>>.invokeEachWithLazy(
    firstElement: Function0R<I1>,
    secondElement: Function0R<I2>,
    thirdElement: Function0R<I3>,
    fourthElement: Function0R<I4>,
    fifthElement: Function0R<I5>,
    sixthElement: Function0R<I6>
) {
    contract {
        callsInPlace(firstElement, InvocationKind.AT_MOST_ONCE)
        callsInPlace(secondElement, InvocationKind.AT_MOST_ONCE)
        callsInPlace(thirdElement, InvocationKind.AT_MOST_ONCE)
        callsInPlace(fourthElement, InvocationKind.AT_MOST_ONCE)
        callsInPlace(fifthElement, InvocationKind.AT_MOST_ONCE)
        callsInPlace(sixthElement, InvocationKind.AT_MOST_ONCE)
    }
    skipIfEmptyOr {
        invokeEachWith(
            firstElement(),
            secondElement(),
            thirdElement(),
            fourthElement(),
            fifthElement(),
            sixthElement()
        )
    }
}

//endregion

//region Invoke each with

/**
 * Invokes each callable argument and discards the result
 * @receiver [Iterable]<[Function0R]<O>>
 */
public inline fun <O> Iterable<Function0R<O>>.invokeEach(): Unit =
    forEach { it() }

/**
 * Invokes each function with the given arguments
 * @receiver [Iterable]<[Function1]<I1, O>>
 * @param element I1
 */

public inline fun <I1, O> Iterable<Function1<I1, O>>.invokeEachWith(
    element: I1
): Unit =
    forEach { it(element) }

/**
 * Invokes each function with the given arguments
 * @receiver [Iterable]<[Function2]<I1, I2, O>>
 * @param firstElement I1
 * @param secondElement I2
 */


public inline fun <I1, I2, O> Iterable<Function2<I1, I2, O>>.invokeEachWith(
    firstElement: I1,
    secondElement: I2
): Unit =
    forEach { it(firstElement, secondElement) }

/**
 * Invokes each function with the given arguments
 * @receiver [Iterable]<[Function3]<I1, I2, I3, O>>
 * @param firstElement I1
 * @param secondElement I2
 * @param thirdElement I3
 */

public inline fun <I1, I2, I3, O>
        Iterable<Function3<I1, I2, I3, O>>.invokeEachWith(
    firstElement: I1,
    secondElement: I2,
    thirdElement: I3
): Unit =
    forEach {
        it(
            firstElement,
            secondElement,
            thirdElement
        )
    }

/**
 *  Invokes each function with the given arguments
 * @receiver [Iterable]<[Function4]<I1, I2, I3, I4, O>>
 * @param firstElement I1
 * @param secondElement I2
 * @param thirdElement I3
 * @param fourthElement I4
 */

public inline fun <I1, I2, I3, I4, O>
        Iterable<Function4<I1, I2, I3, I4, O>>.invokeEachWith(
    firstElement: I1,
    secondElement: I2,
    thirdElement: I3,
    fourthElement: I4
): Unit =
    forEach {
        it(
            firstElement,
            secondElement,
            thirdElement,
            fourthElement
        )
    }

/**
 * Invokes each function with the given arguments
 * @receiver [Iterable]<[Function5]<I1, I2, I3, I4, I5, O>>
 * @param firstElement I1
 * @param secondElement I2
 * @param thirdElement I3
 * @param fourthElement I4
 * @param fifthElement I5
 */

public inline fun <I1, I2, I3, I4, I5, O>
        Iterable<Function5<I1, I2, I3, I4, I5, O>>.invokeEachWith(
    firstElement: I1,
    secondElement: I2,
    thirdElement: I3,
    fourthElement: I4,
    fifthElement: I5
): Unit =
    forEach {
        it(
            firstElement,
            secondElement,
            thirdElement,
            fourthElement,
            fifthElement
        )
    }

/**
 * Invokes each function with the given arguments
 * @receiver [Iterable]<[Function6]<I1, I2, I3, I4, I5, I6, O>>
 * @param firstElement I1
 * @param secondElement I2
 * @param thirdElement I3
 * @param fourthElement I4
 * @param fifthElement I5
 * @param sixthElement I6
 */

public inline fun <I1, I2, I3, I4, I5, I6, O>
        Iterable<Function6<I1, I2, I3, I4, I5, I6, O>>.invokeEachWith(
    firstElement: I1,
    secondElement: I2,
    thirdElement: I3,
    fourthElement: I4,
    fifthElement: I5,
    sixthElement: I6
): Unit =
    forEach {
        it(
            firstElement,
            secondElement,
            thirdElement,
            fourthElement,
            fifthElement,
            sixthElement
        )
    }
//endregion


/**
 * Iterates over an optional collection and only running the function if the parameter is not null
 * @receiver [Iterable]<E?>
 * @param action [FunctionUnit]<E>
 */

public inline fun <E : Any> Iterable<E?>.forEachNotNull(action: FunctionUnit<E>) {
    forEach { it?.let(action) }
}


/**
 * Performs a partition of the given iterable. (also known as splitting) by the given predicate function
 * A safer alternative to the standard library partition(that only yields a pair, which does not enforce a clear understanding of what the first or second is.
 * @receiver [Iterable]<E> the collection to partition
 * @param predicate [Function1]<E, Boolean> whenever the given element should be in the "true" list or false
 * @return [CollectionPartition]<E> the result by partition / splitting the content by the given function
 */
public inline fun <E> Iterable<E>.partitionSafe(predicate: Function1<E, Boolean>): CollectionPartition<E> {
    return this.partition(predicate).let {
        CollectionPartition(it.first, it.second)
    }
}

/**
 * The result from a safe partition / split operation
 * @param E The data / element type
 * @property trueForPredicate [List]<E> the elements considered "true" / included
 * @property falseForPredicate [List]<E> the elements considered "false" / not included
 */
public class CollectionPartition<out E>(
    public val trueForPredicate: List<E>,
    public val falseForPredicate: List<E>
)


//region Generic collection extensions
/**
 * Performs traversal in pairs of 2  (with the first index as well)
 * @receiver [List]<T>
 * @param action [Function2IndexedUnit]<T, T>
 */
@Deprecated("will be removed")
public inline fun <T> Iterable<T>.forEach2Indexed(action: Function2IndexedUnit<T, T>): Unit =
    GenericCollectionExtensions.forEach2Indexed(count(), ::elementAt, action)

/**
 * Performs traversal in pairs of 2
 * @receiver [List]<T>
 * @param action [Function2Unit]<T, T>
 */
@Deprecated("will be removed")
public inline fun <T> Iterable<T>.foreach2(action: Function2Unit<T, T>): Unit =
    GenericCollectionExtensions.forEach2(count(), ::elementAt, action)

/**
 * Performs backwards traversal on this list.
 * @receiver [List]<T>
 * @param action [FunctionUnit]<T>
 * Might be suboptimal for collection that does not store the count
 */
public inline fun <T> Iterable<T>.forEachBackwards(action: FunctionUnit<T>): Unit =
    GenericCollectionExtensions.forEachBackwards(count(), this::elementAt, action)
//endregion

/**
 *
 * @receiver [Iterable]<T>
 * @param function [Function0]<[Unit]>
 */
@OptIn(ExperimentalContracts::class)
public inline fun <T> Iterable<T>.skipIfEmptyOr(function: () -> Unit) {
    contract {
        callsInPlace(function, InvocationKind.AT_MOST_ONCE)
    }
    if (isNotEmpty()) {
        function()
    }
}

/**
 * Tells if this iterable is empty
 * @receiver [Iterable]<T>
 * @return [Boolean] false if it has "next" true otherwise
 */
public inline fun <T> Iterable<T>.isEmpty(): Boolean = !isNotEmpty()

/**
 * Tells if this iterable is not empty
 * @receiver [Iterable]<T>
 * @return [Boolean] true if it has "next" false otherwise
 */
public inline fun <T> Iterable<T>.isNotEmpty(): Boolean = any()


/**
 * invokes the given action on each item that is of the expected type (U)
 * @receiver [List]<*>
 * @param action [Function1]<U, *> action to invoke if the element is of type U
 */
public inline fun <reified U> Iterable<*>.forEachIsInstance(
    action: kotlin.Function1<U, *>
): Unit = forEach {
    it?.invokeIsInstance(action)
}

/**
 * Finds the largest element (and returns it if found)
 * @receiver [Iterable]<E> the iterable to go though
 * @param selector [Function1]<E, V> given an element (E) selects a Value (V) to be used for comparisons
 * @return E? the largest element or null if non were found
 * @timecomplexity O(n)
 */
public inline fun <E, V : Comparable<V>> Iterable<E>.largest(
    selector: Function1<E, V>
): E? {
    var currentResult: E? = null
    var currentValue: V? = null
    forEach {
        val value = selector(it)
        val safeCurrentValue = currentValue
        if (safeCurrentValue == null || value > safeCurrentValue) {
            currentResult = it
            currentValue = value
        }
    }
    return currentResult
}

/**
 * If this [C] is [isEmpty] or null then it will return null, otherwise the receiver (which is [isNotEmpty])
 * @receiver [C]?
 * @return [C]?
 */
@OptIn(ExperimentalContracts::class)
public inline fun <T, C : Iterable<T>> C?.nullOnEmpty(): C? {
    contract {
        returnsNotNull() implies (this@nullOnEmpty != null)
    }
    return isNotNullOrEmpty().map(ifTrue = this, ifFalse = null)
}

/**
 * Tells if this iterable has content (meaning it is not null nor is it empty)
 * @receiver [C]? the optional type
 * @return [Boolean] true if this has content (is not null or empty) false if it is null or empty
 */
@OptIn(ExperimentalContracts::class)
public inline fun <T, C : Iterable<T>> C?.isNotNullOrEmpty(): Boolean {
    contract {
        returns(true) implies (this@isNotNullOrEmpty != null)
    }
    return this != null && this.isNotEmpty()
}

/**
 * Tells if this iteration is null or empty (size = 0)
 * @receiver [Iterable]<T>? the nullable iteration
 * @return [Boolean] true if the iteration is null or empty
 */
@OptIn(ExperimentalContracts::class)
public inline fun <T, C : Iterable<T>> C?.isNullOrEmpty(): Boolean {
    contract {
        returns(false) implies (this@isNullOrEmpty != null)
    }
    return this == null || this.isEmpty()
}