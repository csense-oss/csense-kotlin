package org.csenseoss.kotlin.datastructures.cachedBy.cachedByConstructor.operations

import org.csenseoss.kotlin.datastructures.cachedBy.cachedByConstructor.*
import org.csenseoss.kotlin.datastructures.cachedBy.timeout.*
import kotlin.time.*

public fun <T> CachedByConstructor.Companion.byTimeoutViaCurrentTime(
    getValue: () -> T,
    timeout: Duration
): CachedByConstructor<CachedItemByTimeout<T>> {
    return CachedByConstructor(
        cacheableGetter = {
            CachedItemByTimeout.forCurrentTime(getValue(), timeout)
        },
        isValidForCache = CachedItemByTimeout<T>::isNotTimedOut
    )
}