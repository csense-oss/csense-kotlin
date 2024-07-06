package org.csenseoss.kotlin.datastructures

public interface Cacheable<T> {
    public fun isCacheValid(): Boolean

    public fun invalidate()

    public fun getCachedValue(): T?
}