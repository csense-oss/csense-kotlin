package org.csenseoss.kotlin.datastructures.cachedBy

import org.csenseoss.kotlin.datastructures.*
import kotlin.contracts.*

public class CachedBy<T>(
    private val isCachedValid: (T) -> Boolean
) : Cacheable<T> {
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
        cachedValue?.onValid { it: T ->
            return@cachedOrBy it
        }
        return cacheableGetter().onValid { it: T ->
            cachedValue = it
        }
    }

    private inline fun T.onValid(action: (T) -> Unit): T = apply {
        if (isValid()) {
            action(this)
        }
    }

    private fun T.isValid(): Boolean {
        contract { returns(true) implies (this@isValid != null) }
        return isCachedValid(this)
    }
}