package org.csenseoss.kotlin.datastructures.cachedBy.cachedByConstructor

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class CachedByConstructorTest {
    @Test
    fun getCachedValueEmpty() {
        val cache: CachedByConstructor<Nothing> = CachedByConstructor(
            cacheableGetter = { shouldNotBeCalled() },
            isValidForCache = { failTest("cache is empty, no reason to ask if it is valid..") }
        )
        cache.getCachedValue().assertNull()
    }

    @Test
    fun isCacheValid(): Unit = assertCalled { shouldBeCalled ->
        val cache: CachedByConstructor<String> = CachedByConstructor(
            cacheableGetter = { "something" },
            isValidForCache = { it: String ->
                shouldBeCalled()
                false
            }
        )
        cache.cachedOrGet()
        cache.isCacheValid().assertFalse()
    }

    @Test
    fun cachedOrGet(): Unit = assertCalled(times = 2) { shouldBeCalled ->
        var realGetterCounter = 0
        val cache: CachedByConstructor<String> = CachedByConstructor(
            cacheableGetter = { realGetterCounter += 1; "test" },
            isValidForCache = { it: String ->
                shouldBeCalled()
                true
            }
        )
        //the first time we prime the cache
        cache.cachedOrGet().assert("test")
        //the second time we "validate" the cache is still ok.
        cache.cachedOrGet().assert("test")
        realGetterCounter.assert(1)
    }

    @Test
    fun cachedOrGetOnValidCacheEntry() {
        var realGetterCounter = 0
        val cache: CachedByConstructor<String> = CachedByConstructor(
            cacheableGetter = { realGetterCounter += 1; "notEmpty" },
            isValidForCache = { it: String ->
                it.isNotEmpty()
            }
        )
        cache.cachedOrGet().assert("notEmpty")
        realGetterCounter.assert(1)

        cache.cachedOrGet().assert("notEmpty")
        realGetterCounter.assert(1, message = "should NOT call real getter again")
    }

    @Test
    fun invalidate() {
        var realGetterCounter = 0
        val cache: CachedByConstructor<String> = CachedByConstructor(
            cacheableGetter = { realGetterCounter += 1; "example" },
            isValidForCache = { it: String ->
                true
            }
        )
        cache.cachedOrGet().assert("example")
        realGetterCounter.assert(1)

        cache.invalidate()
        cache.cachedOrGet().assert("example")
        realGetterCounter.assert(2)
    }

    @Test
    fun onValidCache() = assertCalled { shouldBeCalled ->
        val cache: CachedByConstructor<String> = CachedByConstructor(
            cacheableGetter = { "example" },
            isValidForCache = { it: String -> true }
        )
        cache.onValidCache<Nothing> { shouldNotBeCalled() }
        cache.cachedOrGet().assert("example")
        cache.onValidCache { it: String ->
            it.assert("example")
            shouldBeCalled()
        }
    }

    @Test
    fun onInvalidCache() = assertCalled { shouldBeCalled ->
        val cache: CachedByConstructor<String> = CachedByConstructor(
            cacheableGetter = { "example" },
            isValidForCache = { it: String -> true }
        )
        cache.onInvalidCache { shouldBeCalled() }
        cache.cachedOrGet().assert("example")
        cache.onInvalidCache<Nothing> { shouldNotBeCalled() }
    }

    @Test
    fun getCachedValue() {
        val cache: CachedByConstructor<String> = CachedByConstructor(
            cacheableGetter = { "example" },
            isValidForCache = { it: String -> true }
        )
        cache.getCachedValue().assertNull(message = "cache not primed")
        cache.cachedOrGet().assert("example")
        cache.getCachedValue().assert("example", message = "cache should be primed")

    }

    @Test
    fun getValue(): Unit = assertCalled(times = 2) { shouldBeCalled ->
        var realGetterCounter = 0
        val cache: CachedByConstructor<String> = CachedByConstructor(
            cacheableGetter = { realGetterCounter += 1; "test" },
            isValidForCache = { it: String ->
                shouldBeCalled()
                true
            }
        )
        //the first time we prime the cache
        cache.cachedOrGet().assert("test")
        //the second time we "validate" the cache is still ok.
        cache.cachedOrGet().assert("test")
        realGetterCounter.assert(1)
    }
}