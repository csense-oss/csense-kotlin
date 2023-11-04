@file:Suppress("unused")

package csense.kotlin.extensions.collections.iterable

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class InvokeEachTest {

    class IterableFunction0ROInvokeEach {
        @Test
        fun empty() {
            //assumption is; that if there were any issues with bounds then it would throw
            listOf<csense.kotlin.Function0R<String>>().invokeEach()
        }

        @Test
        fun single() {
            assertCalled {
                listOf({
                    it();""
                }).invokeEach()
            }
        }

        @Test
        fun multiple() {
            assertCalled(times = 2) {
                listOf({
                    it();"23"
                }, {
                    it();"154"
                }).invokeEach()
            }
        }
    }

    class IterableFunction1I1OInvokeEachWithElement {
        @Test
        fun empty() {
            //assumption is; that if there were any issues with bounds then it would throw
            listOf<Function1<String, String>>().invokeEachWith("")
        }

        @Test
        fun single() = assertCalled { shouldBeCalled: () -> Unit ->
            listOf<Function1<String, String>>({
                it.assert("input")
                shouldBeCalled()
                ""
            }).invokeEachWith("input")
        }

        @Test
        fun multiple() = assertCalled(times = 2) { shouldBeCalled: () -> Unit ->
            listOf<Function1<String, String>>({
                it.assert("input")
                shouldBeCalled()
                "R1"
            }, {
                it.assert("input")
                shouldBeCalled()
                "R2"
            }).invokeEachWith("input")
        }
    }

    class IterableFunction2I1I2OInvokeEachWithFirstElement {
        @Test
        fun empty() {
            listOf<Function2<String, String, String>>().invokeEachWith("", "")
        }

        @Test
        fun single() = assertCalled { shouldBeCalled: () -> Unit ->
            listOf<Function2<String, String, String>>({ first, second ->
                first.assert("f")
                second.assert("s")
                shouldBeCalled()
                "R"
            }).invokeEachWith("f", "s")
        }

        @Test
        fun multiple() = assertCalled(times = 2) { shouldBeCalled: () -> Unit ->
            listOf<Function2<String, String, String>>({ first, second ->
                first.assert("f")
                second.assert("s")
                shouldBeCalled()
                "R1"
            }, { first, second ->
                first.assert("f")
                second.assert("s")
                shouldBeCalled()
                "R2"
            }).invokeEachWith("f", "s")
        }
    }

    class IterableFunction3I1I2I3OInvokeEachWithFirstElement {
        @Test
        fun empty() {
            listOf<Function3<String, String, String, String>>()
                .invokeEachWith("", "", "")
        }

        @Test
        fun single() = assertCalled { shouldBeCalled: () -> Unit ->
            listOf<Function3<String, String, String, String>>({ first, second, third ->
                first.assert("f")
                second.assert("s")
                third.assert("t")
                shouldBeCalled()
                "R"
            }).invokeEachWith(
                firstElement = "f",
                secondElement = "s",
                thirdElement = "t"
            )
        }

        @Test
        fun multiple() = assertCalled(times = 2) { shouldBeCalled: () -> Unit ->
            listOf<Function3<String, String, String, String>>({ first, second, third ->
                first.assert("f")
                second.assert("s")
                third.assert("t")
                shouldBeCalled()
                "R1"
            }, { first, second, third ->
                first.assert("f")
                second.assert("s")
                third.assert("t")
                shouldBeCalled()
                "R2"
            }).invokeEachWith(
                firstElement = "f",
                secondElement = "s",
                thirdElement = "t"
            )
        }
    }

    class IterableFunction4I1I2I3I4OInvokeEachWithFirstElement {
        @Test
        fun empty() {
            listOf<Function4<String, String, String, String, String>>()
                .invokeEachWith("", "", "", "")
        }

        @Test
        fun single() = assertCalled { shouldBeCalled: () -> Unit ->
            listOf<Function4<String, String, String, String, String>>({ first, second, third, fourth ->
                first.assert("f")
                second.assert("s")
                third.assert("t")
                fourth.assert("fo")
                shouldBeCalled()
                "R"
            }).invokeEachWith(
                firstElement = "f",
                secondElement = "s",
                thirdElement = "t",
                fourthElement = "fo"
            )
        }

        @Test
        fun multiple() = assertCalled(times = 2) { shouldBeCalled: () -> Unit ->
            listOf<Function4<String, String, String, String, String>>({ first, second, third, fourth ->
                first.assert("f")
                second.assert("s")
                third.assert("t")
                fourth.assert("fo")
                shouldBeCalled()
                "R1"
            }, { first, second, third, fourth ->
                first.assert("f")
                second.assert("s")
                third.assert("t")
                fourth.assert("fo")
                shouldBeCalled()
                "R2"
            }).invokeEachWith(
                firstElement = "f",
                secondElement = "s",
                thirdElement = "t",
                fourthElement = "fo"
            )
        }
    }

    class IterableFunction5I1I2I3I4I5OInvokeEachWithFirstElement {
        @Test
        fun empty() {
            listOf<Function5<String, String, String, String, String, String>>()
                .invokeEachWith("", "", "", "", "")
        }

        @Test
        fun single() = assertCalled { shouldBeCalled: () -> Unit ->
            listOf<Function5<String, String, String, String, String, String>>(
                { first, second, third, fourth, fifth ->
                    first.assert("f")
                    second.assert("s")
                    third.assert("t")
                    fourth.assert("fo")
                    fifth.assert("fi")
                    shouldBeCalled()
                    "R"
                }).invokeEachWith(
                firstElement = "f",
                secondElement = "s",
                thirdElement = "t",
                fourthElement = "fo",
                fifthElement = "fi"
            )
        }

        @Test
        fun multiple() = assertCalled(times = 2) { shouldBeCalled: () -> Unit ->
            listOf<Function5<String, String, String, String, String, String>>(
                { first, second, third, fourth, fifth ->
                    first.assert("f")
                    second.assert("s")
                    third.assert("t")
                    fourth.assert("fo")
                    fifth.assert("fi")
                    shouldBeCalled()
                    "R1"
                },
                { first, second, third, fourth, fifth ->
                    first.assert("f")
                    second.assert("s")
                    third.assert("t")
                    fourth.assert("fo")
                    fifth.assert("fi")
                    shouldBeCalled()
                    "R2"
                }).invokeEachWith(
                firstElement = "f",
                secondElement = "s",
                thirdElement = "t",
                fourthElement = "fo",
                fifthElement = "fi"
            )
        }
    }

    class IterableFunction6I1I2I3I4I5I6OInvokeEachWithFirstElement {
        @Test
        fun empty() {
            listOf<Function6<String, String, String, String, String, String, String>>()
                .invokeEachWith("", "", "", "", "", "")
        }

        @Test
        fun single() = assertCalled { shouldBeCalled: () -> Unit ->
            listOf<Function6<String, String, String, String, String, String, String>>(
                { first, second, third, fourth, fifth, sixth ->
                    first.assert("f")
                    second.assert("s")
                    third.assert("t")
                    fourth.assert("fo")
                    fifth.assert("fi")
                    sixth.assert("si")
                    shouldBeCalled()
                    "R"
                }).invokeEachWith(
                firstElement = "f",
                secondElement = "s",
                thirdElement = "t",
                fourthElement = "fo",
                fifthElement = "fi",
                sixthElement = "si"
            )
        }

        @Test
        fun multiple() = assertCalled(times = 2) { shouldBeCalled: () -> Unit ->
            listOf<Function6<String, String, String, String, String, String, String>>(
                { first, second, third, fourth, fifth, sixth ->
                    first.assert("f")
                    second.assert("s")
                    third.assert("t")
                    fourth.assert("fo")
                    fifth.assert("fi")
                    sixth.assert("si")
                    shouldBeCalled()
                    "R1"
                },
                { first, second, third, fourth, fifth, sixth ->
                    first.assert("f")
                    second.assert("s")
                    third.assert("t")
                    fourth.assert("fo")
                    fifth.assert("fi")
                    sixth.assert("si")
                    shouldBeCalled()
                    "R2"
                }).invokeEachWith(
                firstElement = "f",
                secondElement = "s",
                thirdElement = "t",
                fourthElement = "fo",
                fifthElement = "fi",
                sixthElement = "si"
            )
        }
    }
}