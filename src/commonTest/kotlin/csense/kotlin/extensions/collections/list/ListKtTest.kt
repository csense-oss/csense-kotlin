@file:Suppress("unused")

package csense.kotlin.extensions.collections.list

import csense.kotlin.tests.assertions.*
import kotlin.test.Test


class ListKtTest {

    @Test
    fun limitToSize() {
        listOf<String>().limitToSize(0).assertSize(0)
        listOf<String>().limitToSize(-1).assertSize(0)
        listOf<String>().limitToSize(1).assertSize(0)

        listOf("a").limitToSize(0).assertSize(0)
        listOf("a").limitToSize(1).assertSize(1)
        listOf("a").limitToSize(-1).assertSize(0)
        listOf("a").limitToSize(2).assertSize(1)

        listOf("a", "b").limitToSize(1).apply {
            assertSize(1)
            first().assert("a")
        }

        listOf("a", "b").limitToSize(2).apply {
            assertSize(2)
            first().assert("a")
            last().assert("b")
        }
        listOf("a", "b").limitToSize(30).assertSize(2)
    }


    @Test
    fun repeatToSize() {
        val collection: MutableList<Int> = mutableListOf()
        collection.repeatToSize(50).apply {
            size.assert(0, "repeating nothing is wrong and gives nothing")
        }

        collection.add(42)
        collection.repeatToSize(50).apply {
            size.assert(50)
        }

        collection.add(42)
        collection.repeatToSize(50).apply {
            size.assert(50)
        }
    }

    class ListTSubListIntRange {
        @Test
        fun empty() {
            listOf<String>().subList(0..2).assertEmpty()
            listOf<String>().subList(0..1).assertEmpty()
            listOf<String>().subList(0..10).assertEmpty()
        }

        @Test
        fun single() {
            listOf("a").subList(0..2).assertEmpty()
            listOf("a").subList(0..1).apply {
                assertSize(1)
                first().assert("a")
            }
            listOf("a").subList(0..10).assertEmpty()
        }

        @Test
        fun multiple() {
            listOf("a", "b").subList(0..2).apply {
                assertSize(2)
                first().assert("a")
                last().assert("b")
            }
            listOf("a", "b").subList(0..1).apply {
                assertSize(1)
                first().assert("a")
            }
            listOf("a", "b").subList(1..2).apply {
                assertSize(1)
                first().assert("b")
            }
            listOf("a", "b").subList(0..10).assertEmpty()
        }
    }

    class ListTRepeat {
        @Test
        fun empty() {
            listOf<String>().repeat(0).assertEmpty()
            listOf<String>().repeat(1).assertEmpty()
            listOf<String>().repeat(100).assertEmpty()
        }

        @Test
        fun single() {
            listOf("a").repeat(0).assertEmpty()
            listOf("a").repeat(1).apply {
                assertSize(1)
                first().assert("a")
            }
            listOf("a").repeat(10).assertSize(10)
        }

        @Test
        fun multiple() {
            listOf("a", "1").repeat(0).assertEmpty()
            listOf("a", "1").repeat(1).apply {
                assertSize(2)
                first().assert("a")
                last().assert("1")
            }
            listOf("a", "1").repeat(10).assertSize(20)
        }
    }

    class ListTSubListSafe {
        @Test
        fun empty() {
            listOf<String>().subListSafe(0, 2).assertEmpty()
            listOf<String>().subListSafe(0, 1).assertEmpty()
            listOf<String>().subListSafe(0, 10).assertEmpty()
        }

        @Test
        fun single() {
            listOf("a").subListSafe(0, 2).assertEmpty()
            listOf("a").subListSafe(0, 1).apply {
                assertSize(1)
                first().assert("a")
            }
            listOf("a").subListSafe(0, 10).assertEmpty()
        }

