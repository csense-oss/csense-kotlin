package csense.kotlin.classes.general

import csense.kotlin.extensions.general.*
import csense.kotlin.extensions.primitives.boolean.*

/**
 *
 * @property value Int
 * @property valueAndIncrement Int
 * @constructor
 */
public class IncrementalCounter(start: Int = 0) {
    public var value: Int = start
        private set
    public val valueAndIncrement: Int
        get() = value.also { increment() }

    public fun increment() {
        value += 1
    }

    public fun incrementIf(shouldIncrement: Boolean): Unit = shouldIncrement.ifTrue {
        increment()
    }.toUnit()
}