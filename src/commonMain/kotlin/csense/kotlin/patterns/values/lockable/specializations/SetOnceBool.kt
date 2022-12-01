package csense.kotlin.patterns.values.lockable.specializations

import csense.kotlin.*


/**
 * Since a readonly [Boolean] is usually preferred,
 * there are times when you want a [Boolean] value to be able to be set once after initialization and then be "Locked"
 */
public class SetOnceBool(
    initialValue: Boolean,
    onUpdateRejected: EmptyFunction? = null
) : SetOnce<Boolean>(
    initialValue = initialValue,
    onUpdateRejected = onUpdateRejected
)
