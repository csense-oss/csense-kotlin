package org.csenseoss.kotlin.datastructures.cachedBy.cachedByArguments

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class CachedByArgumentsTest {
    @Test
    fun isCacheValid() {
        val cache: CachedByArguments<String, String> = alwaysValidCache()
        cache.isCacheValid(argument = "test").assertFalse("cache is not primed, thus not valid")
        cache.cachedOrGetBy(argument = "test", cacheableGetter = { x -> x })
        cache.isCacheValid(argument = "test").assertTrue()

        cache.isCacheValid(argument = "test2").assertFalse("new argument")
        cache.cachedOrGetBy(argument = "test2", cacheableGetter = { x -> x })
        cache.isCacheValid(argument = "test2").assertTrue()
        cache.isCacheValid(argument = "test").assertFalse()

        val invalid: CachedByArguments<String, String> = alwaysInvalidForCache()
        invalid.isCacheValid(argument ="test").assertFalse()
        invalid.cachedOrGetBy(argument = "test", cacheableGetter = { x -> x })
        invalid.isCacheValid(argument ="test").assertFalse("always invalid for caching")
    }

    @Test
    fun invalidate() {
        val cache: CachedByArguments<String, String> = alwaysValidCache()
        cache.cachedOrGetBy(argument = "test", cacheableGetter = { x -> x })
        cache.isCacheValid(argument = "test").assertTrue()
        cache.invalidate()
        cache.isCacheValid(argument = "test").assertFalse()
    }

    @Test
    fun getCachedValue() {
        val cache: CachedByArguments<String, String> = alwaysValidCache()
        cache.getCachedValue("test").assertNull()
        cache.cachedOrGetBy(argument = "test", cacheableGetter = { x -> x })
        cache.getCachedValue("test").assert("test")
        cache.getCachedValue("test2").assertNull()
        cache.getCachedValue("test").assert("test", message = "should preserve cache when querying")
        cache.invalidate()
        cache.getCachedValue("test").assertNull()

        val invalid: CachedByArguments<String, String> = alwaysInvalidForCache()
        invalid.getCachedValue("test").assertNull()
        invalid.cachedOrGetBy(argument = "test", cacheableGetter = { x -> x })
        invalid.getCachedValue("test").assertNull()
    }

    @Test
    fun cachedOrGetBy() {
        val cache: CachedByArguments<String, String> = alwaysValidCache()
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
        cache.cachedOrGetBy(argument = "test2", cacheableGetter = { it: String -> "random" }).assert("random")
        cache.cachedOrGetBy(argument = "test2", cacheableGetter = { shouldNotBeCalled() }).assert("random")

        cache.invalidate()
        cache.cachedOrGetBy(argument = "test2", cacheableGetter = { it: String -> "newValue" }).assert("newValue")
    }

    private fun alwaysValidCache(): CachedByArguments<String, String> {
        return CachedByArguments(
            isValidForCache = { t: String -> true },
            isArgumentEqual = { lhs: String?, rhs: String? -> lhs == rhs }
        )
    }

    private fun alwaysInvalidForCache(): CachedByArguments<String, String> {
        return CachedByArguments(
            isValidForCache = { t: String -> false },
            isArgumentEqual = { lhs: String?, rhs: String? -> lhs == rhs }
        )
    }

}