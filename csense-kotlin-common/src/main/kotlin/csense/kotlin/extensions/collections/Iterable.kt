package csense.kotlin.extensions.collections

import csense.kotlin.FunctionUnit
import csense.kotlin.*


/**
 * Invokes each function with the given arguments
 * @receiver Iterable<kotlin.Function1<I1, O>>
 * @param element I1
 */
@Suppress("NOTHING_TO_INLINE")
inline fun <I1, O> Iterable<kotlin.Function1<I1, O>>.invokeEachWith(element: I1) =
        forEach { it(element) }

/**
 * Invokes each function with the given arguments
 * @receiver Iterable<Function2<I1, I2, O>>
 * @param firstElement I1
 * @param secondElement I2
 */

@Suppress("NOTHING_TO_INLINE")
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
@Suppress("NOTHING_TO_INLINE")
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
@Suppress("NOTHING_TO_INLINE")
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
@Suppress("NOTHING_TO_INLINE")
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
@Suppress("NOTHING_TO_INLINE")
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


/**
 * Iterates over an optional collection and only running the function if the parameter is not null
 * @receiver Iterable<E?>
 * @param action FunctionUnit<E>
 */
@Suppress("NOTHING_TO_INLINE")
inline fun <E> Iterable<E?>.forEachNotNull(crossinline action: FunctionUnit<E>) {
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
 * @param out E
 * @property trueForPredicate List<E>
 * @property falseForPredicate List<E>
 * @constructor
 */
data class CollectionPartition<out E>(val trueForPredicate: List<E>, val falseForPredicate: List<E>)
