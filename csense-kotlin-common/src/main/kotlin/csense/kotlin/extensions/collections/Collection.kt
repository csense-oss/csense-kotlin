package csense.kotlin.extensions.collections


@Suppress("NOTHING_TO_INLINE")
inline fun Collection<*>.isIndexValid(index: Int) =
        index >= 0 && index < count()


@Suppress("NOTHING_TO_INLINE")
inline fun Collection<*>.isIndexValidForInsert(index: Int) =
        index >= 0 && index <= count()


@Suppress("NOTHING_TO_INLINE")
inline fun <T> Collection<T>.getSafe(index: Int): T? =
        if (this.isIndexValid(index)) {
            elementAt(index)
        } else {
            null
        }


@Suppress("NOTHING_TO_INLINE")
inline fun Collection<*>.isRangeValid(intRange: IntRange): Boolean =
        (intRange.start >= 0 && intRange.endInclusive < size)