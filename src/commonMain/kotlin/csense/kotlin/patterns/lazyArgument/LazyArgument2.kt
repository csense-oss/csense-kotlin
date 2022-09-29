@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.patterns.lazyArgument

/**
 *  A "lazy" alike construction that allows a value to be used for the "lazy" computation
 * @param FirstArgumentType
 * @param SecondArgumentType
 * @param ValueType the resulting value (to be lazy created)
 * @property constructor Function2<FirstArgumentType,SecondArgumentType, ValueType> how to construct the [ValueType] given the arguments
 */
public class LazyArgument2<FirstArgumentType, SecondArgumentType, ValueType>(
    private val constructor: (first: FirstArgumentType, second: SecondArgumentType) -> ValueType
) {
    private var instance: ValueType? = null

    /**
     * Retrieves the instance if any or creates a new with the given arguments
     * @param first FirstArgumentType
     * @return ValueType
     */
    public fun getValue(first: FirstArgumentType, second: SecondArgumentType): ValueType {
        return instance ?: constructor(first, second).apply { instance = this }
    }

    /**
     * Retrieves the instance if any or creates a new with the given arguments
     * @param first FirstArgumentType
     * @return ValueType
     */
    public inline operator fun invoke(
        first: FirstArgumentType,
        second: SecondArgumentType
    ): ValueType = getValue(first, second)
}

public inline fun <FirstArgumentType, SecondArgumentType, ValueType> lazyArgument(
    noinline constructor: (first: FirstArgumentType, second: SecondArgumentType) -> ValueType
): LazyArgument2<FirstArgumentType, SecondArgumentType, ValueType> {
    return LazyArgument2(constructor)
}
