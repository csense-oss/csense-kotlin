@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.collections

import csense.kotlin.*
import csense.kotlin.extensions.collections.generic.*
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

//region Invoke each Lazy
/**
 * Invokes each function with the given argument(s) computed lazy
 * @receiver Iterable<kotlin.Function1<I1, O>>
 * @param element Function0R<I1>
 */
@UseExperimental(ExperimentalContracts::class)
inline fun <I1, O> Iterable<Function1<I1, O>>.invokeEachWithLazy(
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
 * @receiver Iterable<Function2<I1, I2, O>>
 * @param firstElement I1
 * @param secondElement I2
 */

@UseExperimental(ExperimentalContracts::class)
inline fun <I1, I2, O> Iterable<Function2<I1, I2, O>>.invokeEachWithLazy(
        firstElement: Function0R<I1>,
        secondElement: Function0R<I2>) {
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
 * @receiver Iterable<Function3<I1, I2, I3, O>>
 * @param firstElement I1
 * @param secondElement I2
 * @param thirdElement I3
 */
@UseExperimental(ExperimentalContracts::class)
inline fun <I1, I2, I3, O>
        Iterable<Function3<I1, I2, I3, O>>.invokeEachWithLazy(
        firstElement: Function0R<I1>,
        secondElement: Function0R<I2>,
        thirdElement: Function0R<I3>) {
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
 * @receiver Iterable<Function4<I1, I2, I3, I4, O>>
 * @param firstElement I1
 * @param secondElement I2
 * @param thirdElement I3
 * @param forthElement I4
 */
@UseExperimental(ExperimentalContracts::class)
inline fun <I1, I2, I3, I4, O>
        Iterable<Function4<I1, I2, I3, I4, O>>.invokeEachWithLazy(
        firstElement: Function0R<I1>,
        secondElement: Function0R<I2>,
        thirdElement: Function0R<I3>,
        forthElement: Function0R<I4>) {
    contract {
        callsInPlace(firstElement, InvocationKind.AT_MOST_ONCE)
        callsInPlace(secondElement, InvocationKind.AT_MOST_ONCE)
        callsInPlace(thirdElement, InvocationKind.AT_MOST_ONCE)
        callsInPlace(forthElement, InvocationKind.AT_MOST_ONCE)
    }
    skipIfEmptyOr {
        invokeEachWith(
                firstElement(),
                secondElement(),
                thirdElement(),
                forthElement())
    }
}

/**
 * Invokes each function with the given arguments
 * @receiver Iterable<Function5<I1, I2, I3, I4, I5, O>>
 * @param firstElement I1
 * @param secondElement I2
 * @param thirdElement I3
 * @param forthElement I4
 * @param fifthElement I5
 */
@UseExperimental(ExperimentalContracts::class)
inline fun <I1, I2, I3, I4, I5, O>
        Iterable<Function5<I1, I2, I3, I4, I5, O>>.invokeEachWithLazy(
        firstElement: Function0R<I1>,
        secondElement: Function0R<I2>,
        thirdElement: Function0R<I3>,
        forthElement: Function0R<I4>,
        fifthElement: Function0R<I5>) {
    contract {
        callsInPlace(firstElement, InvocationKind.AT_MOST_ONCE)
        callsInPlace(secondElement, InvocationKind.AT_MOST_ONCE)
        callsInPlace(thirdElement, InvocationKind.AT_MOST_ONCE)
        callsInPlace(forthElement, InvocationKind.AT_MOST_ONCE)
        callsInPlace(fifthElement, InvocationKind.AT_MOST_ONCE)
    }
    skipIfEmptyOr {
        invokeEachWith(
                firstElement(),
                secondElement(),
                thirdElement(),
                forthElement(),
                fifthElement()
        )
    }
}

/**
 * Invokes each function with the given arguments
 * @receiver Iterable<Function6<I1, I2, I3, I4, I5, I6, O>>
 * @param firstElement I1
 * @param secondElement I2
 * @param thirdElement I3
 * @param forthElement I4
 * @param fifthElement I5
 * @param sixthElement I6
 */
@UseExperimental(ExperimentalContracts::class)
inline fun <I1, I2, I3, I4, I5, I6, O>
        Iterable<Function6<I1, I2, I3, I4, I5, I6, O>>.invokeEachWithLazy(
        firstElement: Function0R<I1>,
        secondElement: Function0R<I2>,
        thirdElement: Function0R<I3>,
        forthElement: Function0R<I4>,
        fifthElement: Function0R<I5>,
        sixthElement: Function0R<I6>) {
    contract {
        callsInPlace(firstElement, InvocationKind.AT_MOST_ONCE)
        callsInPlace(secondElement, InvocationKind.AT_MOST_ONCE)
        callsInPlace(thirdElement, InvocationKind.AT_MOST_ONCE)
        callsInPlace(forthElement, InvocationKind.AT_MOST_ONCE)
        callsInPlace(fifthElement, InvocationKind.AT_MOST_ONCE)
        callsInPlace(sixthElement, InvocationKind.AT_MOST_ONCE)
    }
    skipIfEmptyOr {
        invokeEachWith(
                firstElement(),
                secondElement(),
                thirdElement(),
                forthElement(),
                fifthElement(),
                sixthElement()
        )
    }
}

//endregion

//region Invoke each with
/**
 * Invokes each function with the given arguments
 * @receiver Iterable<kotlin.Function1<I1, O>>
 * @param element I1
 */

inline fun <I1, O> Iterable<Function1<I1, O>>.invokeEachWith(
        element: I1) =
        forEach { it(element) }

/**
 * Invokes each function with the given arguments
 * @receiver Iterable<Function2<I1, I2, O>>
 * @param firstElement I1
 * @param secondElement I2
 */


inline fun <I1, I2, O> Iterable<Function2<I1, I2, O>>.invokeEachWith(
        firstElement: I1,
        secondElement: I2) =
        forEach { it(firstElement, secondElement) }

/**
 * Invokes each function with the given arguments
 * @receiver Iterable<Function3<I1, I2, I3, O>>
 * @param firstElement I1
 * @param secondElement I2
 * @param thirdElement I3
 */

inline fun <I1, I2, I3, O>
        Iterable<Function3<I1, I2, I3, O>>.invokeEachWith(
        firstElement: I1,
        secondElement: I2,
        thirdElement: I3) =
        forEach {
            it(firstElement,
                    secondElement,
                    thirdElement)
        }

/**
 *  Invokes each function with the given arguments
 * @receiver Iterable<Function4<I1, I2, I3, I4, O>>
 * @param firstElement I1
 * @param secondElement I2
 * @param thirdElement I3
 * @param forthElement I4
 */

inline fun <I1, I2, I3, I4, O>
        Iterable<Function4<I1, I2, I3, I4, O>>.invokeEachWith(
        firstElement: I1,
        secondElement: I2,
        thirdElement: I3,
        forthElement: I4) =
        forEach {
            it(firstElement,
                    secondElement,
                    thirdElement,
                    forthElement)
        }

/**
 * Invokes each function with the given arguments
 * @receiver Iterable<Function5<I1, I2, I3, I4, I5, O>>
 * @param firstElement I1
 * @param secondElement I2
 * @param thirdElement I3
 * @param forthElement I4
 * @param fifthElement I5
 */

inline fun <I1, I2, I3, I4, I5, O>
        Iterable<Function5<I1, I2, I3, I4, I5, O>>.invokeEachWith(
        firstElement: I1,
        secondElement: I2,
        thirdElement: I3,
        forthElement: I4,
        fifthElement: I5) =
        forEach {
            it(firstElement,
                    secondElement,
                    thirdElement,
                    forthElement,
                    fifthElement)
        }

/**
 * Invokes each function with the given arguments
 * @receiver Iterable<Function6<I1, I2, I3, I4, I5, I6, O>>
 * @param firstElement I1
 * @param secondElement I2
 * @param thirdElement I3
 * @param forthElement I4
 * @param fifthElement I5
 * @param sixthElement I6
 */

inline fun <I1, I2, I3, I4, I5, I6, O>
        Iterable<Function6<I1, I2, I3, I4, I5, I6, O>>.invokeEachWith(
        firstElement: I1,
        secondElement: I2,
        thirdElement: I3,
        forthElement: I4,
        fifthElement: I5,
        sixthElement: I6) =
        forEach {
            it(firstElement,
                    secondElement,
                    thirdElement,
                    forthElement,
                    fifthElement,
                    sixthElement)
        }
//endregion


/**
 * Iterates over an optional collection and only running the function if the parameter is not null
 * @receiver Iterable<E?>
 * @param action FunctionUnit<E>
 */

inline fun <E : Any> Iterable<E?>.forEachNotNull(action: FunctionUnit<E>) {
    forEach { it?.let(action) }
}


/**
 * A safer alternative to the STD partition, that only yields a pair, which does not enforce a clear understanding of what the first or second is.
 * @receiver Iterable<E>
 * @param predicate Function1<E, Boolean>
 * @return CollectionPartition<E>
 */
fun <E> Iterable<E>.partitionSafe(predicate: Function1<E, Boolean>): CollectionPartition<E> {
    return this.partition(predicate).let {
        CollectionPartition(it.first, it.second)
    }
}


/**
 *
 * @param E
 * @property trueForPredicate List<E>
 * @property falseForPredicate List<E>
 * @constructor
 */
data class CollectionPartition<out E>(val trueForPredicate: List<E>, val falseForPredicate: List<E>)


//region Generic collection extensions
/**
 * Performs traversal in pairs of 2  (with the first index as well)
 * @receiver List<T>
 * @param action Function2IndexedUnit<T, T>
 */
inline fun <T> Iterable<T>.forEach2Indexed(action: Function2IndexedUnit<T, T>) =
        GenericCollectionExtensions.forEach2Indexed(count(), ::elementAt, action)

/**
 * Performs traversal in pairs of 2
 * @receiver List<T>
 * @param action Function2Unit<T, T>
 */
inline fun <T> Iterable<T>.foreach2(action: Function2Unit<T, T>) =
        GenericCollectionExtensions.forEach2(count(), ::elementAt, action)

/**
 * Performs backwards traversal on this list.
 * @receiver List<T>
 * @param action FunctionUnit<T>
 */
inline fun <T> Iterable<T>.forEachBackwards(action: FunctionUnit<T>) =
        GenericCollectionExtensions.forEachBackwards(count(), this::elementAt, action)
//endregion


@UseExperimental(ExperimentalContracts::class)
inline fun <T> Iterable<T>.skipIfEmptyOr(function: () -> Unit) {
    contract {
        callsInPlace(function, InvocationKind.AT_MOST_ONCE)
    }
    if (isNotEmpty()) {
        function()
    }
}

inline fun <T> Iterable<T>.isEmpty(): Boolean = !isNotEmpty()

inline fun <T> Iterable<T>.isNotEmpty(): Boolean = any()