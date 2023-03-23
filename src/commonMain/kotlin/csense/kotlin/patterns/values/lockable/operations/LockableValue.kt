package csense.kotlin.patterns.values.lockable.operations

import csense.kotlin.*
import csense.kotlin.patterns.values.lockable.*
import kotlin.contracts.*
import kotlin.reflect.*


/**
 * Shortcut for locking with the given [newValue].
 * Does nothing if this is locked already
 */
public fun <T> LockableValue<T>.lockWith(newValue: T): Unit = whenUnlocked {
    value = newValue
    lock()
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
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }
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
) {
    contract {
        callsInPlace(onUnlocked, InvocationKind.AT_MOST_ONCE)
    }
    whenUnlocked {
        lock()
        onUnlocked()
    }
}

/**
 * Performs the given [action] if this [LockableValue.isLocked]
 * @param action EmptyFunction
 */
public inline fun <T> LockableValue<T>.ifLocked(action: EmptyFunction) {
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }
    if (isLocked()) {
        action()
    }
}

/**
 * Fail fast strategy, where you can return if this value is already [LockableValue.isLocked]
 * @receiver [LockableValue]<T>
 * @param action [Function0]<[Nothing]>
 */
public inline fun <T> LockableValue<T>.lockOrReturn(
    action: () -> Nothing
) {
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }
    if (isLocked()) {
        action()
    }
    lock()
}