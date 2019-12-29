package csense.kotlin.extensions.collections

import csense.kotlin.tests.assertions.*
import kotlin.test.Test

class IterableTest {


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

    class iterableEForEachNotNull {
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
                    ::shouldNotBeCalled, ::shouldNotBeCalled, ::shouldNotBeCalled)
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
                    ::shouldNotBeCalled, ::shouldNotBeCalled, ::shouldNotBeCalled, ::shouldNotBeCalled)
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
                    ::shouldNotBeCalled, ::shouldNotBeCalled, ::shouldNotBeCalled, ::shouldNotBeCalled, ::shouldNotBeCalled)
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
                    ::shouldNotBeCalled, ::shouldNotBeCalled, ::shouldNotBeCalled)
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


}