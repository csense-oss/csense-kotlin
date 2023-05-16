package csense.kotlin.extensions.collections.generic.collectionPartition

/**
 * The result from a safe partition / split operation
 * @param E The data / element type
 * @property trueForPredicate [List]<E> the elements considered "true" / included
 * @property falseForPredicate [List]<E> the elements considered "false" / not included
 */
public data class CollectionPartition<out E>(
    public val trueForPredicate: List<E>,
    public val falseForPredicate: List<E>
)