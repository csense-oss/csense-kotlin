package csense.kotlin.extensions.collections.iterable

import csense.kotlin.classes.general.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class CountWithTest {

    @Test
    fun empty() {
        val input: Iterable<String> = listOf()
        val result: Int = input.countWith(
            predicateWithCount = { _: Int, _: String ->
                shouldNotBeCalled()
            }
        )
        result.assert(0)
    }

    @Test
    fun singleFalse() {
        val input: Iterable<String> = listOf("test")
        val result: Int = input.countWith(
            predicateWithCount = { count: Int, item: String ->
                item.assert("test")
                count.assert(0)
                false
            }
        )
        result.assert(0)
    }

    @Test
    fun singleTrue() {
        val input: Iterable<String> = listOf("test")
        val result: Int = input.countWith(
            predicateWithCount = { index: Int, item: String ->
                item.assert("test")
                index.assert(0)
                true
            }
        )
        result.assert(1)
    }

    @Test
    fun multipleAllFalseInputCorrect() {
        val callOrder: List<Pair<Int, String>> = listOf(
            0 to "test",
            0 to "test2",
            0 to "test3"
        )
        val input: Iterable<String> = listOf("test", "test2", "test3")
        assertCallbackCalledWith(
            expectedItemsInOrder = callOrder,
            testCode = { assertFunction: (Pair<Int, String>) -> Unit ->
                input.countWith { count: Int, item: String ->
                    assertFunction(count to item)
                    false
                }.assert(0)
            },
            assertFunction = { lhs: Pair<Int, String>, rhs: Pair<Int, String> ->
                return@assertCallbackCalledWith lhs == rhs
            }
        )
    }

    @Test
    fun multipleAllTrue() {
        val countAcc = IncrementalCounter(0)
        val input: Iterable<String> = listOf("test", "test2", "test3")
        input.countWith { count: Int, _: String ->
            count.assert(countAcc.valueAndIncrement)
            true
        }.assert(3)
    }

    @Test
    fun multipleSomeTrue() {
        val countAcc = IncrementalCounter(0)
        val input: Iterable<String> = listOf("test", "test2", "test3")
        input.countWith { count: Int, _: String ->
            count.assert(countAcc.valueAndIncrement)
            return@countWith count != 2
        }.assert(2)
    }


}