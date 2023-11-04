@file:Suppress("unused")

package csense.kotlin.extensions.collections.iterable

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class OnEmptyTest {
    class IterableTOnEmptyOnEmpty {
        @Test
        fun empty() {
            val itt = listOf<String>().asIterable()
            val result = itt.onEmpty(listOf("test"))
            result.single().assert("test")
        }

        @Test
        fun single() {
            val itt = listOf("test").asIterable()
            val result = itt.onEmpty(listOf())
            result.single().assert("test")
        }

        @Test
        fun multiple() {
            val itt = listOf("test", "1234").asIterable()
            val result = itt.onEmpty(listOf())
            result.count().assert(2)
            result.assertContainsInOrder("test", "1234")
        }
    }

    class IterableTOnEmptyOnEmptyAction {
        @Test
        fun empty() = assertCalled { shouldBeCalled: () -> Unit ->
            val itt = listOf<String>().asIterable()
            val result = itt.onEmpty { shouldBeCalled(); listOf("test") }
            result.single().assert("test")
        }

        @Test
        fun single() {
            val itt = listOf("test").asIterable()
            val result = itt.onEmpty { shouldNotBeCalled() }
            result.single().assert("test")
        }

        @Test
        fun multiple() {
            val itt = listOf("test", "1234").asIterable()
            val result = itt.onEmpty { shouldNotBeCalled() }
            result.count().assert(2)
            result.assertContainsInOrder("test", "1234")
        }
    }

    @Test
    fun onEmpty() {
        TODO("vararg")
    }
}