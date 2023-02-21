package csense.kotlin.patterns.values.recreateableValue

import csense.kotlin.*

/**
 * A value that can be (re)created (by first resetting it)
 * Nb the creation is eager by default (intention is that if you want it lazy, you can wrap the whole variable in "by lazy").
 * @param Value
 * @property createValue [Function0R]<Value>
 * @property innerValue Value?
 * @property value Value
 * @constructor
 */
public class RecreateableValue<Value>(
    private val createValue: Function0R<Value>
) {
    private var innerValue: Value? = createValue()

    /**
     * Gets the current instance or (re)creates it
     */
    public val value: Value
        get() = innerValue ?: createValue().also { innerValue = it }

    /**
     * Resets/removes the current instance
     */
    public fun reset() {
        innerValue = null
    }

    /**
     * Tells if there currently is a value (of if it is [reset] / removed)
     * @return Boolean true if [value] will return the current instance
     * false means [value] will (re)create a new value
     */
    public fun isValuePresent(): Boolean = innerValue != null
}