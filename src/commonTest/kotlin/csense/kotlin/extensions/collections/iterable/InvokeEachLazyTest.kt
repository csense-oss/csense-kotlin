@file:Suppress("unused")

package csense.kotlin.extensions.collections.iterable

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class InvokeEachLazyTest {
    class IterableFunction1I1OInvokeEachWithLazyElement {
        @Test
        fun empty() {
            listOf<Function1<String, String>>().invokeEachWithLazy(::shouldNotBeCalled)
        }

        @Test
        fun single() = assertCalled { shouldBeCalled: () -> Unit ->
            listOf<Function1<String, String>>({ first ->
                first.assert("first")
                shouldBeCalled()
                "result"
            }).invokeEachWithLazy { "first" }
        }

        @Test
        fun multiple() = assertCalled(times = 2) { shouldBeCalled: () -> Unit ->
            listOf<Function1<String, String>>({ first ->
                first.assert("first")
                shouldBeCalled()
                "result"
            }, { first ->
                first.assert("first")
                shouldBeCalled()
                "result"
            }).invokeEachWithLazy { "first" }
        }
    }

    class IterableFunction2I1I2OInvokeEachWithLazyFirstElement {
        @Test
        fun empty() {
            listOf<Function2<String, String, String>>().invokeEachWithLazy(::shouldNotBeCalled, ::shouldNotBeCalled)
        }

        @Test
        fun single() = assertCalled { shouldBeCalled: () -> Unit ->
            listOf<Function2<String, String, String>>({ first, second ->
                first.assert("first")
                second.assert("second")
                shouldBeCalled()
                "result"
            }).invokeEachWithLazy({ "first" }, { "second" })
        }

        @Test
        fun multiple() = assertCalled(times = 2) { shouldBeCalled: () -> Unit ->
            listOf<Function2<String, String, String>>({ first, second ->
                first.assert("first")
                second.assert("second")
                shouldBeCalled()
                "result1"
            }, { first, second ->
                first.assert("first")
                second.assert("second")
                shouldBeCalled()
                "result2"
            }).invokeEachWithLazy({ "first" }, { "second" })
        }
    }

    class IterableFunction3I1I2I3OInvokeEachWithLazyFirstElement {
        @Test
        fun empty() {
            listOf<Function3<String, String, String, String>>().invokeEachWithLazy(
                ::shouldNotBeCalled, ::shouldNotBeCalled, ::shouldNotBeCalled
            )
        }

        @Test
        fun single() = assertCalled { shouldBeCalled: () -> Unit ->
            listOf<Function3<String, String, String, String>>({ first, second, third ->
                first.assert("first")
                second.assert("second")
                third.assert("third")
                shouldBeCalled()
                "result"
            }).invokeEachWithLazy({ "first" }, { "second" }, { "third" })
        }

        @Test
        fun multiple() = assertCalled(times = 2) { shouldBeCalled: () -> Unit ->
            listOf<Function3<String, String, String, String>>({ first, second, third ->
                first.assert("first")
                second.assert("second")
                third.assert("third")
                shouldBeCalled()
                "result1"
            }, { first, second, third ->
                first.assert("first")
                second.assert("second")
                third.assert("third")
                shouldBeCalled()
                "result2"
            }).invokeEachWithLazy({ "first" }, { "second" }, { "third" })
        }
    }

    class IterableFunction4I1I2I3I4OInvokeEachWithLazyFirstElement {
        @Test
        fun empty() {
            listOf<Function4<String, String, String, String, String>>().invokeEachWithLazy(
                ::shouldNotBeCalled, ::shouldNotBeCalled, ::shouldNotBeCalled, ::shouldNotBeCalled
            )
        }

        @Test
        fun single() = assertCalled { shouldBeCalled: () -> Unit ->
            listOf<Function4<String, String, String, String, String>>({ first, second, third, fourth ->
                first.assert("first")
                second.assert("second")
                third.assert("third")
                fourth.assert("fourth")
                shouldBeCalled()
                "result"
            }).invokeEachWithLazy({ "first" }, { "second" }, { "third" }, { "fourth" })
        }

        @Test
        fun multiple() = assertCalled(times = 2) { shouldBeCalled: () -> Unit ->
            listOf<Function4<String, String, String, String, String>>({ first, second, third, fourth ->
                first.assert("first")
                second.assert("second")
                third.assert("third")
                fourth.assert("fourth")
                shouldBeCalled()
                "result1"
            }, { first, second, third, fourth ->
                first.assert("first")
                second.assert("second")
                third.assert("third")
                fourth.assert("fourth")
                shouldBeCalled()
                "result2"
            }).invokeEachWithLazy({ "first" }, { "second" }, { "third" }, { "fourth" })
        }
    }

    class IterableFunction5I1I2I3I4I5OInvokeEachWithLazyFirstElement {
        @Test
        fun empty() {
            listOf<Function5<String, String, String, String, String, String>>().invokeEachWithLazy(
                ::shouldNotBeCalled, ::shouldNotBeCalled, ::shouldNotBeCalled, ::shouldNotBeCalled, ::shouldNotBeCalled
            )
        }

        @Test
        fun single() = assertCalled { shouldBeCalled: () -> Unit ->
            listOf<Function5<String, String, String, String, String, String>>({ first, second, third, fourth, fifth ->
                first.assert("first")
                second.assert("second")
                third.assert("third")
                fourth.assert("fourth")
                fifth.assert("fifth")
                shouldBeCalled()
                "result"
            }).invokeEachWithLazy({ "first" }, { "second" }, { "third" }, { "fourth" }, { "fifth" })
        }

        @Test
        fun multiple() = assertCalled(times = 2) { shouldBeCalled: () -> Unit ->
            listOf<Function5<String, String, String, String, String, String>>({ first, second, third, fourth, fifth ->
                first.assert("first")
                second.assert("second")
                third.assert("third")
                fourth.assert("fourth")
                fifth.assert("fifth")
                shouldBeCalled()
                "result1"
            }, { first, second, third, fourth, fifth ->
                first.assert("first")
                second.assert("second")
                third.assert("third")
                fourth.assert("fourth")
                fifth.assert("fifth")
                shouldBeCalled()
                "result2"
            }).invokeEachWithLazy({ "first" }, { "second" }, { "third" }, { "fourth" }, { "fifth" })
        }
    }

    class IterableFunction6I1I2I3I4I5I6OInvokeEachWithLazyFirstElement {
        @Test
        fun empty() {
            listOf<Function6<String, String, String, String, String, String, String>>().invokeEachWithLazy(
                ::shouldNotBeCalled, ::shouldNotBeCalled, ::shouldNotBeCalled,
                ::shouldNotBeCalled, ::shouldNotBeCalled, ::shouldNotBeCalled
            )
        }

        @Test
        fun single() = assertCalled { shouldBeCalled: () -> Unit ->
            listOf<Function6<String, String, String, String, String, String, String>>({ first, second, third, fourth, fifth, sixth ->
                first.assert("first")
                second.assert("second")
                third.assert("third")
                fourth.assert("fourth")
                fifth.assert("fifth")
                sixth.assert("sixth")
                shouldBeCalled()
                "result"
            }).invokeEachWithLazy({ "first" }, { "second" }, { "third" }, { "fourth" }, { "fifth" }, { "sixth" })
        }

        @Test
        fun multiple() = assertCalled(times = 2) { shouldBeCalled: () -> Unit ->
            listOf<Function6<String, String, String, String, String, String, String>>({ first, second, third, fourth, fifth, sixth ->
                first.assert("first")
                second.assert("second")
                third.assert("third")
                fourth.assert("fourth")
                fifth.assert("fifth")
                sixth.assert("sixth")
                shouldBeCalled()
                "result1"
            }, { first, second, third, fourth, fifth, sixth ->
                first.assert("first")
                second.assert("second")
                third.assert("third")
                fourth.assert("fourth")
                fifth.assert("fifth")
                sixth.assert("sixth")
                shouldBeCalled()
                "result2"
            }).invokeEachWithLazy({ "first" }, { "second" }, { "third" }, { "fourth" }, { "fifth" }, { "sixth" })
        }
    }
}