@file:Suppress("unused")

package csense.kotlin.extensions.collections.collection

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class FindTest {
    class CollectionAnyFindWithType {
        @Test
        fun empty() {
            val collection: Collection<String> = listOf()
            collection.findWithType<Int> { shouldNotBeCalled() }
            collection.findWithType<String> { shouldNotBeCalled() }
        }

        @Test
        fun single() {
            val collection: Collection<Any?> = listOf("test")
            collection.findWithType<Int> { shouldNotBeCalled() }
            collection.findWithType<String> {
                it.assert("test")
                false
            }.assertNull()

            collection.findWithType<String> {
                it.assert("test")
                true
            }.assert("test")
        }

        @Test
        fun multiple() {
            val collection: Collection<Any?> = listOf("test", "1234", 1234)
            collection.findWithType<Char> { shouldNotBeCalled() }
            collection.findWithType<Char> { shouldNotBeCalled() }
            collection.findWithType<Number> {
                true
            }?.assertByEquals(1234)


            assertCallbackCalledWith(listOf("test", "1234")) { assertCallback ->
                collection.findWithType<String> {
                    assertCallback(it)
                    false
                }.assertNull()
            }

            collection.findWithType<String> {
                it.assert("test")
                true
            }.assert("test")

            collection.findWithType<String> {
                it == "1234"
            }.assert("1234")
        }

    }

}