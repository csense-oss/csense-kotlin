package csense.kotlin.datastructures.values.lockable

import csense.kotlin.*
import csense.kotlin.annotations.numbers.*
import kotlin.reflect.*

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
        lockWith(value)
    }

    /**
     * Shortcut for locking with the given [newValue].
     * Does nothing if this is locked already
     */
    public fun lockWith(newValue: T): Unit = whenUnlocked {
        value = newValue
        remainingUpdateCount = 0
    }

}

/**
 *
 * @receiver LockableValue<T>
 * @param any Any?
 * @param property KProperty<*>
 * @return T
 */
public operator fun <T> LockableValue<T>.getValue(any: Any?, property: KProperty<*>): T {
    return value
}

/**
 *
 * @receiver LockableValue<T>
 * @param item Any?
 * @param property KProperty<*>
 * @param newValue T
 */
public operator fun <T> LockableValue<T>.setValue(item: Any?, property: KProperty<*>, newValue: T) {
    value = newValue
}

/**
 * Executes the given action if this lockable value is unlocked. does not alter its state.
 * @param action [ReceiverFunctionUnit]<[LockableValue]<[T]> the callback to invoke if this is [LockableValue.isUnlocked]
 */
public inline fun <T> LockableValue<T>.whenUnlocked(
    action: ReceiverFunctionUnit<LockableValue<T>>
) {
    if (isUnlocked()) {
        action(this)
    }
}

/**
 * Runs the given action iff this is not locked, if it is locked, then nothing is performed
 * @param onUnlocked EmptyFunction 
 */
public inline fun <T> LockableValue<T>.lockWithAction(
    onUnlocked: EmptyFunction
): Unit = whenUnlocked {
    lock()
    onUnlocked()
}

/**
 * Performs the given [action] if this [LockableValue.isLocked]
 * @param action EmptyFunction 
 */
public inline fun <T> LockableValue<T>.ifLocked(action: EmptyFunction) {
    if (isLocked()) {
        action()
    }
}