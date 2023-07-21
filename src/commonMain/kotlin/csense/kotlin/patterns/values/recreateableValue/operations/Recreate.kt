@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.patterns.values.recreateableValue.operations

import csense.kotlin.patterns.values.recreateableValue.*

public inline fun <Value> RecreateableValue<Value>.recreate(): Value {
    reset()
    return value
}