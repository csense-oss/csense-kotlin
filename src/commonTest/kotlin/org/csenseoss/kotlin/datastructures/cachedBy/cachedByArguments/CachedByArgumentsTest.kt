package org.csenseoss.kotlin.datastructures.cachedBy.cachedByArguments

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class CachedByArgumentsTest {
    @Test
    fun isCacheValid() {
        val cache = alwaysValidCache()
        cache.isCacheValid("test").assertFalse("cache is not primed, thus not valid")
        cache.cachedOrGetBy(argument = "test", cacheableGetter = { x -> x })
        cache.isCacheValid("test").assertTrue()

        cache.isCacheValid("test2").assertFalse("new argument")
        cache.cachedOrGetBy(argument = "test2", cacheableGetter = { x -> x })
        cache.isCacheValid("test2").assertTrue()
        cache.isCacheValid("test").assertFalse()

        val invalid = alwaysInvalidForCache()
        invalid.isCacheValid("test").assertFalse()
        invalid.cachedOrGetBy(argument = "test", cacheableGetter = { x -> x })
        invalid.isCacheValid("test").assertFalse("always invalid for caching")
    }

    @Test
    fun invalidate() {
        val cache = alwaysValidCache()
        cache.cachedOrGetBy("test", { x -> x })
        cache.isCacheValid("test").assertTrue()
        cache.invalidate()
        cache.isCacheValid("test").assertFalse()
    }

    @Test
    fun getCachedValue() {
        val cache = alwaysValidCache()
        cache.getCachedValue("test").assertNull()
        cache.cachedOrGetBy(argument = "test", cacheableGetter = { x -> x })
        cache.getCachedValue("test").assert("test")
        cache.getCachedValue("test2").assertNull()
        cache.getCachedValue("test").assert("test", message = "should preserve cache when querying")
        cache.invalidate()
        cache.getCachedValue("test").assertNull()

        val invalid = alwaysInvalidForCache()
        invalid.getCachedValue("test").assertNull()
        invalid.cachedOrGetBy(argument = "test", cacheableGetter = { x -> x })
        invalid.getCachedValue("test").assertNull()
    }

    @Test
    fun cachedOrGetBy() {
        val cache = alwaysValidCache()
        assertCalled { shouldBeCalled: () -> Unit ->
            cache.cachedOrGetBy(
                argument = "test",
                cacheableGetter = { it: String ->
                    shouldBeCalled()
                    it
                }
            ).assert("test")
        }
        cache.cachedOrGetBy(argument = "test", cacheableGetter = { shouldNotBeCalled() }).assert("test")
        cache.cachedOrGetBy(argument = "test2", cacheableGetter = { it -> "random" }).assert("random")
        cache.cachedOrGetBy(argument = "test2", cacheableGetter = { shouldNotBeCalled() }).assert("random")

        cache.invalidate()
        cache.cachedOrGetBy(argument = "test2", cacheableGetter = { it -> "newValue"}).assert("newValue")
    }

    private fun alwaysValidCache(): CachedByArguments<String, String> {
        return CachedByArguments<String, String>(
            isValidForCache = { t: String -> true },
            isArgumentEqual = { lhs: String?, rhs: String? -> lhs == rhs }
        )
    }

    private fun alwaysInvalidForCache(): CachedByArguments<String, String> {
        return CachedByArguments<String, String>(
            isValidForCache = { t: String -> false },
            isArgumentEqual = { lhs: String?, rhs: String? -> lhs == rhs }
        )
    }

}