package csense.kotlin.extensions.collections

import csense.kotlin.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class IterableTest {


    //region invoke each with
    class IterableFunction0ROInvokeEach {
        @Test
        fun empty() {
            //assumption is; that if there were any issues with bounds then it would throw
            listOf<Function0R<String>>().invokeEach()
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
        fun single() = assertCalled { shouldBeCalled ->
            listOf<Function1<String, String>>({
                it.assert("input")
                shouldBeCalled()
                ""
            }).invokeEachWith("input")
        }

        @Test
        fun multiple() = assertCalled(times = 2) { shouldBeCalled ->
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
        fun single() = assertCalled { shouldBeCalled ->
            listOf<Function2<String, String, String>>({ first, second ->
                first.assert("f")
                second.assert("s")
                shouldBeCalled()
                "R"
            }).invokeEachWith("f", "s")
        }

        @Test
        fun multiple() = assertCalled(times = 2) { shouldBeCalled ->
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
        fun single() = assertCalled { shouldBeCalled ->
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
        fun multiple() = assertCalled(times = 2) { shouldBeCalled ->
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
        fun single() = assertCalled { shouldBeCalled ->
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
        fun multiple() = assertCalled(times = 2) { shouldBeCalled ->
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
        fun single() = assertCalled { shouldBeCalled ->
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
        fun multiple() = assertCalled(times = 2) { shouldBeCalled ->
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
        fun single() = assertCalled { shouldBeCalled ->
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
        fun multiple() = assertCalled(times = 2) { shouldBeCalled ->
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
    //endregion

    //region Invoke each lazy (1 - 6 )
    class IterableFunction1I1OInvokeEachWithLazyElement {
        @Test
        fun empty() {
            listOf<Function1<String, String>>().invokeEachWithLazy(::shouldNotBeCalled)
        }

        @Test
        fun single() = assertCalled { shouldBeCalled ->
            listOf<Function1<String, String>>({ first ->
                first.assert("first")
                shouldBeCalled()
                "result"
            }).invokeEachWithLazy { "first" }
        }

        @Test
        fun multiple() = assertCalled(times = 2) { shouldBeCalled ->
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
        fun single() = assertCalled { shouldBeCalled ->
            listOf<Function2<String, String, String>>({ first, second ->
                first.assert("first")
                second.assert("second")
                shouldBeCalled()
                "result"
            }).invokeEachWithLazy({ "first" }, { "second" })
        }

        @Test
        fun multiple() = assertCalled(times = 2) { shouldBeCalled ->
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
        fun single() = assertCalled { shouldBeCalled ->
            listOf<Function3<String, String, String, String>>({ first, second, third ->
                first.assert("first")
                second.assert("second")
                third.assert("third")
                shouldBeCalled()
                "result"
            }).invokeEachWithLazy({ "first" }, { "second" }, { "third" })
        }

        @Test
        fun multiple() = assertCalled(times = 2) { shouldBeCalled ->
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
        fun single() = assertCalled { shouldBeCalled ->
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
        fun multiple() = assertCalled(times = 2) { shouldBeCalled ->
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
        fun single() = assertCalled { shouldBeCalled ->
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
        fun multiple() = assertCalled(times = 2) { shouldBeCalled ->
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
        fun single() = assertCalled { shouldBeCalled ->
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
        fun multiple() = assertCalled(times = 2) { shouldBeCalled ->
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
    //endregion

    @Test
    fun iterableTIsEmpty() {
        val empty: Iterable<String> = arrayListOf()
        empty.isEmpty().assertTrue()
        val single: Iterable<String> = arrayListOf("1")
        single.isEmpty().assertFalse()
        val multiple: Iterable<String> = arrayListOf("a", "b")
        multiple.isEmpty().assertFalse()
    }

    @Test
    fun iterableTIsNotEmpty() {
        val empty: Iterable<String> = arrayListOf()
        empty.isNotEmpty().assertFalse()
        val single: Iterable<String> = arrayListOf("1")
        single.isNotEmpty().assertTrue()
        val multiple: Iterable<String> = arrayListOf("a", "b")
        multiple.isNotEmpty().assertTrue()

    }

    @Test
    fun iterableTSkipIfEmptyOr() {
        val empty: Iterable<String> = arrayListOf()
        empty.skipIfEmptyOr { shouldNotBeCalled() }

        val single: Iterable<String> = arrayListOf("1")
        assertCalled { single.skipIfEmptyOr(it) }

        val multiple: Iterable<String> = arrayListOf("a", "b")
        assertCalled { multiple.skipIfEmptyOr(it) }
    }

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

    class IterableEPartitionSafe {
        @Test
        fun empty() {
            listOf<String>().partitionSafe { shouldNotBeCalled() }
        }

        @Test
        fun single() {
            listOf(1).partitionSafe { true }.apply {
                trueForPredicate.assertSize(1)
                falseForPredicate.assertEmpty()
                trueForPredicate.first().assert(1)
            }
            listOf(2).partitionSafe { false }.apply {
                trueForPredicate.assertEmpty()
                falseForPredicate.assertSize(1)
                falseForPredicate.first().assert(2)
            }
            listOf(3).partitionSafe { it == 3 }.apply {
                trueForPredicate.assertSize(1)
                falseForPredicate.assertEmpty()
            }
        }

        @Test
        fun multiple() {
            listOf("test", "1234", "a").partitionSafe { false }.apply {
                trueForPredicate.assertEmpty()
                falseForPredicate.assertSize(3)
            }
            listOf("test", "1234", "a").partitionSafe { true }.apply {
                trueForPredicate.assertSize(3)
                falseForPredicate.assertEmpty()
            }
            listOf("test", "1234", "a").partitionSafe { it == "a" }.apply {
                trueForPredicate.assertSize(1)
                falseForPredicate.assertSize(2)
                trueForPredicate.first().assert("a")
                falseForPredicate.assertContainsAll("test", "1234")
            }
            listOf("test", "1234", "a").partitionSafe { it.length == 4 }.apply {
                trueForPredicate.assertSize(2)
                falseForPredicate.assertSize(1)
                trueForPredicate.assertContainsAll("1234", "test")
                falseForPredicate.first().assert("a")
            }
        }
    }

    class IterableELargest {
        @Test
        fun empty() {
            listOf<String>().largest { shouldNotBeCalled() }
        }

        @Test
        fun single() {
            listOf("test")
                .largest {
                    it.assert("test")
                    it.length
                }.assert("test")
        }

        @Test
        fun multiple() {
            listOf(42, 100, 1, 102).largest { it }.assert(102)
            listOf(42, 100, 1, 102).largest { 0 - it }.assert(1)
        }
    }

    @Test
    fun cIsNullOrEmpty() {
        val nullLst: List<String>? = null
        nullLst.isNullOrEmpty().assertTrue()
        listOf<String>().nullable().isNullOrEmpty().assertTrue()
        listOf("").nullable().isNullOrEmpty().assertFalse()
        listOf("", "test2").nullable().isNullOrEmpty().assertFalse()
    }

    @Test
    fun cIsNotNullOrEmpty() {
        val nullLst: List<String>? = null
        nullLst.isNotNullOrEmpty().assertFalse()
        listOf<String>().nullable().isNotNullOrEmpty().assertFalse()
        listOf("").nullable().isNotNullOrEmpty().assertTrue()
        listOf("", "test2").nullable().isNotNullOrEmpty().assertTrue()
    }

    @Test
    fun cNullOnEmpty() {
        val nullLst: List<String>? = null
        nullLst.nullOnEmpty().assertNull()
        listOf<String>().nullOnEmpty().assertNull()
        listOf("").nullOnEmpty().assertNotNull()
        listOf("", "test2").nullOnEmpty().assertNotNull()
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
        fun single() = assertCalled(times = 1) { shouldBeCalled ->
            val single: Iterable<String> = listOf("test")
            single.forEachBackwards {
                it.assert("test")
                shouldBeCalled()
            }
        }

        @Test
        fun multiple() = assertCalled(times = 2) { shouldBeCalled ->
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

    class IterableTTakeOrNull {
        @Test
        fun empty() {
            val lst: Iterable<String> = listOf()
            lst.takeOrNull(0).assertNotNullApply { assertEmpty() }
            lst.takeOrNull(1).assertNotNullApply { assertEmpty() }
            lst.takeOrNull(-1).assertNull()
        }

        @Test
        fun single() {
            val lst: Iterable<String> = listOf("abc")
            lst.takeOrNull(0).assertNotNullApply { assertEmpty() }
            lst.takeOrNull(1).assertNotNullApply {
                assertSingle("abc")
            }
            lst.takeOrNull(-1).assertNull()
        }

        @Test
        fun multiple() {
            val lst: Iterable<String> = listOf("abc", "123")
            lst.takeOrNull(-1).assertNull()
            lst.takeOrNull(0).assertNotNullApply { assertEmpty() }
            lst.takeOrNull(1).assertNotNullApply {
                assertSingle("abc")
            }
            lst.takeOrNull(2).assertNotNullApply {
                assertSize(2)
                this[0].assert("abc")
                this[1].assert("123")
            }
            lst.takeOrNull(3).assertNotNullApply {
                assertSize(2)
                this[0].assert("abc")
                this[1].assert("123")
            }
        }
    }
}