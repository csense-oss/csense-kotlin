package csense.kotlin.patterns.values.lockable

import csense.kotlin.*
import csense.kotlin.annotations.numbers.*

/**
 * A mutable container that locks the amount of updates that are allowed (or if forced to lock)
 * Can never be unlocked once locked
 * @param T the type of value we are to contain
 * @property remainingUpdateCount [Int] the amount of updates that are allowed
 * @property value T the starting value that "may" be updated up to the provided update count
 */
public open class LockableValue<T>(
    @IntLimit(from = 1) maxUpdateCount: Int,
    initialValue: T,
    private val onUpdateRejected: EmptyFunction? = null
) {
    /**
     * How many update counts that are left before this value is locked.
     */
    @IntLimit(from = 0)
    private var remainingUpdateCount: Int = maxUpdateCount

    /**
     * The current value (getter)
     * setting the value might not update it if the maxUpdateCount reaches zero or negative
     */
    public var value: T = initialValue
        set(newValue) {
            if (isLocked()) {
                onUpdateRejected?.invoke()
                return
            }
            remainingUpdateCount -= 1
            field = newValue
        }

    public fun isLocked(): Boolean {
        return remainingUpdateCount <= 0
    }

    public fun isUnlocked(): Boolean {
        return !isLocked()
    }

    /**
     * Shortcut for locking this value.
     * Does nothing if this is locked already
     */
    public fun lock() {
        remainingUpdateCount = 0
    }
}
