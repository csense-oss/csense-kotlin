package org.csenseoss.kotlin.patterns.values.lockable.specializations

import org.csenseoss.kotlin.*
import org.csenseoss.kotlin.patterns.values.lockable.*

/**
 * A value that can only be set once
 * @param T
 * @constructor
 */
public open class SetOnce<T>(
    initialValue: T,
    onUpdateRejected: EmptyFunction? = null
) : LockableValue<T>(
    maxUpdateCount = 1,
    initialValue = initialValue,
    onUpdateRejected = onUpdateRejected
)