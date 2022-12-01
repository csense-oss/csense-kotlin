package csense.kotlin.classes.general

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
}