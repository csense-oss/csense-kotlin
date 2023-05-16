package csense.kotlin.extensions.collections.iterable

import csense.kotlin.extensions.collections.*
import csense.kotlin.extensions.collections.generic.collectionPartition.*


/**
 * Performs a partition of the given iterable. (also known as splitting) by the given predicate function
 * A safer alternative to the standard library partition (that only yields a pair, which does not enforce a clear understanding of what the first or second is.)
 * @receiver [Iterable]<E> the collection to partition
 * @param predicate [Function1]<E, Boolean> whenever the given element should be in the "true" list or false
 * @return [CollectionPartition]<E> the result by partition / splitting the content by the given function
 */
public inline fun <E> Iterable<E>.partitionSafe(predicate: Predicate<E>): CollectionPartition<E> {
    return this.partition(predicate).let { it: Pair<List<E>, List<E>> ->
        CollectionPartition(trueForPredicate = it.first, falseForPredicate = it.second)
    }
}

