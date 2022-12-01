package csense.kotlin.general

/**
 * Indicates that this is not implemented
 * akin to a todo 
 * but intentional
 */
@Throws(NotImplementedError::class)
public fun notImplemented(): Nothing = throw NotImplementedError()