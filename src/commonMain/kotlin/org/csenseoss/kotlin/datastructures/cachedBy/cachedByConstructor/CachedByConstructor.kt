package org.csenseoss.kotlin.datastructures.cachedBy.cachedByConstructor

import org.csenseoss.kotlin.datastructures.*
import org.csenseoss.kotlin.datastructures.cachedBy.*
import kotlin.reflect.*

public class CachedByConstructor<T>(
    private val cacheableGetter: () -> T,
    isValidForCache: (T) -> Boolean
) : Cacheable<T> {

    private val cacheBy: CachedBy<T> = CachedBy(isValidForCache)

    public fun cachedOrGet(): T = cacheBy.cachedOrBy(cacheableGetter)
    override fun getCachedValue(): T? = cacheBy.getCachedValue()
    override fun invalidate(): Unit = cacheBy.invalidate()
    override fun isCacheValid(): Boolean = cacheBy.isCacheValid()

    public operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return cachedOrGet()
    }

    public fun <R> onValidCache(action: (T) -> R): R? {
        val cachedValue: T? = getCachedValue()
        if (cachedValue != null && isCacheValid()) {
            return action(cachedValue)
        }
        return null
    }

    public inline fun <R> onInvalidCache(action: () -> R): R? {
        if (isCacheValid()) {
            return null
        }
        return action()
    }

    public companion object
}