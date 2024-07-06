package org.csenseoss.kotlin.datastructures.cachedBy.cachedByArguments

import org.csenseoss.kotlin.datastructures.cachedBy.*

public class CachedByArguments<T, I1>(
    isValidForCache: (T) -> Boolean,
    private val isArgumentEqual: (I1?, I1?) -> Boolean
) {

    private val cachedBy: CachedBy<T> = CachedBy(isValidForCache)

    private var cachedArgument1: I1? = null

    public fun isCacheValid(argument: I1): Boolean {
        onArgumentsMisMatch(argument){
            return@isCacheValid false
        }
        return cachedBy.isCacheValid()
    }

    public fun invalidate() {
        cachedArgument1 = null
        cachedBy.invalidate()
    }

    public fun getCachedValue(argument: I1): T? {
        onArgumentsMisMatch(argument){
            return@getCachedValue null
        }
        return cachedBy.getCachedValue()
    }

    public fun cachedOrGetBy(
        argument: I1,
        cacheableGetter: (I1) -> T
    ) {
        onArgumentsMisMatch(argument){
            cachedBy.invalidate()
        }
        cachedBy.cachedOrBy(cacheableGetter = {
            cacheableGetter(argument)
        })
    }

    private inline fun <T> onArgumentsMisMatch(
        argument: I1,
        action: () -> T
    ) {
        if (!isArgumentEqual(argument, cachedArgument1)) {
            action()
        }
    }

}