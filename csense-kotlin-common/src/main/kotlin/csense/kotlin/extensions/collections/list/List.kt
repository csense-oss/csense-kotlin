package csense.kotlin.extensions.collections.list

import csense.kotlin.extensions.primitives.onTrue


/**
 * Returns a limited view of this list, by limiting the size of it (if the list is shorter than the limit,
 * then the result will be the lists' length).
 */
@Suppress("NOTHING_TO_INLINE")
inline fun <E> List<E>.limitToSize(size: Int): List<E> =
        subList(0, minOf(size, this.size))

@Suppress("NOTHING_TO_INLINE")
inline fun <T> List<T>.subList(intRange: IntRange): List<T> =
        subList(intRange.start, intRange.endInclusive)


@Suppress("NOTHING_TO_INLINE")
inline fun <T> List<T>.categorizeInto(vararg filters: (T) -> Boolean): List<List<T>> {
    val result = filters.map { mutableListOf<T>() }
    this.forEach {
        filters.forEachIndexed { index, filterAccepts ->
            filterAccepts(it).onTrue { result[index].add(it) }
        }
    }
    return result
}

@Suppress("NOTHING_TO_INLINE")
inline fun <T> List<T>.repeate(repeateBy: Int): List<T> {
    val resultList = this.toMutableList()
    for (i in 0 until repeateBy) {
        resultList += this
    }
    return resultList
}


inline fun <reified T> List<T>.repeateToSize(size: Int): List<T> {
    if (isEmpty() || size <= 0) {
        return listOf()
    }
    val timesToRepeate = size / count()
    val missingItemsToCopy = size % count()
    val resultList = this.repeate(timesToRepeate - 1)
    return resultList + this.subList(0, missingItemsToCopy)
}
