package org.csenseoss.kotlin.datastructures.cachedBy.cachedByArguments

import org.csenseoss.kotlin.datastructures.cachedBy.*

public class CachedByArguments<T, I1>(
    isValidForCache: (T) -> Boolean,
    private val isArgumentEqual: (I1?, I1?) -> Boolean
) {

    private val cachedBy: CachedBy<T> = CachedBy(isValidForCache)

    private var cachedArgument1: I1? = null

    public fun isCacheValid(argument: I1): Boolean {
        onArgumentsMismatch(argument) {
            return@isCacheValid false
        }
        return cachedBy.isCacheValid()
    }

    public fun invalidate() {
        cachedArgument1 = null
        cachedBy.invalidate()
    }

    public fun getCachedValue(argument: I1): T? {
        onArgumentsMismatch(argument) {
            return@getCachedValue null
        }
        return cachedBy.getCachedValue()
    }

    public fun cachedOrGetBy(
        argument: I1,
        cacheableGetter: (I1) -> T
    ): T {
        onArgumentsMismatch(argument) {
            cachedBy.invalidate()
        }
        return cachedBy.cachedOrBy(cacheableGetter = {
            cachedArgument1 = argument
            cacheableGetter(argument)
        })
    }

    private inline fun <T> onArgumentsMismatch(
        argument: I1,
        action: () -> T
    ) {
        if (!isArgumentEqual(argument, cachedArgument1)) {
            action()
        }
    }

}