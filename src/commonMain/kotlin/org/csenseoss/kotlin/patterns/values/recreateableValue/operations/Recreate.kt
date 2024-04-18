@file:Suppress("NOTHING_TO_INLINE")

package org.csenseoss.kotlin.patterns.values.recreateableValue.operations

import org.csenseoss.kotlin.patterns.values.recreateableValue.*

public inline fun <Value> RecreateableValue<Value>.recreate(): Value {
    reset()
    return value
}