        @Test
        fun multiple() {
            listOf("a", "b").subListSafe(0, 2).apply {
                assertSize(2)
                first().assert("a")
                last().assert("b")
            }
            listOf("a", "b").subListSafe(0, 1).apply {
                assertSize(1)
                first().assert("a")
            }
            listOf("a", "b").subListSafe(1, 2).apply {
                assertSize(1)
                first().assert("b")
            }
            listOf("a", "b").subListSafe(0, 10).assertEmpty()
        }
    }

    class ListTSubListFromIndex {
        @Test
        fun empty() {
            listOf<String>().subList(0).assertEmpty()
            listOf<String>().subList(-1).assertEmpty()
            listOf<String>().subList(1).assertEmpty()
        }

        @Test
        fun single() {
            listOf("a").subList(0).apply {
                assertSize(1)
                first().assert("a")
            }
            listOf("b").subList(-1).assertEmpty()
            listOf("c").subList(1).assertEmpty()
        }
    }

    @Test
    fun multiple() {
        listOf("a", "b", "c").subList(0).apply {
            assertSize(3)
            this[0].assert("a")
            this[1].assert("b")
            this[2].assert("c")
        }
        listOf("a", "b", "c").subList(-1).assertEmpty("its out of bounds")
        listOf("a", "b", "c").subList(1).apply {
            assertSize(2)
            this[0].assert("b")
            this[1].assert("c")
        }
        listOf("a", "b", "c").subList(2).apply {
            assertSize(1)
            this[0].assert("c")
        }
        listOf("a", "b", "c").subList(3).assertEmpty()
    }
}

class ListForEachIsInstanceIntProgression {
    @Test
    fun empty() {
        val anyList = listOf<Any>()
        anyList.forEachIsInstance<String>(anyList.indices) { shouldNotBeCalled() }
        anyList.forEachIsInstance<Int>(anyList.indices) { shouldNotBeCalled() }
    }

    @Test
    fun single() {
        val anyList = listOf<Any>("")
        assertCalled {
            anyList.forEachIsInstance<String>(anyList.indices) { it() }
        }
        anyList.forEachIsInstance<Int>(anyList.indices) { shouldNotBeCalled() }
        assertCalled {
            anyList.forEachIsInstance<CharSequence>(anyList.indices) { it() }
        }
        assertCalled {
            anyList.forEachIsInstance<Comparable<String>>(anyList.indices) { it() }
        }
    }

    @Test
    fun multiple() {
        val anyList = listOf<Any>("", 42, true)
        assertCalled {
            anyList.forEachIsInstance<String>(anyList.indices) { it() }
        }
        assertCalled {
            anyList.forEachIsInstance<Int>(anyList.indices) { it() }
        }
        assertCalled {
            anyList.forEachIsInstance<Boolean>(anyList.indices) { it() }
        }
        anyList.forEachIsInstance<Array<*>>(anyList.indices) { shouldNotBeCalled() }
        anyList.forEachIsInstance<Char>(anyList.indices) { shouldNotBeCalled() }

    }

    @Test
    fun multipleIndicies() {
        val anyList = listOf<Any>("", 42, true)
        anyList.forEachIsInstance<String>(1 until anyList.size) { shouldNotBeCalled() }
        anyList.forEachIsInstance<String>(2 until anyList.size) { shouldNotBeCalled() }

        anyList.forEachIsInstance<String>(1..1) { shouldNotBeCalled() }
        anyList.forEachIsInstance<String>(2..2) { shouldNotBeCalled() }
        assertCalled {
            anyList.forEachIsInstance<String>(0..0) { it() }
        }
        assertCalled {
            anyList.forEachIsInstance<String>(0..1) { it() }
        }


        anyList.forEachIsInstance<Int>(0..0) { shouldNotBeCalled() }
        assertCalled {
            anyList.forEachIsInstance<Int>(0..1) { it() }
        }
        anyList.forEachIsInstance<Int>(2..2) { shouldNotBeCalled() }

    }
}


class ListListTCombine {
    class Empty {

