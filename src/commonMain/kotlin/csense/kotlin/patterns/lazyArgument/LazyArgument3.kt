@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.patterns.lazyArgument

/**
 *  A "lazy" alike construction that allows a value to be used for the "lazy" computation
 * @param ValueType the resulting value (to be lazy created)
 * @property constructor Function3<FirstArgumentType, SecondArgumentType, ThirdArgumentType, ValueType> how to construct the [ValueType] given the arguments
 */
public class LazyArgument3<ValueType, FirstArgumentType, SecondArgumentType, ThirdArgumentType>(
    private val constructor: (first: FirstArgumentType, second: SecondArgumentType, third: ThirdArgumentType) -> ValueType
) {
    private var instance: ValueType? = null

    /**
     * Retrieves the instance if any or creates a new with the given arguments
     * @return ValueType
     */
    public fun getValue(
        first: FirstArgumentType,
        second: SecondArgumentType,
        third: ThirdArgumentType
    ): ValueType {
        return instance ?: constructor(first, second, third).apply { instance = this }
    }

    /**
     * Retrieves the instance if any or creates a new with the given arguments
     * @return ValueType
     */
    public inline operator fun invoke(
        first: FirstArgumentType,
        second: SecondArgumentType,
        third: ThirdArgumentType
    ): ValueType = getValue(first, second, third)
}

public inline fun <ValueType, FirstArgumentType, SecondArgumentType, ThirdArgumentType> lazyArgument(
    noinline constructor: (first: FirstArgumentType, second: SecondArgumentType, third: ThirdArgumentType) -> ValueType
): LazyArgument3<ValueType, FirstArgumentType, SecondArgumentType, ThirdArgumentType> {
    return LazyArgument3(constructor)
}
