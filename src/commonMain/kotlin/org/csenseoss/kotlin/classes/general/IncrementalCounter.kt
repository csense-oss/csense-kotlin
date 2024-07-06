package org.csenseoss.kotlin.classes.general

import org.csenseoss.kotlin.extensions.general.*
import org.csenseoss.kotlin.extensions.primitives.boolean.*

/**
 * a simple counter, defaulting to start at 0.
 */
public class IncrementalCounter(
    start: Int = 0
) {
    /**
     * The current value
     */
    public var value: Int = start
        private set

    /**
     * Get the current value and then increment it
     */
    public val valueAndIncrement: Int
        get() = value.also { increment() }


    public fun increment() {
        value += 1
    }

    /**
     * Increment if and only if [shouldIncrement] is true.
     */
    public fun incrementIf(shouldIncrement: Boolean): Unit = shouldIncrement.ifTrue {
        increment()
    }.toUnit()
}