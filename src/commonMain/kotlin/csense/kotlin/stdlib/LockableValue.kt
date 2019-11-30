package csense.kotlin.stdlib

import csense.kotlin.annotations.numbers.IntLimit
import csense.kotlin.extensions.primitives.isNegativeOrZero

/**
 * A mutable container that locks the amount of updates that are allowed
 * @param T the type of value we are to contain
 * @property remainingUpdateCount Int the amount of updates that are allowed
 * @property value T the starting value that "may" be updated up to the provided update count
 */
open class LockableValue<T>(
        @IntLimit(from = 0) private var maxUpdateCount: Int,
        initialValue: T
) {
    /**
     * The current value (getter)
     * setting the value might not update it if the maxUpdateCount reaches zero or negative
     */
    var value: T = initialValue
        set(newValue) {
            if (maxUpdateCount.isNegativeOrZero) {
                return
            }
            maxUpdateCount -= 1
            field = newValue
        }
}