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
        val underlayingCache = CachedByConstructor(
            cacheableGetter = {
                CachedItemByTimeout<String>(
                    timeout = Duration.ZERO,
                    start = testTime.markNow(),
                    value = "test"
                )
            },
            isValidForCache = { x -> true }
        )
        val unwrapped = underlayingCache.unwrap()
        unwrapped.cachedOrGet().assert("test")
        unwrapped.getCachedValue().assert("test")
        unwrapped.isCacheValid().assertTrue()

    }
}