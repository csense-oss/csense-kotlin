package csense.kotlin.patterns.values.lifecycle

import csense.kotlin.extensions.*
import csense.kotlin.patterns.values.recreateableValue.*

/**
 * A value related to a lifecycle. It is intended to wrap the "lifecycle" (say some creation and destroy code) into a single object
 * Nb createValue is invoked eagerly (wrap the property in lazy if lazy is required)
 * @param Value
 * @property onResetValue Function1<Value, Unit>
 * @property innerValue RecreateableValueLazy<Value>
 * @property value Value
 * @constructor
 */
public class LifecycleValue<Value>(
    createValue: () -> Value,
    private val onResetValue: (Value) -> Unit
) {

    private val innerValue = RecreateableValue(createValue)

    /**
     * Gets the current instance or (re)creates it
     */
    public val value: Value
        get() = innerValue.value

    /**
     * Resets/removes the current instance
     */
    public fun reset(): Unit = value.ifNotNull {
        onResetValue(it)
        innerValue.reset()
    }

    /**
     * Tells if there currently is a value (of if it is [reset] / removed)
     * @return Boolean true if [value] will return the current instance
     * false means [value] will (re)create a new value
     */
    public fun isValuePresent(): Boolean =
        innerValue.isValuePresent()
}
