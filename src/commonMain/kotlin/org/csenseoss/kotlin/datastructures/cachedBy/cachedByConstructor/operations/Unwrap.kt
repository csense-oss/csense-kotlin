package org.csenseoss.kotlin.datastructures.cachedBy.cachedByConstructor.operations

import org.csenseoss.kotlin.datastructures.cachedBy.cachedByConstructor.*
import org.csenseoss.kotlin.datastructures.cachedBy.timeout.*

public fun <T> CachedByConstructor<CachedItemByTimeout<T>>.unwrap(

): CachedByConstructor<T> {
    return CachedByConstructor(
        cacheableGetter = { this.cachedOrGet().value },
        isValidForCache = { false }
    )
}