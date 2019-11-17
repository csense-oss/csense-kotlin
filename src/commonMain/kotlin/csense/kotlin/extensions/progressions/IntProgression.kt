@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.progressions

import csense.kotlin.annotations.numbers.IntLimit


/**
 * Maps a progression into an array of all the values.
 * @receiver IntProgression
 * @return IntArray
 */

inline fun IntProgression.toIntArray(): IntArray = this.toList().toIntArray()

/**
 * The length of an IntProgression
 * its the number of times steps have to be taken to get to the end.
 * or simply put, the number of times it would run in a loop.
 */
inline val IntProgression.length
    @IntLimit(from = 0)
    get() = ((last + step) - first) / step //+ step due to "inclusive".

