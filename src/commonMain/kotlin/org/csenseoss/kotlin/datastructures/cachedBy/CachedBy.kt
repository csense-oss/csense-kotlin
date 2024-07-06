package org.csenseoss.kotlin.datastructures.cachedBy

import org.csenseoss.kotlin.datastructures.*
import kotlin.contracts.*

public class CachedBy<T>(
    private val isCachedValid: (T) -> Boolean
): Cacheable<T> {
    private var cachedValue: T? = null

    public override fun isCacheValid(): Boolean {
        return cachedValue?.isValid() ?: false
    }

    public override fun invalidate() {
        cachedValue = null
    }

    public override fun getCachedValue(): T? {
        return cachedValue
    }

    public fun cachedOrBy(
        cacheableGetter: () -> T
    ): T {
        val cached: T? = cachedValue
        if (cached?.isValid() == true) {
            return cached
        }
        return cacheableGetter().also { it: T ->
            cachedValue = it
        }
    }

    private fun T.isValid(): Boolean {
        contract { returns(true) implies (this@isValid != null) }
        return isCachedValid(this)
    }
}