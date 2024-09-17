package org.csenseoss.kotlin.datastructures

public interface Cacheable<T> {
    /**
     * Whenever the cache is valid
     * @return [Boolean.true] if valid, false otherwise
     */
    public fun isCacheValid(): Boolean

    /**
     * Invalidates the cache and thus the cached value is null'ed
     */
    public fun invalidate()

    /**
     * Retrieves the cached value if any, requires the cache to be populated (With a valid item) to contain an item
     */
    public fun getCachedValue(): T?
}