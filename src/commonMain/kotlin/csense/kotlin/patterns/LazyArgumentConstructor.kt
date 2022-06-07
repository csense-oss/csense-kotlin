package csense.kotlin.patterns

import csense.kotlin.*
import kotlin.contracts.*

/**
 * A generalized lazy construct allowing to compute a lazy value by supplying the constructor on "get".
 * @param ValueType
 * @property instance ValueType?
 */
public class LazyArgumentConstructor<ValueType> {
    private var instance: ValueType? = null

    /**
     * gets the current instance or creates it (via [constructor]) and store the result for subsequent
     * usage
     * @param constructor [Function0]<ValueType> the constructor function to invoke iff the instance is null
     * @return ValueType
     */
    public fun getValue(constructor: Function0R<ValueType>): ValueType {
        contract {
            callsInPlace(constructor, InvocationKind.AT_MOST_ONCE)
        }
        return instance ?: constructor().apply { instance = this }
    }

    /**
     * Convenience function for [getValue]
     * gets the current instance or creates it (via [constructor]) and store the result for subsequent
     * usage
     * @param constructor [Function0]<ValueType> the constructor function to invoke iff the instance is null
     * @return ValueType
     */
    public operator fun invoke(constructor: Function0R<ValueType>): ValueType = getValue(constructor)

}
