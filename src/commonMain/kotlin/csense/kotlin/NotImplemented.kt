@file:OptIn(ExperimentalContracts::class)

package csense.kotlin

import kotlin.contracts.*

/**
 * Indicates that this is not implemented
 * akin to a todo 
 * but intentional
 */
@Throws(NotImplementedError::class)
public fun notImplemented(): Nothing = throw NotImplementedError()