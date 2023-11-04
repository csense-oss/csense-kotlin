@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.collections.iterator

/**
 * If this iterator is at the end (no more elements)
 * @receiver Iterator<T>
 * @see Iterator.hasNext (this is the inverse of that)
 * @return [Boolean] true if there are no more elements. false if there are more elements.
 */
public inline fun <T> Iterator<T>.isAtEnd(): Boolean {
    return !hasNext()
}