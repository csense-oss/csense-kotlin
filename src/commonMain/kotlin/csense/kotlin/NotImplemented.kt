@file:OptIn(ExperimentalContracts::class)

package csense.kotlin

import kotlin.contracts.*

/**
 * Indicates that this is not implemented
 * akin to a todo 
 * but intentional
 */
public fun notImplemented(): Nothing = throw NotImplementedError()