        @Test
        fun emptyOuter() {
            val emptyA = listOf<List<String>>()
            val emptyB = listOf<List<String>>()
            emptyA.combine(emptyB).assertSize(0)
        }

        @Test
        fun emptyInnerSameLength() {
            val emptyA = listOf<List<String>>(listOf())
            val emptyB = listOf<List<String>>(listOf())
            emptyA.combine(emptyB).apply {
                assertSize(1)
                first().assertSize(0)
            }
        }

        @Test
        fun emptyInnerNotSameLength() {
            val emptyA = listOf<List<String>>(listOf(), listOf())
            val emptyB = listOf<List<String>>(listOf())
            emptyA.combine(emptyB).apply {
                assertSize(2)
                first().assertSize(0)
                last().assertSize(0)
            }
        }

        @Test
        fun emptyInnerNotSameLengthBToA() {
            val emptyA = listOf<List<String>>(listOf(), listOf())
            val emptyB = listOf<List<String>>(listOf())
            emptyB.combine(emptyA).apply {
                assertSize(2)
                first().assertSize(0)
                last().assertSize(0)
            }
        }
    }

    class SinglesSingleTop {

        @Test
        fun singleA() {
            val a = listOf(listOf("a"))
            val b = listOf<List<String>>()
            val res = a.combine(b)
            res.assertSize(1)
            res.first().assertSize(1)
            res.first().first().assert("a")
        }

        @Test
        fun singleB() {
            val a = listOf<List<String>>()
            val b = listOf(listOf("b"))
            val res = a.combine(b)
            res.assertSize(1)
            res.first().assertSize(1)
            res.first().first().assert("b")
        }

    }

    class MultipleSingleTop {
        @Test
        fun multipleAToBMultipleSameLength() {
            val a = listOf(listOf("a", "b"))
            val b = listOf(listOf("1", "2"))
            val res = a.combine(b)
            res.assertSize(1)
            res.first().assertSize(4)
            res.first().elementAt(0).assert("a")
            res.first().elementAt(1).assert("b")
            res.first().elementAt(2).assert("1")
            res.first().elementAt(3).assert("2")
        }

        @Test
        fun multipleBToAMultipleSameLength() {
            val a = listOf(listOf("q", "w", "e"))
            val b = listOf(listOf("z", "x", "c"))
            val res = b.combine(a)
            res.assertSize(1)
            res.first().assertSize(6)
            res.first().elementAt(0).assert("z")
            res.first().elementAt(1).assert("x")
            res.first().elementAt(2).assert("c")
            res.first().elementAt(3).assert("q")
            res.first().elementAt(4).assert("w")
            res.first().elementAt(5).assert("e")
        }

        @Test
        fun multipleNotSameLength() {
            val a = listOf(listOf("a", "b"))
            val b = listOf(listOf("1", "2", "3"))
            val res = a.combine(b)
            res.assertSize(1)
            res.first().assertSize(5)
            res.first().elementAt(0).assert("a")
            res.first().elementAt(1).assert("b")
            res.first().elementAt(2).assert("1")
            res.first().elementAt(3).assert("2")
            res.first().elementAt(4).assert("3")
        }
    }

}

class ListTDoesNotContain {
    @Test
    fun empty() {
        listOf<String>().doesNotContain("a").assertTrue()
        listOf<String>().doesNotContain("b").assertTrue()
    }

    @Test
    fun single() {
        listOf("1").doesNotContain("a").assertTrue()
        listOf("a").doesNotContain("b").assertTrue()
        listOf("a").doesNotContain("a").assertFalse()
        listOf("1").doesNotContain("1").assertFalse()
    }

    @Test
    fun multiple() {
        listOf("1", "a").doesNotContain("c").assertTrue()
        listOf("1", "a").doesNotContain("b").assertTrue()
        listOf("1", "a").doesNotContain("a").assertFalse()
        listOf("1", "a").doesNotContain("1").assertFalse()
        listOf("1", "a").doesNotContain("2").assertTrue()
    }
}
