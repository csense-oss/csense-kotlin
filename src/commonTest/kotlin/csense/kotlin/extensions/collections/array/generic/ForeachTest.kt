package csense.kotlin.extensions.collections.array.generic

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class ForeachTest {

    class ForeachDiscard {
        @Test
        fun empty() {
            arrayOf<String>().forEachDiscard { shouldNotBeCalled() }
        }

        @Test
        fun single() {
            arrayOf("test").forEachDiscard { it: String ->
                it.assert("test")
                "52"
            }
        }

        @Test
        fun multiple() {
            val testData: Array<String> = arrayOf("test", "1234")
            assertCallbackCalledWith(testData.toList()) { expected: (String) -> Unit ->
                testData.forEachDiscard { it: String ->
                    expected(it)
                    "52"
                }
            }
        }
    }

    class ForEachBackwards {
        @Test
        fun empty() {
            arrayOf<String>().forEachBackwards { shouldNotBeCalled() }
        }

        @Test
        fun single() = assertCalled { shouldBeCalled: () -> Unit ->
            arrayOf("test").forEachBackwards { it: String ->
                it.assert("test")
                shouldBeCalled()
            }
        }

        @Test
        fun multiple(): Unit = assertCallbackCalledWith(listOf("1234", "test")) { expected: (String) -> Unit ->
            arrayOf("test", "1234").forEachBackwards { it: String ->
                expected(it)
            }
        }

    }
}