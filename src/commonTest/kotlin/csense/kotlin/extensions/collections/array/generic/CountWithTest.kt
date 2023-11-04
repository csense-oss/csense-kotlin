package csense.kotlin.extensions.collections.array.generic

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class CountWithTest {
    @Test
    fun empty() {
        arrayOf<String>().countWith { _: Int, _: String ->
            shouldNotBeCalled()
        }
    }

    @Test
    fun singleAlwaysTrue() {
        arrayOf("test").countWith { count: Int, item: String ->
            count.assert(0)
            item.assert("test")
            true
        }.assert(1)
    }

    @Test
    fun singleAlwaysFalse() {
        arrayOf("test").countWith { count: Int, item: String ->
            count.assert(0)
            item.assert("test")
            false
        }.assert(0)
    }

    @Test
    fun multipleAlwaysTrue(): Unit = assertCallbackCalledWith(
        listOf(
            0 to "test",
            1 to "omg"
        ),
        assertFunction = Pair<Int, String>::equals,
        testCode = { assertExpected: (Pair<Int, String>) -> Unit ->
            arrayOf("test", "omg").countWith { count: Int, item: String ->
                assertExpected(count to item)
                true
            }.assert(2)
        }
    )

    @Test
    fun multipleAlwaysFalse() = assertCallbackCalledWith(
        listOf(
            0 to "test",
            0 to "omg"
        ),
        assertFunction = Pair<Int, String>::equals,
        testCode = { assertExpected: (Pair<Int, String>) -> Unit ->
            arrayOf("test", "omg").countWith { count: Int, item: String ->
                assertExpected(count to item)
                false
            }.assert(0)
        }
    )

    @Test
    fun multipleOnlyCountingTest() = assertCallbackCalledWith(
        listOf(
            0 to "test",
            1 to "omg",
            1 to "test",
            2 to "test2"
        ),
        assertFunction = Pair<Int, String>::equals,
        testCode = { assertExpected: (Pair<Int, String>) -> Unit ->
            arrayOf("test", "omg", "test", "test2").countWith { count: Int, item: String ->
                assertExpected(count to item)
                item == "test"
            }.assert(2)
        }
    )

    @Test
    fun canReturn() {
        arrayOf("test").countWith { _: Int, _: String ->
            return@canReturn
        }
        shouldNotBeCalled()
    }

}