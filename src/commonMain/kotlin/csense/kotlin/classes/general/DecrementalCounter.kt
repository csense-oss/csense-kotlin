package csense.kotlin.classes.general

import csense.kotlin.extensions.general.*
import csense.kotlin.extensions.primitives.boolean.*

/**
 *
 * @property value Int
 * @property valueAndIncrement Int
 * @constructor
 */
public class DecrementalCounter(from: Int) {
    public var value: Int = from
        private set
    public val valueAndDecrement: Int
        get() = value.also { decrement() }

    public fun decrement() {
        value -= 1
    }

    public fun decrementIf(shouldDecrement: Boolean): Unit = shouldDecrement.ifTrue {
        decrement()
    }.toUnit()
}