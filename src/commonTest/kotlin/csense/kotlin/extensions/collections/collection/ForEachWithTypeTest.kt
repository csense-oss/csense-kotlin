package csense.kotlin.extensions.collections.collection

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class ForEachWithTypeTest {
    @Test
    fun empty() {
        val collection: Collection<String> = listOf()
        collection.forEachWithType<Int> { shouldNotBeCalled() }
        collection.forEachWithType<String> { shouldNotBeCalled() }
    }

    @Test
    fun single() {
        val collection: Collection<String> = listOf("1")
        collection.forEachWithType<Int> { shouldNotBeCalled() }
        assertCalled { shouldBeCalled: () -> Unit ->
            collection.forEachWithType<String> {
                it.assert("1")
                shouldBeCalled()
            }
        }
        assertCalled { shouldBeCalled: () -> Unit ->
            collection.forEachWithType<CharSequence> {
                it.assert("1")
                shouldBeCalled()
            }
        }
        assertCalled { shouldBeCalled: () -> Unit ->
            collection.forEachWithType<Comparable<String>> {
                it.compareTo("1").assert(0, "expect it to be the same string as \"1\"")
                shouldBeCalled()
            }
        }
    }

    @Test
    fun multiple() {
        val collection: Collection<Any> = listOf("a", "b", 42)
        assertCalled { shouldBeCalled: () -> Unit ->
            collection.forEachWithType<Int> {
                it.assert(42)
                shouldBeCalled()
            }
        }
        var isFirstCall = true
        assertCalled(times = 2) { shouldBeCalled: () -> Unit ->
            collection.forEachWithType<String> {
                if (isFirstCall) {
                    it.assert("a")
                } else {
                    it.assert("b")
                }
                isFirstCall = false
                shouldBeCalled()
            }
        }
        collection.forEachWithType<Char> { shouldNotBeCalled() }
    }
}