package csense.kotlin.stdlib

/**
 * Since a readonly boolean is usually preferred, there are times where you want a boolean value to be able to be set once after initialization and then be "Locked"
 */
class SetOnceBool(initialValue: Boolean) : LockableValue<Boolean>(1, initialValue)