package csense.kotlin.extensions.collections

import csense.kotlin.FunctionUnit
import csense.kotlin.*


/**
 * Invokes each function with the given arguments
 */
@Suppress("NOTHING_TO_INLINE")
inline fun <I1, O> Iterable<kotlin.Function1<I1, O>>.invokeEachWith(element: I1) =
        forEach { it(element) }


@Suppress("NOTHING_TO_INLINE")
inline fun <I1, I2, O> Iterable<Function2<I1, I2, O>>.invokeEachWith(
        firstElement: I1,
        secondElement: I2) =
        forEach { it(firstElement, secondElement) }


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


@Suppress("NOTHING_TO_INLINE")
inline fun <E> Iterable<E?>.forEachNotNull(crossinline action: FunctionUnit<E>) {
    forEach { it?.let(action) }
}


/**
 * A safer alternative to the STD partition, that only yields a pair, which does not enforce a clear understanding of what the first or second is.
 *
 */
fun <E> Iterable<E>.partitionSafe(predicate: Function1<E, Boolean>): CollectionPartition<E> {
    return this.partition(predicate).let {
        CollectionPartition(it.first, it.second)
    }
}

/**
 *
 */
data class CollectionPartition<out E>(val trueForPredicate: List<E>, val falseForPredicate: List<E>)
