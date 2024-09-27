package org.csenseoss.kotlin.datastructures.cachedBy.cachedByConstructor.operations

import csense.kotlin.tests.assertions.*
import org.csenseoss.kotlin.datastructures.cachedBy.cachedByConstructor.*
import org.csenseoss.kotlin.datastructures.cachedBy.timeout.*
import kotlin.test.*
import kotlin.time.*

class UnwrapTest {

    @Test
    fun unwrap() {
        val testTime = TestTimeSource()
        val underlayingCache: CachedByConstructor<CachedItemByTimeout<String>> = CachedByConstructor(
            cacheableGetter = {
                CachedItemByTimeout(
                    timeout = Duration.ZERO,
                    start = testTime.markNow(),
                    value = "test"
                )
            },
            isValidForCache = { _: CachedItemByTimeout<String> -> true }
        )
        val unwrapped: CachedByConstructor<String> = underlayingCache.unwrap()
        unwrapped.cachedOrGet().assert("test")
        unwrapped.getCachedValue().assertNull()
        unwrapped.isCacheValid().assertFalse()

    }
}