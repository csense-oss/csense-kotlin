package org.csenseoss.kotlin.datastructures.cachedBy

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class CachedByTest {
    @Test
    fun isCacheValid(): Unit = assertCalled { shouldBeCalled: () -> Unit ->
        val cache: CachedBy<String> = CachedBy { it: String -> shouldBeCalled(); true }
        cache.isCacheValid().assertFalse("when empty,it is never valid")
        cache.cachedOrBy { "prime cache" }
        cache.cachedOrBy { "make sure is cache valid is called" }
    }

    @Test
    fun invalidate() {
        val cache: CachedBy<String> = CachedBy { it: String -> true }
        cache.cachedOrBy { "priming" }
        cache.cachedOrBy { shouldNotBeCalled() }.assert("priming")
        cache.invalidate()
        assertCalled { shouldBeCalled: () -> Unit ->
            cache.cachedOrBy { shouldBeCalled();"test" }.assert("test")
        }

    }

    @Test
    fun getCachedValue() {
        val cache: CachedBy<String> = CachedBy { it: String -> true }
        cache.isCacheValid().assertFalse("when empty,it is never valid")
        cache.getCachedValue().assertNull()
        cache.cachedOrBy { "prime cache" }
        cache.getCachedValue().assert("prime cache")
    }

    @Test
    fun cachedOrBy() {
        val upToDateCache: CachedBy<String> = CachedBy { it: String -> true }
        upToDateCache.cachedOrBy { "first call" }.assert("first call")
        upToDateCache.cachedOrBy { shouldNotBeCalled() }.assert("first call", message = "since the cache is up to date")

        val neverUpToDateCache: CachedBy<String> = CachedBy { it: String -> false }
        neverUpToDateCache.cachedOrBy { "first call" }.assert("first call")
        neverUpToDateCache.cachedOrBy { "second call" }
            .assert("second call", message = "since the cache is invalid, it needs to fetch again")
    }
}