package csense.kotlin.datastructures.values.lockable

import csense.kotlin.*

public open class SetOnce<T>(
    initialValue: T,
    onUpdateRejected: EmptyFunction? = null
) : LockableValue<T>(1, initialValue, onUpdateRejected)


/**
 * Since a readonly [Boolean] is usually preferred, there are times when you want a [Boolean] value to be able to be set once after initialization and then be "Locked"
 */
public class SetOnceBool(
    initialValue: Boolean,
    onUpdateRejected: EmptyFunction? = null
) : SetOnce<Boolean>(initialValue, onUpdateRejected)
