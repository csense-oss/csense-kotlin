@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.patterns.lazyArgument

import csense.kotlin.*

/**
 * A "lazy" alike construction that allows a value to be used for the "lazy" computation
 *
 * @param ValueType the resulting value (to be lazy created)
 * @param FirstArgumentType the argument type to create the [ValueType]
 * @property constructor [Function1]<FirstArgumentType, ValueType>
 */
public class LazyArgument<FirstArgumentType, ValueType>(
    private val constructor: (first: FirstArgumentType) -> ValueType
) {
    private var instance: ValueType? = null

    /**
     * Retrieves the instance if any or creates a new with the given argument
     * @return ValueType
     */
    public fun getValue(first: FirstArgumentType): ValueType {
        return instance ?: constructor(first).apply { instance = this }
    }

    /**
     * Retrieves the instance if any or creates a new with the given argument

     * @return ValueType
     */
    public inline operator fun invoke(
        first: FirstArgumentType
    ): ValueType = getValue(first)
}


public inline fun <FirstArgumentType, ValueType> lazyArgument(
    noinline constructor: (first: FirstArgumentType) -> ValueType
): LazyArgument<FirstArgumentType, ValueType> {
    return LazyArgument(constructor)
}
