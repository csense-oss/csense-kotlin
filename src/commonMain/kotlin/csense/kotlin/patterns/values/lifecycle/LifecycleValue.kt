package csense.kotlin.patterns.values.lifecycle

import csense.kotlin.extensions.*
import csense.kotlin.patterns.values.recreateableValue.*

/**
 * A value related to a lifecycle. It is intended to wrap the "lifecycle" (say some creation and destroy code) into a single opbject
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
     * Retrives the current value or creates it (if destroy had been called)
     */
    public val value: Value
        get() = innerValue.value

    public fun reset(): Unit = value.ifNotNull {
        onResetValue(it)
        innerValue.reset()
    }

    public fun isValuePresent(): Boolean =
        innerValue.isValuePresent()
}
