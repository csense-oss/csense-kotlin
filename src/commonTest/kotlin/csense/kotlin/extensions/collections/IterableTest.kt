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
        fun single() {
            //TODO test single element condition here.
        }

        @Test
        fun multiple() {
            //TODO test multiple element condition here.
        }
    }

    class IterableFunction2I1I2OInvokeEachWithFirstElement {
        @Test
        fun empty() {
            //TODO test empty condition here.
        }

        @Test
        fun single() {
            //TODO test single element condition here.
        }

        @Test
        fun multiple() {
            //TODO test multiple element condition here.
        }
    }

    class IterableFunction3I1I2I3OInvokeEachWithFirstElement {
        @Test
        fun empty() {
            //TODO test empty condition here.
        }

        @Test
        fun single() {
            //TODO test single element condition here.
        }

        @Test
        fun multiple() {
            //TODO test multiple element condition here.
        }
    }

    class IterableFunction4I1I2I3I4OInvokeEachWithFirstElement {
        @Test
        fun empty() {
            //TODO test empty condition here.
        }

        @Test
        fun single() {
            //TODO test single element condition here.
        }

        @Test
        fun multiple() {
            //TODO test multiple element condition here.
        }
    }

    class IterableFunction5I1I2I3I4I5OInvokeEachWithFirstElement {
        @Test
        fun empty() {
            //TODO test empty condition here.
        }

        @Test
        fun single() {
            //TODO test single element condition here.
        }

        @Test
        fun multiple() {
            //TODO test multiple element condition here.
        }
    }

    class IterableFunction6I1I2I3I4I5I6OInvokeEachWithFirstElement {
        @Test
        fun empty() {
            //TODO test empty condition here.
        }

        @Test
        fun single() {
            //TODO test single element condition here.
        }

        @Test
        fun multiple() {
            //TODO test multiple element condition here.
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
        fun single() {
            //TODO test single element condition here.
        }

        @Test
        fun multiple() {
            //TODO test multiple element condition here.
        }
    }

    class IterableFunction2I1I2OInvokeEachWithLazyFirstElement {
        @Test
        fun empty() {
            listOf<Function2<String, String, String>>().invokeEachWithLazy(::shouldNotBeCalled, ::shouldNotBeCalled)
            //TODO test empty condition here.
        }

        @Test
        fun single() {
            //TODO test single element condition here.
        }

        @Test
        fun multiple() {
            //TODO test multiple element condition here.
        }
    }

    class IterableFunction3I1I2I3OInvokeEachWithLazyFirstElement {
        @Test
        fun empty() {
            listOf<Function3<String, String, String, String>>().invokeEachWithLazy(
                ::shouldNotBeCalled, ::shouldNotBeCalled, ::shouldNotBeCalled
            )
            //TODO test empty condition here.
        }

        @Test
        fun single() {
            //TODO test single element condition here.
        }

        @Test
        fun multiple() {
            //TODO test multiple element condition here.
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
        fun single() {
            //TODO test single element condition here.
        }

        @Test
        fun multiple() {
            //TODO test multiple element condition here.
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
        fun single() {
            //TODO test single element condition here.
        }

        @Test
        fun multiple() {
            //TODO test multiple element condition here.
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
        fun single() {
            //TODO test single element condition here.
        }

        @Test
        fun multiple() {
            //TODO test multiple element condition here.
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
                }.assertNotNullAndEquals("test")
        }

        @Test
        fun multiple() {
            listOf(42, 100, 1, 102).largest { it }.assertNotNullAndEquals(102)
            listOf(42, 100, 1, 102).largest { 0 - it }.assertNotNullAndEquals(1)
        }
    }

    @Test
    fun cIsNullOrEmpty() {
        val nullLst: List<String>? = null
        nullLst.isNullOrEmpty().assertTrue()
        listOf<String>().isNullOrEmpty().assertTrue()
        listOf("").isNullOrEmpty().assertFalse()
        listOf("","test2").isNullOrEmpty().assertFalse()
    }

    @Test
    fun cIsNotNullOrEmpty() {
        val nullLst: List<String>? = null
        nullLst.isNotNullOrEmpty().assertFalse()
        listOf<String>().isNotNullOrEmpty().assertFalse()
        listOf("").isNotNullOrEmpty().assertTrue()
        listOf("","test2").isNotNullOrEmpty().assertTrue()
    }

    @Test
    fun cNullOnEmpty() {
        val nullLst: List<String>? = null
        nullLst.nullOnEmpty().assertNull()
        listOf<String>().nullOnEmpty().assertNull()
        listOf("").nullOnEmpty().assertNotNull()
        listOf("","test2").nullOnEmpty().assertNotNull()
    }
}