package csense.kotlin.extensions.collections.collection

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class ForEachWithTest {
    @Test
    fun empty() {
        val empty: Collection<String> = listOf()
        empty.forEachWith {
            shouldNotBeCalled()
        }
    }

    @Test
    fun single(): Unit = assertCalled { shouldBeCalled: () -> Unit ->
        val single: Collection<String> = listOf("test")
        single.forEachWith {
            this.assert("test")
            shouldBeCalled()
        }
    }

    @Test
    fun multiple() {
        val args: List<String> = listOf("test", "1234")
        assertCallbackCalledWith(args) { callback: (String) -> Unit ->
            args.forEachWith {
                callback(this)
            }
        }
    }
}