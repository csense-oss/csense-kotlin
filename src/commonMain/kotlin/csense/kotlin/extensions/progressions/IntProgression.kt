@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.progressions

import csense.kotlin.annotations.numbers.*


/**
 * Maps a progression into an array of all the values.
 * @receiver [IntProgression]
 * @return [IntArray]
 */

public inline fun IntProgression.toIntArray(): IntArray = this.toList().toIntArray()

/**
 * The length of an [IntProgression]
 * its the number of times steps have to be taken to get to the end.
 * or simply put, the number of times it would run in a loop.
 */
public inline val IntProgression.length: Int
    @IntLimit(from = 0)
    get() = ((last + step) - first) / step //+ step due to "inclusive".

/**
 * Skips "[step]s" of this [IntProgression]
 * @receiver [IntProgression] the progression to skip the given steps (if this has a negative length, the given value is returned)
 * @param lengthToSkip [Int] the [step]s to skip
 * @return [IntProgression] the resulting IntProgression when skipping
 */
public inline fun IntProgression.skip(lengthToSkip: Int): IntProgression {
    if (length <= 0) {
        return this
    }
    val lengthEnd = (lengthToSkip * step) + first
    val newStart = lengthEnd.coerceAtMost(last)
    return IntProgression.fromClosedRange(newStart, last, step)
}
