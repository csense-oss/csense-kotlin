@file:OptIn(ExperimentalContracts::class)

package csense.kotlin.patterns

import csense.kotlin.*
import kotlin.contracts.*


public class LazyArgument<T> {
    private var instance: T? = null

    /**
     * gets the current instance or creates it (via [constructor]) and store the result for subsequent
     * usage
     * @param constructor [Function0]<T> the constructor function to invoke iff the instance is null
     * @return T
     */
    public fun getValue(constructor: Function0R<T>): T {
        contract {
            callsInPlace(constructor, InvocationKind.AT_MOST_ONCE)
        }
        return instance ?: constructor().apply { instance = this }
    }

    /**
     * Convenience function for [getValue]
     * gets the current instance or creates it (via [constructor]) and store the result for subsequent
     * usage
     * @param constructor [Function0]<T> the constructor function to invoke iff the instance is null
     * @return T
     */
    public operator fun invoke(constructor: Function0R<T>): T = getValue(constructor)

}
