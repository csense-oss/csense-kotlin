package org.csenseoss.kotlin.classes.general

import org.csenseoss.kotlin.extensions.general.*
import org.csenseoss.kotlin.extensions.primitives.boolean.*

/**
 * a simple decremental counter. Start with the given value, and either you get or "get and decrement" that value.
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