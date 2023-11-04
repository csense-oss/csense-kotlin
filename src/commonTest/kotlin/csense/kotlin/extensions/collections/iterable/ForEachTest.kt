@file:Suppress("unused")

package csense.kotlin.extensions.collections.iterable

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class ForEachTest {

    class IterableEForEachNotNull {
        @Test
        fun empty() {
            val itt: Iterable<String?> = listOf()
            itt.forEachNotNull { shouldNotBeCalled() }
        }

        @Test
        fun singelNull() {
            val itt: Iterable<String?> = listOf(null)
            itt.forEachNotNull { shouldNotBeCalled() }
        }

        @Test
        fun singleNotNull() = assertCalled {
            val itt: Iterable<String?> = listOf("asd")
            itt.forEachNotNull { it() }
        }

        @Test
        fun multiple() = assertCalled(times = 2) {
            val itt: Iterable<String?> = listOf("asd", "1234")
            itt.forEachNotNull { it() }
        }

        @Test
        fun multipleNull() = assertNotCalled {
            val itt: Iterable<String?> = listOf(null, null)
            itt.forEachNotNull { it() }
        }

        @Test
        fun multipleMixed() = assertCalled(times = 3) {
            val itt: Iterable<String?> =
                listOf(null, "asd", "1234", null, "1")
            itt.forEachNotNull { it() }
        }
    }


    class IterableForEachIsInstanceAction {
        @Test
        fun empty() {
            val anyList = listOf<Any>()
            anyList.forEachIsInstance<String> { shouldNotBeCalled() }
        }

        @Test
        fun single() {
            val anyList = listOf<Any>("")
            assertCalled {
                anyList.forEachIsInstance<String> { it() }
            }
            anyList.forEachIsInstance<Int> { shouldNotBeCalled() }
            assertCalled {
                anyList.forEachIsInstance<CharSequence> { it() }
            }
            assertCalled {
                anyList.forEachIsInstance<Comparable<String>> { it() }
            }
        }

        @Test
        fun multiple() {
            val anyList = listOf<Any>("", 42, true)
            assertCalled {
                anyList.forEachIsInstance<String> { it() }
            }
            assertCalled {
                anyList.forEachIsInstance<Int> { it() }
            }
            assertCalled {
                anyList.forEachIsInstance<Boolean> { it() }
            }
            anyList.forEachIsInstance<Char> {
                shouldNotBeCalled()
            }
            anyList.forEachIsInstance<Array<*>> { shouldNotBeCalled() }
        }
    }

    class IterableTForEachBackwards {
        @Test
        fun empty() {
            val empty: Iterable<String> = listOf()
            empty.forEachBackwards {
                shouldNotBeCalled()
            }
        }

        @Test
        fun single() = assertCalled(times = 1) { shouldBeCalled: () -> Unit ->
            val single: Iterable<String> = listOf("test")
            single.forEachBackwards {
                it.assert("test")
                shouldBeCalled()
            }
        }

        @Test
        fun multiple() = assertCalled(times = 2) { shouldBeCalled: () -> Unit ->
            var haveSeenLast = false
            val multiple: Iterable<String> = listOf("first", "last")
            multiple.forEachBackwards {
                if (haveSeenLast) {
                    it.assert("first")
                } else {
                    it.assert("last")
                }
                haveSeenLast = true
                shouldBeCalled()
            }
        }
    }


}