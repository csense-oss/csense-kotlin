package csense.kotlin.general

/**
 * Indicates that this is not implemented
 * akin to a todo (intentional)
 */
@Throws(NotImplementedError::class)
public fun notImplemented(reason: String = "An operation is not implemented."): Nothing =
    throw NotImplementedError(reason)